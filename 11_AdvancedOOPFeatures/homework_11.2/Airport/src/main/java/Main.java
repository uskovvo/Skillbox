import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {


    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        airport = Airport.getInstance();
        airport.getTerminals().stream().map(Terminal::getFlights);
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        return Collections.emptyList();
    }

}