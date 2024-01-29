//package mk.ukim.finki.kolkviumsk2;
//
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.TreeMap;
//
//class AirportNotFoundException extends Exception {
//    public AirportNotFoundException(String message) {
//        super(message);
//    }
//}
//
//class Airport implements Comparable<Airport>{
//    final private String airportName;
//    final private String airportCode;
//    private final Map<String, Long> flights;
//    public Airport(String name, String code) {
//        this.airportName = name;
//        this.airportCode = code;
//        this.flights = new TreeMap<>();
//    }
//
//    public void addFlight(String flightCode, long duration) {
//        flights.put(flightCode, duration);
//    }
//
//    public String getAirportName() {
//        return airportName;
//    }
//
//    public String getAirportCode() {
//        return airportCode;
//    }
//
//    public long getTotalFlightsDuration() {
//        return flights.values()
//                .stream().mapToLong(i -> i).sum();
//    }
//
//    private int countFlights() {
//        return flights.values().size();
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder str = new StringBuilder();
//        str.append(airportName).append(": ").append(airportCode);
//        flights.forEach((key, value) -> str.append(key).append(" : ").append(value).append(" min").append("\n"));
//        return str.toString();
//    }
//    //TODO SORTIRANJE
//    @Override
//    public int compareTo(Airport other) {
//        return Comparator.comparing(Airport::countFlights).reversed()
//                .thenComparing(Airport::getAirportCode)
//                .thenComparing(Airport::getAirportName).compare(this,other);
//    }
//}
//
////EntrySet - parovi key,value za celata mapa
//class Airline {
//    private final String airlineName;
//    private final Map<String, Airport> airports;
//    public Airline(String airlineName) {
//        this.airlineName = airlineName;
//        this.airports = new HashMap<>();
//    }
//
//    public void addAirport(String airportName, String code) {
//        airports.put(code, new Airport(airportName, code));
//    }
//
//    public void addFlight(String flightCodeFrom, String flightCodeTo, long duration) {
//        airports.get(flightCodeFrom).addFlight(flightCodeTo, duration);
//    }
//
//    long allFlights() {
//        return airports.values()
//                .stream().mapToLong(Airport::getTotalFlightsDuration).sum();
//    }
//
//    void search(String code) throws AirportNotFoundException {
//        if (!airports.containsKey(code)) {
//            throw new AirportNotFoundException(String.format("Airport with code %s not part of the company", code));
//        }
//        System.out.println(airports.get(code));
//    }
//
//    void printAirports() {
//        airports.values().stream().sorted().forEach(System.out::println);
//    }
//}
//
//public class AirlineTest {
//
//    public static void main(String[] args) {
//
//    }
//}
