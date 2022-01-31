import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Goods {

    public boolean addGoods(String command, MongoCollection<Document> goods){
        String convertCommand = command.replaceAll("addgoods", "").trim();
        StringBuilder digit = new StringBuilder();
        String nameGood = "";
        for(Character c: convertCommand.toCharArray()){
            if(Character.isDigit(c)){
                digit.append(c);
            }else {
                nameGood += c;
            }
        }
        if(digit.length() == 0 || nameGood.isEmpty()){
            System.out.println("Не указано название товара или цена");
            return false;
        }
        int price = Integer.parseInt(String.valueOf(digit));
        Document product = new Document().
                append("name", nameGood.trim()).
                append("price", price);
        goods.insertOne(product);
        System.out.println("Товар успешно добавлен");
        return true;
    }
}
