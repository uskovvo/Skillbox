import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullSubwayMSK {
    private static JSONObject jsonMapMetro = new JSONObject();
    private static JSONObject jsonStations = new JSONObject();
    private static JSONArray jsonLines = new JSONArray();
    private static JSONArray jsonConnections = new JSONArray();

    public static void createSubwayMap(Elements elements){
        elements.forEach(element -> {
            List<String> numberLines = new ArrayList<>();
            List<String> nameLines = new ArrayList<>();
            Elements lines = element.child(4).getElementsByTag("tbody");
            lines.forEach(line -> {
                Elements numbers = line.getElementsByTag("strong");
                numbers.forEach(number -> {
                    numberLines.add(number.text());
                });
            });
            Elements names = element.child(4).getElementsByAttributeValue("title", "список станций");
            names.forEach(name -> {
                nameLines.add(name.text());
            });
            for(int a = 0; a < numberLines.size(); a++) {
                JSONObject line = new JSONObject();
                line.put("number", numberLines.get(a));
                line.put("name", nameLines.get(a));
                jsonLines.add(line);
                JSONArray stations = new JSONArray();
                Elements searchNameStations = element.select("table:nth-child(15) > tbody");
                int finalA = a + 3;
                searchNameStations.forEach(search -> {
                    Elements nameStations = search.select("tr:nth-child(" + finalA + ")");
                    nameStations.forEach(name -> {
                        String stationLine = name.select("td:nth-child(1) > p > span").text();
                        String nameStation = name.select("td:nth-child(2)").text();
                        System.out.println(nameStation);
                        String transfer = name.select("td:nth-child(4) > p").text();
                        System.out.println(transfer);
                    });
                });
            }
        });
    }
}
