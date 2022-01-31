import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    public boolean addShop(String command, MongoCollection<Document> shops){
        String name = command.replaceAll("addshop", "").trim();
        if(name.isEmpty()){
            System.out.println("Вы не указали название магазина!");
            return false;
        }
        List<Document> goods = new ArrayList<>();
        Document shop = new Document().
                append("name", name).
                append("goods", goods);
        shops.insertOne(shop);
        System.out.println("Магазин добавлен в БД");
        return true;
    }
}
