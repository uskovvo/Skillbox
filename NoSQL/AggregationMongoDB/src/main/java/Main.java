import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.Serializable;
import java.util.*;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;

public class Main
{
    private static String command;
    private static boolean action = true;

    public static void main(String[] args)
    {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> shops = database.getCollection("shops");
        MongoCollection<Document> goods = database.getCollection("goods");

        while (action)
        {
            System.out.println("Введите команду:");
            command = new Scanner(System.in).nextLine().toLowerCase(Locale.ROOT);
            if(command.isEmpty()){
                System.out.println("Вы не ввели команду!");
                continue;
            }

            if (command.contains("addshop"))
            {
                if(!new Shop().addShop(command, shops))
                {
                    continue;
                }
                action = nextAction();
            }
            else if (command.contains("addgoods"))
            {
                if(!new Goods().addGoods(command, goods))
                {
                    continue;
                }
                action = nextAction();
            }
            else if (command.contains("sale"))
            {
                if(!new GoodsSale().addGoodsForSale(command, shops))
                {
                    continue;
                }
                action = nextAction();
            }
            else if(command.contains("agg"))
            {
                List<Serializable> list = new ArrayList<>();
                list.add(new BasicDBObject("$lt", Arrays.asList("$Basket.price", 100)));
                list.add(1);
                list.add(0);

                AggregateIterable<Document> aggregateIterable = shops
                        .aggregate(List
                                .of(lookup("goods", "goods", "name", "Basket"),
                                        unwind("$Basket"),
                                        group("$name",
                                                avg("avgPrice", "$Basket.price"),
                                                min("minPriceGoods", "$Basket.name"),
                                                max("maxPriceGoods", "$Basket.name"),
                                                sum("count100", new BasicDBObject("$cond", list)),
                                                sum("count", 1))));

                for(Document doc: aggregateIterable){
                    System.out.println("Название магазина: " + doc.get("_id") +
                            "\n Самый дешевый товар: " + doc.get("minPriceGoods") +
                            "\n Самый дорогой товар: " + doc.get("maxPriceGoods") +
                            "\n Количество товаров в магазине: " + doc.get("count") +
                            "\n Средняя цена товаров: " + doc.get("avgPrice") +
                            "\n Кол-во товаров с ценой меньше 100 руб: " + doc.get("count100") + "\n");
                }
            }
        }
    }

    private static boolean nextAction()
    {
        System.out.println("Продолжаем дальше?\n Введите Y или N\n");
        command = new Scanner(System.in).nextLine().toLowerCase(Locale.ROOT);
        if(command.isEmpty())
        {
            nextAction();
        }
        return !command.contains("n");
    }
}