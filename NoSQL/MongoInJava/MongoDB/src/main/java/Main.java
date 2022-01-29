import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.BsonDocument;
import org.bson.Document;

public class Main {
    private static BsonDocument query;

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> studentCollection = new MongoDB().createDB(database);

        //Определяем общее кол-во студентов
        System.out.println("Общее кол-во студентов в БД:" + studentCollection.countDocuments());

        //Через запрос определяем кол-во студентов старше 40 лет
        query = BsonDocument.parse("{age: {$gt: 40}}");
        System.out.println("Кол-во студентов старше 40 лет: " + studentCollection.countDocuments(query));

        System.out.println(studentCollection
                .find()
                .sort(Sorts
                        .ascending("age"))
                .first()
                .getString("name"));
        System.out.println(studentCollection
                .find()
                .sort(Sorts
                        .descending("age"))
                .first()
                .get("courses"));
    }
}
