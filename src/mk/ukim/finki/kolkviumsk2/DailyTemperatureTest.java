package mk.ukim.finki.kolkviumsk2;

import java.io.*;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

class Day {
    int dayOfYear;
    List<Double> measuresInC;
    List<Double> measuresInF;
    String scale;


    public Day(int day, String scale, List<Double> measuresAsInt) {
        this.dayOfYear = day;
        this.scale = scale;
        if (scale.equals("C")) {
            measuresInC = measuresAsInt;
            measuresInF = converted(measuresAsInt, scale);
        } else {
            measuresInF = measuresAsInt;
            measuresInC = converted(measuresAsInt, scale);
        }

    }

    private List<Double> converted(List<Double> measuresAsInt, String scale) {
        if (scale.equals("C")) {
            return measuresAsInt
                    .stream()
                    .map(i -> (i * 9) / 5 + 32)
                    .collect(Collectors.toList());
        } else {
            return measuresAsInt
                    .stream()
                    .map(i -> (i - 32) * 5 / 9)
                    .collect(Collectors.toList());
        }
    }

    public String getStats(char scale) {
        if (scale == 'C') {
            DoubleSummaryStatistics stats = measuresInC.stream().mapToDouble(Double::doubleValue).summaryStatistics();
            return String.format("%3d: Count:%4d Min: %6.2fC Max: %6.2fC Avg: %6.2fC", dayOfYear, stats.getCount(), stats.getMin(), stats.getMax(), stats.getAverage());
        } else {
            DoubleSummaryStatistics stats = measuresInF.stream().mapToDouble(Double::doubleValue).summaryStatistics();
            return String.format("%3d: Count:%4d Min: %6.2fF Max: %6.2fF Avg: %6.2fF", dayOfYear, stats.getCount(), stats.getMin(), stats.getMax(), stats.getAverage());

        }
    }
}

class DailyTemperatures {
    TreeMap<Integer, Day> measures;

    public DailyTemperatures() {
        this.measures = new TreeMap<>();
    }

    public Day dayFactory(String line) {
        List<String> parts = Arrays.stream(line.split("\\s+")).collect(Collectors.toList());
        int day = Integer.parseInt(parts.get(0));
        String scale = "";
        if (parts.get(1).charAt(parts.get(1).length() - 1) == 'C') {
            scale = "C";
        } else {
            scale = "F";
        }
        parts.remove(0);
        List<Double> measuresAsInt = parts
                .stream()
                .mapToDouble(i -> Double.parseDouble(i.substring(0, i.length() - 1)))
                .boxed().collect(Collectors.toList());

        return new Day(day, scale, measuresAsInt);
    }

    public void readTemperatures(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.lines().forEach(line -> {
            String[] parts = line.split("\\s+");
            measures.put(Integer.parseInt(parts[0]), dayFactory(line));
        });
    }

    public void writeDailyStats(OutputStream out, char scale) {
        PrintWriter pw = new PrintWriter(out);
        measures
                .values()
                .forEach(i -> pw.println(i.getStats(scale)));
        pw.flush();
//        pw.close();
    }
}

public class DailyTemperatureTest {
    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        dailyTemperatures.readTemperatures(System.in);
        System.out.println("=== Daily temperatures in Celsius (C) ===");
        dailyTemperatures.writeDailyStats(System.out, 'C');
        System.out.println("=== Daily temperatures in Fahrenheit (F) ===");
        dailyTemperatures.writeDailyStats(System.out, 'F');
    }
}
