import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapSubway {
    private static JSONObject jsonMapMetro = new JSONObject();
    private static JSONObject jsonStations = new JSONObject();
    private static JSONArray jsonLines = new JSONArray();
    private static JSONArray jsonConnections = new JSONArray();

    public static void createSubwayMap(Elements elements) {
        elements.forEach(element -> {
            Elements cells = element.getElementsByTag("td");
            if(cells.size() == 7 || cells.size() == 8){
                Element elementStationName = cells.get(1).select("a").first();
                Element elementStationLine = cells.get(0).select("img").last();
                Elements elementConnection = cells.get(3).select("img");
                if(elementStationName != null && elementStationLine != null){
                    String stationName = elementStationName.text();
                    String lineName = elementStationLine.attr("alt");
                    String urlLineNumber = elementStationLine.attr("src");
                    urlLineNumber = urlLineNumber.substring(urlLineNumber
                                    .lastIndexOf("Moskwa_Metro_Line_") + 18)
                                    .split(".svg.")[0];
                    String lineNumber = "";
                    try {
                        lineNumber = java.net.URLDecoder.decode(urlLineNumber, StandardCharsets.UTF_8.name());
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                    if(!lineNumber.isEmpty() && !stationName.isEmpty()){
                        if(!jsonStations.containsKey(lineNumber)) {
                            jsonStations.put(lineNumber, new JSONArray());
                        }
                        ((JSONArray)jsonStations.get(lineNumber)).add(stationName);
                    }
                    JSONObject line = new JSONObject();
                    if(!lineNumber.isEmpty() && !lineName.isEmpty()){
                        line.put("number", lineNumber);
                        line.put("name", lineName);
                        if(!jsonLines.contains(line)){
                            jsonLines.add(line);
                        }
                    }
                    JSONArray connect = new JSONArray();
                    elementConnection.forEach(elConnect -> {
                        JSONObject lineConnect = new JSONObject();
                        Matcher match = Pattern.compile("(?<=Переход на станцию )[А-Я а-я]+(?= [А-Я])")
                                        .matcher(elConnect.attr("alt"));
                        String urlConnection = elConnect.attr("src");
                        urlConnection = urlConnection.substring(urlConnection
                                        .lastIndexOf("Moskwa_Metro_Line_") + 18).split(".svg.")[0];
                        String lineNum = "";
                        try{
                            lineNum = java.net.URLDecoder.decode(urlConnection, StandardCharsets.UTF_8.name());
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                        if(!lineNum.isEmpty() && match.find()) {
                            lineConnect.put("line", lineNum);
                            lineConnect.put("station", match.group());
                            connect.add(lineConnect);
                        }
                        if(connect.size() > 1 && !jsonConnections.contains(connect)){
                            jsonConnections.add(connect);
                        }
                    });
                }
            }
        });
        jsonMapMetro.put("stations", jsonStations);
        jsonMapMetro.put("lines", jsonLines);
        jsonMapMetro.put("connections", jsonConnections);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/subway.json", false);
            fileWriter.write(gson.toJson(jsonMapMetro));
            fileWriter.flush();
            fileWriter.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
