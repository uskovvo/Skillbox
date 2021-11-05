import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;

public class SubwayMSK {
    private static JSONObject jsonMapMetro = new JSONObject();
    private static JSONObject jsonStations = new JSONObject();
    private static JSONArray jsonLines = new JSONArray();
    private static JSONArray jsonConnections = new JSONArray();

    public static void createSubwayMap(Elements elements){
        elements.forEach(element -> {
            JSONObject line = new JSONObject();
            JSONArray stations = new JSONArray();
            String lineNumber = element.child(0).attr("data-line");
            String lineName = element.text();
            line.put("number", lineNumber);
            line.put("name", lineName);
            jsonLines.add(line);

            Elements findStations = element.nextElementSibling().select("span.name");
            findStations.forEach(station -> {
                stations.add(station.text());

                JSONObject connection = new JSONObject();
                JSONArray connectionLines = new JSONArray();

                Element findConnection = station.nextElementSibling();
                if(findConnection == null){
                    return;
                }
                connection.put("name", station.text());
                connection.put("lineNumber", lineNumber);
                connectionLines.add(connection);

                while(findConnection != null){
                    JSONObject json = new JSONObject();
                    String connectStationLine = findConnection.className().split(" ")[1].replaceAll("\\w+-", "");
                    String connectStationName = findConnection.attr("title").split("«")[1].split("»")[0];
                    json.put("line", connectStationLine);
                    json.put("station", connectStationName);
                    findConnection = findConnection.nextElementSibling();
                }
                jsonConnections.add(connectionLines);
            });
            jsonStations.put(element.nextElementSibling().child(1).attr("data-line"), stations);
        });

        jsonMapMetro.put("stations", jsonStations);
        jsonMapMetro.put("lines", jsonLines);
        jsonMapMetro.put("connections", jsonConnections);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            FileWriter writer = new FileWriter("src/main/resources/subway.json", false);
            writer.write(gson.toJson(jsonMapMetro));
            writer.flush();
            writer.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
