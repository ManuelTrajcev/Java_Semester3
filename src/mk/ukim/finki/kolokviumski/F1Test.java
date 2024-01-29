package mk.ukim.finki.kolokviumski;


import java.io.*;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

class Lap implements Comparable<Lap> {
    String time;
    int valueInMs;

    public Lap(String t) {
        time = t;
        String[] parts = t.split(":");
        valueInMs = Integer.parseInt(parts[0]) * 60 * 1000 + Integer.parseInt(parts[1]) * 1000 + Integer.parseInt(parts[2]);
    }

    @Override
    public String toString() {
        return time;
    }

    @Override
    public int compareTo(Lap o) {
        return Integer.compare(this.valueInMs, o.valueInMs);
    }
}

class Driver implements Comparable<Driver> {
    String name;
    List<Lap> laps;

    public Driver(String name, List<Lap> laps) {
        this.name = name;
        this.laps = laps;
    }

    @Override
    public int compareTo(Driver o) {
        // Compare drivers based on their first lap time
        return this.laps.get(0).compareTo(o.laps.get(0));
    }

    @Override
    public String toString() {
        return String.format("%-11s %s", name, laps.get(0));
    }
}

class F1Race {
    List<Driver> drivers;

    public F1Race() {
        drivers = new ArrayList<>();
    }

    public Driver driverFactory(String line) {
        String[] parts = line.split("\\s+");
        String name = parts[0];
        List<Lap> laps = Arrays.stream(parts).skip(1).map(Lap::new).collect(Collectors.toList());
        laps = laps.stream().sorted().collect(Collectors.toList());
        return new Driver(name, laps);
    }

    void readResults(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        br.lines().forEach(line -> drivers.add(driverFactory(line)));
    }

    void printSorted(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        drivers = drivers.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < drivers.size(); i++) {
            pw.println(String.format("%d. %s", i + 1, drivers.get(i)));
        }
        pw.flush();
        pw.close();
    }
}

public class F1Test {
    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }
}
