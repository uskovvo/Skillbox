import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadJsonFile {
    public static void readJsonFile(String path) {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader(path);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONObject stationsObject = (JSONObject) jsonObject.get("stations");
            AtomicInteger stationsCount = new AtomicInteger();
            stationsObject.keySet().forEach(line -> {
                JSONArray stationsArray = (JSONArray) stationsObject.get(line);
                System.out.println("Станций метро на линии " + line + ": " + stationsArray.size());
                stationsCount.addAndGet(stationsArray.size());
            });
            System.out.println("\nОбщее количество станций: " + stationsCount);

            JSONArray connectionsArray = (JSONArray) jsonObject.get("connections");
            System.out.println("Количество переходов: " + connectionsArray.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
