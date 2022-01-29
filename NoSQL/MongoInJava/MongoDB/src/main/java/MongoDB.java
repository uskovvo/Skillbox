import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {
    private static final String fileCvs = "src/main/resources/mongo.csv";
    private final Student student = new Student(fileCvs);

    public MongoCollection<Document> createDB (MongoDatabase database){
        MongoCollection<Document> collection = database.getCollection("Students");

        collection.drop();

        student.getStudent().forEach((k, v) -> {
            Document firstDocument = new Document()
                    .append("name", k.getName())
                    .append("age", k.getAge())
                    .append("courses", v);

            collection.insertOne(firstDocument);
        });
        return collection;
    }
}
