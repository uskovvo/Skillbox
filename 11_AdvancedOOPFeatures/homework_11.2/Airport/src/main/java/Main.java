import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        return airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream()
                        .filter(flight -> flight.getType() == Flight.Type.DEPARTURE &&
                                LocalTime.ofInstant(flight.getDate().toInstant(), ZoneId.systemDefault())
                                .isAfter(LocalTime.now()) &&
                                LocalTime.ofInstant(flight.getDate().toInstant(), ZoneId.systemDefault())
                                .isBefore(LocalTime.now().plusHours(2)))).collect(Collectors.toList());
    }

}