package mk.ukim.finki.kolokviumski;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;


class Measurment implements Comparable<Measurment> {
    double temperature;
    double wind;
    double humidity;

    double visibility;
    LocalDateTime time;

    public Measurment(double temperature, double wind, double humidity, double visibility, LocalDateTime time) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.visibility = visibility;
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss", Locale.ENGLISH);
        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s GMT %s", temperature, wind, humidity, visibility, time.format(dtf), time.getYear());
    }

    @Override
    public int compareTo(Measurment o) {
        if (this.time.isBefore(o.time))
            return -1;
        if (this.time.isAfter(o.time))
            return 1;
        else
            return 0;
    }
}

class WeatherStation {
    int days;
    List<Measurment> measurments;

    public WeatherStation(int days) {
        this.days = days;
        this.measurments = new ArrayList<>();
    }

    public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date) {
        LocalDateTime localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        measurments.add(new Measurment(temperature, wind, humidity, visibility, localDate));
        boolean dontAdd = false;

        for (Measurment m : measurments) {
            Duration d = Duration.between(localDate, m.time);
            long duration = Math.abs(d.toSeconds());
            if (duration <= 150) {
                dontAdd = true;
                break;
            }
        }
        if (!dontAdd) {
            LocalDateTime before = localDate.minusDays(days);
            measurments = measurments.stream()
                    .filter(m -> m.time.isAfter(before)).collect(Collectors.toList());
            measurments =  measurments.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        }
    }

    public int total() {
        return measurments.size();
    }

    public void status(Date from, Date to) {
        LocalDateTime fromFormatted = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime toFormatted = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        List<Measurment> filtered = measurments.stream().filter(measurment -> !measurment.time.isAfter(toFormatted) && !measurment.time.isBefore(fromFormatted)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new RuntimeException();
        } else {
            filtered.forEach(System.out::println);
        }
        double avg = filtered.stream().mapToDouble(i->i.temperature).average().getAsDouble();
        System.out.printf("Average temperature: %.2f",avg);
    }
}

public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
        WeatherStation ws = new WeatherStation(n);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("=====")) {
                break;
            }
            String[] parts = line.split(" ");
            float temp = Float.parseFloat(parts[0]);
            float wind = Float.parseFloat(parts[1]);
            float hum = Float.parseFloat(parts[2]);
            float vis = Float.parseFloat(parts[3]);
            line = scanner.nextLine();
            Date date = df.parse(line);
            ws.addMeasurment(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        Date from = df.parse(line);
        line = scanner.nextLine();
        Date to = df.parse(line);
        scanner.close();
        System.out.println(ws.total());
        try {
            ws.status(from, to);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}

// vashiot kod ovde