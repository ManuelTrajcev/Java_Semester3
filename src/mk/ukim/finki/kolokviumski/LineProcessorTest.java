package mk.ukim.finki.kolokviumski;


import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class LineP implements Comparable<LineP> {
    String line;
    char c;

    public LineP(String line, char c) {
        this.line = line;
        this.c = c;
    }

    private int counC() {
        int count = 0;
        String tmp = line.toLowerCase();
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int compareTo(LineP o) {
        return Integer.compare(this.counC(), o.counC());
    }

    @Override
    public String toString() {
        return line;
    }
}

class LineProcessor {
    List<String> lines;

    public LineProcessor() {
        this.lines = new ArrayList<>();
    }

    private int counC(String line, char c) {
        int count = 0;
        String tmp = line.toLowerCase();
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    void readLines(InputStream inputStream, OutputStream outputStream, char c) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter pw = new PrintWriter(outputStream);
        lines = br.lines().collect(Collectors.toList());

        Optional<String> max = lines.stream().max((o1, o2) -> Integer.compare(counC(o1, c), counC(o2, c)));
        pw.println(max.orElseGet(() -> max.orElse("")));

        pw.flush();
    }
}

public class LineProcessorTest {
    public static void main(String[] args) {
        LineProcessor lineProcessor = new LineProcessor();
        try {
            lineProcessor.readLines(System.in, System.out, 'a');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
