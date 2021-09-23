import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest2 extends TestCase {
    StationIndex stationIndex;
    List<Station> connectionStation = new ArrayList<>();
    RouteCalculator routeCalculator = getRouteCalculator();
    List<Station> withoutConnection = new ArrayList<>();
    List<Station> withOneConnection = new ArrayList<>();
    List<Station> withTwoConnection = new ArrayList<>();

    public RouteCalculator getRouteCalculator(){
        createMetro();
        return new RouteCalculator(stationIndex);
    }

    @Override
    protected void setUp() throws Exception {
        Line line1 = new Line(1, "Красная");
        Line line2 = new Line(3, "Зеленая");
        Line line3 = new Line(4, "Оранжевая");

        withoutConnection.add(stationIndex.getStation("Елизаровская"));
        withoutConnection.add(stationIndex.getStation("Площадь Александра Невского 1"));
        withoutConnection.add(stationIndex.getStation("Маяковская"));

        withOneConnection.add(stationIndex.getStation("Елизаровская"));
        withOneConnection.add(stationIndex.getStation("Площадь Александра Невского 1"));
        withOneConnection.add(stationIndex.getStation("Площадь Александра Невского 2"));
        withOneConnection.add(stationIndex.getStation("Лиговский проспект"));
        withOneConnection.add(stationIndex.getStation("Достоевская"));

        withTwoConnection.add(stationIndex.getStation("Елизаровская"));
        withTwoConnection.add(stationIndex.getStation("Площадь Александра Невского 1"));
        withTwoConnection.add(stationIndex.getStation("Площадь Александра Невского 2"));
        withTwoConnection.add(stationIndex.getStation("Лиговский проспект"));
        withTwoConnection.add(stationIndex.getStation("Достоевская"));
        withTwoConnection.add(stationIndex.getStation("Владимирская"));
        withTwoConnection.add(stationIndex.getStation("Площадь Восстания"));
    }

    public void testWithoutConnection(){
        Station station1 = stationIndex.getStation("Елизаровская");
        Station station2 = stationIndex.getStation("Маяковская");
        List<Station> expected = withoutConnection;
        List<Station> actual = routeCalculator.getShortestRoute(station1, station2);
        assertEquals(expected, actual);
    }

    public void testWithOneConnection(){
        Station station1 = stationIndex.getStation("Елизаровская");
        Station station2 = stationIndex.getStation("Достоевская");
        List<Station> expected = withOneConnection;
        List<Station> actual = routeCalculator.getShortestRoute(station1, station2);
        assertEquals(expected, actual);
    }

    public void testWithTwoConnection(){
        Station station = stationIndex.getStation("Елизаровская");
        Station station1 = stationIndex.getStation("Площадь Восстания");
        List<Station> expected = withTwoConnection;
        List<Station> actual = routeCalculator.getShortestRoute(station, station1);
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }

    private void createMetro(){
        stationIndex = new StationIndex();
        connectionStation = new ArrayList<>();
        Line redLine = new Line(1, "Красная");
        Line greenLine = new Line(3, "Зеленая");
        Line orangeLine = new Line(4, "Оранжевая");

        Station elizarovskaya = new Station("Елизаровская", greenLine);
        Station alexanderNevskySquare1 = new Station("Площадь Александра Невского 1", greenLine);
        Station mayakovsky = new Station("Маяковская", greenLine);

        Station alexanderNevskySquare2 = new Station("Площадь Александра Невского 2", orangeLine);
        Station ligovskyAvenue = new Station("Лиговский проспект", orangeLine);
        Station dostoevskaya = new Station("Достоевская", orangeLine);

        Station vladimirskaya = new Station("Владимирская", redLine);
        Station vosstaniyaSquare = new Station("Площадь Восстания", redLine);

        redLine.addStation(vladimirskaya);
        redLine.addStation(vosstaniyaSquare);
        greenLine.addStation(elizarovskaya);
        greenLine.addStation(alexanderNevskySquare1);
        greenLine.addStation(mayakovsky);
        orangeLine.addStation(alexanderNevskySquare2);
        orangeLine.addStation(ligovskyAvenue);
        orangeLine.addStation(dostoevskaya);

        stationIndex.addStation(elizarovskaya);
        stationIndex.addStation(alexanderNevskySquare1);
        stationIndex.addStation(mayakovsky);
        stationIndex.addStation(alexanderNevskySquare2);
        stationIndex.addStation(ligovskyAvenue);
        stationIndex.addStation(dostoevskaya);
        stationIndex.addStation(vladimirskaya);
        stationIndex.addStation(vosstaniyaSquare);

        stationIndex.addLine(redLine);
        stationIndex.addLine(greenLine);
        stationIndex.addLine(orangeLine);

        connectionStation.add(alexanderNevskySquare1);
        connectionStation.add(alexanderNevskySquare2);
        connectionStation.add(alexanderNevskySquare2);
        connectionStation.add(vladimirskaya);
        connectionStation.add(vosstaniyaSquare);
        connectionStation.add(mayakovsky);

        stationIndex.addConnection(connectionStation);
    }
}
