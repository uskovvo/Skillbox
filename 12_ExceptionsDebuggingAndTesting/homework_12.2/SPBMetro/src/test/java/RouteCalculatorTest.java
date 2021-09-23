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
    List<Station> route = new ArrayList<>();
    StationIndex stationIndex;

    @Override
    protected void setUp() throws Exception {
        Line line1 = new Line(1, "Красная");
        Line line2 = new Line(2, "Синяя");

        route.add(new Station("Чернышевская", line1));
        route.add(new Station("Площадь Восстания", line1));
        route.add(new Station("Маяковская", line2));
        route.add(new Station("Гостиный дом", line2));


    }

    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
