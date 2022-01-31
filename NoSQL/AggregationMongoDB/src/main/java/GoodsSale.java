import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;


public class GoodsSale
{

    public boolean addGoodsForSale(String command, MongoCollection<Document> shops)
    {
        command = command.replaceAll("sale", "").trim();
        if (command.isEmpty())
        {
            System.out.println("Не указан товар или магазин!");
        }
        if(command.contains(" "))
        {
            String[] sale = command.split(" ");
            shops.updateOne(eq("name", sale[1]), new Document().append("$push", new Document("goods", sale[0])));
            return true;
        }
        System.out.println("Не указан товар или магазин!");
        return false;
    }
}
