package mk.ukim.finki.kolkviumsk2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

class WrongDateException extends Exception {
    public WrongDateException(String message) {
        super(message);
    }
}

class Event implements Comparable<Event> {
    String name;
    String location;
    Date date;

    public Event(String name, String location, Date date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        DateFormat dtf = new SimpleDateFormat("dd MMM, YYYY HH:mm");
        return String.format("%s at %s, %s", dtf.format(date), location, name);
    }

    @Override
    public int compareTo(Event o) {
        return this.date.compareTo(o.date);
    }

    int getMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int m = calendar.get(Calendar.MONTH);
        return m;
    }
}

class EventCalendar {
    final int year;
    final List<Event> events;

    public EventCalendar(int year) {
        this.year = year;
        this.events = new ArrayList<>();
    }

    public void addEvent(String name, String location, Date date) throws WrongDateException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) != year) throw new WrongDateException(String.format("Wrong date: %s", date));

        events.add(new Event(name, location, date));

        events.sort(Comparator.comparing(Event::getDate).thenComparing(Event::getName));
    }

    public void listEvents(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int n = (int) events.stream().filter(event -> {
            Calendar curr = Calendar.getInstance();
            curr.setTime(event.date);
            return cal.get(Calendar.DAY_OF_YEAR) == curr.get(Calendar.DAY_OF_YEAR);
        }).count();
        if (n == 0) {
            System.out.println("No events on this day!");
        } else
            events.stream().filter(event -> {
                        Calendar curr = Calendar.getInstance();
                        curr.setTime(event.date);
                        return cal.get(Calendar.DAY_OF_YEAR) == curr.get(Calendar.DAY_OF_YEAR);
                    })
                    .forEach(System.out::println);
    }

    public void listByMonth() {
        for (int i = 0; i < 12; i++) {
            int finalI = i;
            int n = (int) events.stream().filter(e -> e.getMonth() == finalI).count();
            System.out.println(String.format("%d : %d", finalI + 1, n));
        }

    }
}

public class EventCalendarTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int year = scanner.nextInt();
        scanner.nextLine();
        EventCalendar eventCalendar = new EventCalendar(year);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            String name = parts[0];
            String location = parts[1];
            Date date = df.parse(parts[2]);
            try {
                eventCalendar.addEvent(name, location, date);
            } catch (WrongDateException e) {
                System.out.println(e.getMessage());
            }
        }
        Date date = df.parse(scanner.nextLine());
        eventCalendar.listEvents(date);
        eventCalendar.listByMonth();
    }
}

// vashiot kod ovde