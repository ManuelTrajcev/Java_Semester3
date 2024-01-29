package mk.ukim.finki.kolokviumski;

import java.io.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


class UnsupportedFormatException extends Exception {
    public UnsupportedFormatException(String message) {
        super(message);
    }
}

class InvalidTimeException extends Exception {
    public InvalidTimeException(String message) {
        super(message);
    }
}

class Times implements Comparable<Times> {
    int hours;
    int minutes;

    public Times(String line) throws UnsupportedFormatException, InvalidTimeException {
        if (!matches(line)) {
            throw new UnsupportedFormatException(String.format("UnsupportedFormatException: %s", line));
        }

        String[] parts = line.split("[:.]");
        if (Integer.parseInt(parts[0]) < 0 || Integer.parseInt(parts[0]) > 23 || Integer.parseInt(parts[1]) < 0 || Integer.parseInt(parts[1]) > 59) {
            throw new InvalidTimeException("Invalid time");
        }
        this.hours = Integer.parseInt(parts[0]);
        this.minutes = Integer.parseInt(parts[1]);
    }

    private boolean matches(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != '0' && line.charAt(i) != '1' && line.charAt(i) != '2' && line.charAt(i) != '3' && line.charAt(i) != '4' && line.charAt(i) != '5' && line.charAt(i) != '6' && line.charAt(i) != '7' && line.charAt(i) != '8' && line.charAt(i) != '9' && line.charAt(i) != '.' && line.charAt(i) != ':') {
                return false;
            }
        }
        return true;
    }

    public String Format24() {
        return String.format("%2d:%02d", hours, minutes);
    }

    public String FormatAMPM() {
        return String.format("%2d:%02d %s", hours == 0 ? 12 : hours == 12 ? 12 : hours % 12, minutes, hours >= 12 ? "PM" : "AM");
    }

    @Override
    public int compareTo(Times o) {
        return Integer.compare(this.hours * 60 + this.minutes, o.hours * 60 + o.minutes);
    }
}

class TimeTable {
    List<Times> times;

    public TimeTable() {
        this.times = new ArrayList<>();
    }

    void readTimes(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        br.lines().forEach(t -> {
            if (t.contains(" ")) {
                String[] parts = t.split("\\s+");
                for (String p : parts) {
                    try {
                        times.add(new Times(p));
                    } catch (UnsupportedFormatException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidTimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else {
                try {
                    times.add(new Times(t));
                } catch (UnsupportedFormatException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidTimeException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        Collections.sort(times);
    }

    public void writeTimes(PrintStream out, TimeFormat timeFormat) {
        PrintWriter pw = new PrintWriter(out);
        if (timeFormat.equals(TimeFormat.FORMAT_24)) {
            times.forEach(t -> pw.println(t.Format24()));
        } else {
            times.forEach(t -> pw.println(t.FormatAMPM()));
        }
        pw.flush();
    }


}

public class TimesTest {

    public static void main(String[] args) {
        TimeTable timeTable = new TimeTable();
        timeTable.readTimes(System.in);
        System.out.println("24 HOUR FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
        System.out.println("AM/PM FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
    }

}

enum TimeFormat {
    FORMAT_24, FORMAT_AMPM
}