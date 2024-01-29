package mk.ukim.finki.kolokviumski;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


class Square {
    int a;

    public Square(int a) {
        this.a = a;
    }

    public int perimeter() {
        return 4 * a;
    }
}

class Canvas implements Comparable<Canvas> {
    String id;
    List<Square> squares;

    public Canvas(String id, List<Square> squares) {
        this.id = id;
        this.squares = squares;
    }

    public int totalPerimeter() {
        return squares.stream().mapToInt(i -> i.perimeter()).sum();
    }

    @Override
    public int compareTo(Canvas o) {
        return Integer.compare(this.totalPerimeter(), o.totalPerimeter());
    }
}

class ShapesApplication {
    List<Canvas> canvases;

    public ShapesApplication() {
        this.canvases = new ArrayList<>();
    }

    int readCanvases(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        br.lines().forEach(l -> {
            String[] parts = l.split("\\s+");
            List<Square> squares = new ArrayList<>();
            for (int i = 1; i < parts.length; i++) {
                squares.add(new Square(Integer.parseInt(parts[i])));
            }
            canvases.add(new Canvas(parts[0], squares));
        });
        return canvases.stream().mapToInt(i -> i.squares.size()).sum();
    }

    void printLargestCanvasTo(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        Canvas max = canvases.stream().max(Comparator.naturalOrder()).get();
        pw.println(String.format("%s %d %d", max.id, max.squares.size(), max.totalPerimeter()));
        pw.flush();
        pw.close();
    }
}

public class Shapes1Test {

    public static void main(String[] args) {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}