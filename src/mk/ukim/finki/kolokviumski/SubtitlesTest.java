package mk.ukim.finki.kolokviumski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Subtitle {
    int n;
    LocalTime start;
    LocalTime end;
    String text;

    public Subtitle(int n, String time, String text) {
        this.n = n;
        String[] times = time.split("\\s+");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
        this.start = LocalTime.parse(times[0], dtf);
        this.end = LocalTime.parse(times[2], dtf);
        this.text = text;
    }

    @Override
    public String toString() {
        String starting = start.toString().replaceAll("\\.", ",");
        String ending = end.toString().replaceAll("\\.", ",");
        if (starting.length() < 10) {
            starting += ",000";
        }
        if (ending.length() < 10) {
            ending += ",000";
        }
        return String.format("%d\n%s --> %s\n%s\n", n, starting, ending, text);
    }

    public void setStart(int ms) {
        start = start.plusNanos(ms);
    }

    public void setEnd(int ms) {
        end = end.plusNanos(ms);
    }
}

class Subtitles {
    List<Subtitle> subtitles;

    public Subtitles() {
        subtitles = new ArrayList<>();
    }


    int loadSubtitles(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = br.readLine()) != null) {
            int n = Integer.parseInt(line);
            String time = br.readLine();
            String text = br.readLine();
            line = br.readLine();
            if (line == null) {
                subtitles.add(new Subtitle(n, time, text));
                break;
            }
            while (!line.equals("")) {
                text = text + "\n" + line;
                if ((line = br.readLine()) == null) {
                    break;
                }
            }
            subtitles.add(new Subtitle(n, time, text));
        }
        return subtitles.size();
    }

    public void print() {
        subtitles.forEach(System.out::println);
    }

    public void shiftSub(Subtitle subtitle, int ms) {
        subtitle.setStart(ms * 1000000);
        subtitle.setEnd(ms * 1000000);
    }

    public void shift(int shift) {
        subtitles.forEach(subtitle -> shiftSub(subtitle, shift));
    }
}

public class SubtitlesTest {
    public static void main(String[] args) {
        Subtitles subtitles = new Subtitles();
        int n = 0;
        try {
            n = subtitles.loadSubtitles(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("+++++ ORIGINIAL SUBTITLES +++++");
        subtitles.print();
        int shift = n * 37;
        shift = (shift % 2 == 1) ? -shift : shift;
        System.out.println(String.format("SHIFT FOR %d ms", shift));
        subtitles.shift(shift);
        System.out.println("+++++ SHIFTED SUBTITLES +++++");
        subtitles.print();
    }
}