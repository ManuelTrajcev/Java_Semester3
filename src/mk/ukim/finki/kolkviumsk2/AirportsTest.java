package mk.ukim.finki.kolkviumsk2;

import java.util.*;
import java.util.stream.Collectors;

class Flight implements Comparable<Flight> {
    String from;
    String to;
    int time;
    int duration;

    public Flight(String from, String to, int time, int duration) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration;
    }

    @Override
    public int compareTo(Flight o) {
        int toComp = this.to.compareTo(o.to);
        if (toComp == 0) {
            return Integer.compare(this.time, o.time);
        } else {
            return toComp;
        }
    }

    @Override
    public String toString() {
        return String.format("%s-%s %s %s", from, to, convertedTime(), calculatedDuration());
    }

    private String calculatedDuration() {
        int h = duration / 60;
        int m = duration % 60;
        int d = h / 24;

        StringBuilder str = new StringBuilder();
        if (d != 0) {
            str.append("+").append(d).append("d ");
        }
        str.append(String.format("%dh%02dm", h, m));
        return str.toString();
    }

    private String convertedTime() {
        int m = time % 60;
        int h = time / 60;
        h %= 24;

        int arrivalTime = time + duration;
        int am = arrivalTime % 60;
        int ah = arrivalTime / 60;
        ah %= 24;

        return String.format("%02d:%02d-%02d:%02d", h, m, ah, am);
    }
}

class Airport {
    String name;
    String country;
    String code;
    int passengers;
    TreeSet<Flight> from;
    TreeSet<Flight> to;

    public Airport(String name, String country, String code, int passengers) {
        this.name = name;
        this.country = country;
        this.code = code;
        this.passengers = passengers;
        from = new TreeSet<>();
        to = new TreeSet<>();
    }

    @Override
    public String toString() {
        return String.format("%s (%s)\n%s\n%d", name, code, country, passengers);
    }
}

class Airports {
    Map<String, Airport> airports;

    Set<Flight> flights;

    public Airports() {
        this.flights = new TreeSet<>();
        this.airports = new HashMap<>();
    }

    public void addAirport(String name, String country, String code, int passengers) {
        airports.put(code, new Airport(name, country, code, passengers));
    }

    public void addFlights(String from, String to, int time, int duration) {
        Flight currFlight = new Flight(from, to, time, duration);
        flights.add(currFlight);
        airports.get(from).from.add(currFlight);
        airports.get(to).to.add(currFlight);
    }

    public void showFlightsFromAirport(String code) {
        System.out.println(airports.get(code));
        List<Flight> filtered = airports.get(code)
                .from
                .stream()
                .collect(Collectors.toList());
        for (int i = 1; i <= filtered.size(); i++) {
            System.out.println(i + ". " + filtered.get(i - 1));
        }
    }

    public void showDirectFlightsFromTo(String from, String to) {
        List<Flight> directFlights = flights
                .stream()
                .filter(i -> i.from.equals(from) && i.to.equals(to))
                .collect(Collectors.toList());
        if (directFlights.isEmpty()) {
            System.out.printf("No flights from %s to %s\n", from, to);
        } else {
            directFlights.forEach(System.out::println);
        }
    }

    public void showDirectFlightsTo(String to) {
        List<Flight> filtered = airports.get(to)
                .to
                .stream()
                .collect(Collectors.toList());

        filtered.forEach(System.out::println);
    }
}

public class AirportsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Airports airports = new Airports();
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] codes = new String[n];
        for (int i = 0; i < n; ++i) {
            String al = scanner.nextLine();
            String[] parts = al.split(";");
            airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
            codes[i] = parts[2];
        }
        int nn = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nn; ++i) {
            String fl = scanner.nextLine();
            String[] parts = fl.split(";");
            airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        int f = scanner.nextInt();
        int t = scanner.nextInt();
        String from = codes[f];
        String to = codes[t];
        System.out.printf("===== FLIGHTS FROM %S =====\n", from);
        airports.showFlightsFromAirport(from);
        System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
        airports.showDirectFlightsFromTo(from, to);
        t += 5;
        t = t % n;
        to = codes[t];
        System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
        airports.showDirectFlightsTo(to);
    }
}

// vashiot kod ovde

