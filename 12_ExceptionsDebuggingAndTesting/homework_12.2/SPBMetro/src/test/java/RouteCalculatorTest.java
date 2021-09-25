import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    private static final String DATA_FILE = "src/main/resources/map.json";
    List<Station> route = new ArrayList<>();
    StationIndex stationIndex;
    RouteCalculator calculator = getRouteCalculator();
    List<Station> withoutConnection = new ArrayList<>();
    List<Station> withOneConnection = new ArrayList<>();
    List<Station> withTwoConnection = new ArrayList<>();

    private RouteCalculator getRouteCalculator() {
        createStationIndex();
        return new RouteCalculator(stationIndex);
    }


    @Override
    protected void setUp() throws Exception {
        Line line1 = new Line(1, "Красная");
        Line line2 = new Line(2, "Синяя");

        route.add(new Station("Чернышевская", line1));
        route.add(new Station("Площадь Восстания", line1));
        route.add(new Station("Маяковская", line2));
        route.add(new Station("Гостиный дом", line2));

        withoutConnection.add(stationIndex.getStation("Гражданский проспект"));
        withoutConnection.add(stationIndex.getStation("Академическая"));
        withoutConnection.add(stationIndex.getStation("Политехническая"));

        withOneConnection.add(stationIndex.getStation("Горьковская"));
        withOneConnection.add(stationIndex.getStation("Невский проспект"));
        withOneConnection.add(stationIndex.getStation("Гостиный двор"));
        withOneConnection.add(stationIndex.getStation("Маяковская"));
        withOneConnection.add(stationIndex.getStation("Площадь Александра Невского"));
//        withOneConnection.add(stationIndex.getStation("Елизаровская"));

        withTwoConnection.add(stationIndex.getStation("Елизаровская"));
        withTwoConnection.add(stationIndex.getStation("Площадь Александра Невского"));
        withTwoConnection.add(stationIndex.getStation("Площадь Александра Невского"));
        withTwoConnection.add(stationIndex.getStation("Лиговский проспект"));
        withTwoConnection.add(stationIndex.getStation("Достоевская"));
        withTwoConnection.add(stationIndex.getStation("Владимирская"));
        withTwoConnection.add(stationIndex.getStation("Площадь Восстания"));

    }

    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        assertEquals(expected, actual);
    }

    public void testWithoutConnection(){
        Station station1 = stationIndex.getStation("Гражданский проспект");
        Station station2 = stationIndex.getStation("Политехническая");
        List<Station> expected = withoutConnection;
        List<Station> actual = calculator.getShortestRoute(station1, station2);
        assertEquals(expected, actual);
    }

    public void testWithOneConnection(){
        Station station1 = stationIndex.getStation("Горьковская");
        Station station2 = stationIndex.getStation("Площадь Александра Невского");
        List<Station> expected = withOneConnection;
        List<Station> actual = calculator.getShortestRoute(station1, station2);
    }

    public void testWithTwoConnection(){
        Station station = stationIndex.getStation("Новочеркасская");
        Station station1 = stationIndex.getStation("Чернышевская");
        List<Station> expected = withTwoConnection;
        List<Station> actual = calculator.getShortestRoute(station, station1);
    }

    @Override
    protected void tearDown() throws Exception {

    }

    private void createStationIndex() {
        stationIndex = new StationIndex();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject);

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void parseConnections(JSONArray connectionsArray) {
        connectionsArray.forEach(connectionObject ->
        {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                int lineNumber = ((Long) itemObject.get("line")).intValue();
                String stationName = (String) itemObject.get("station");

                Station station = stationIndex.getStation(stationName, lineNumber);
                if (station == null) {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            stationIndex.addConnection(connectionStations);
        });
    }

    private void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            int lineNumber = Integer.parseInt((String) lineNumberObject);
            Line line = stationIndex.getLine(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                Station station = new Station((String) stationObject, line);
                stationIndex.addStation(station);
                line.addStation(station);
            });
        });
    }

    private void parseLines(JSONArray linesArray) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    ((Long) lineJsonObject.get("number")).intValue(),
                    (String) lineJsonObject.get("name")
            );
            stationIndex.addLine(line);
        });
    }

    private String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
