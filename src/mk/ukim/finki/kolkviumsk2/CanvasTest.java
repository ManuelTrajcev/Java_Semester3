package mk.ukim.finki.kolkviumsk2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class InvalidIDException extends Exception {
    public InvalidIDException(String message) {
        super(message);
    }
}

class InvalidDimensionException extends Exception {
    public InvalidDimensionException(String message) {
        super(message);
    }
}

abstract class Shape implements Comparable<Shape> {
    double size;

    public Shape(double size) {
        this.size = size;
    }

    public abstract double area();

    public abstract double perimeter();

    @Override
    public int compareTo(Shape o) {
        return Double.compare(this.area(), o.area());
    }

    public void scale(double coef) {
        size *= coef;
    }
}

class Circle extends Shape {
    public Circle(double size) {
        super(size);
    }

    @Override
    public double area() {
        return size * size * Math.PI;
    }

    @Override
    public String toString() {
        return String.format("Circle -> Radius: %.2f Area: %.2f Perimeter: %.2f", size, area(), perimeter());
    }

    public double perimeter() {
        return 2 * size * Math.PI;
    }
}

class Rectangle extends Shape {
    double b;

    public Rectangle(double size, double b) {
        super(size);
        this.b = b;
    }

    @Override
    public double area() {
        return size * b;
    }

    public double perimeter() {
        return 2 * size + 2 * b;
    }

    public String toString() {
        return String.format("Rectangle: -> Sides: %.2f, %.2f Area: %.2f Perimeter: %.2f", size, b, area(), perimeter());
    }

    @Override
    public void scale(double coef) {
        super.scale(coef);
        b *= coef;
    }
}

class Square extends Shape {
    public Square(double size) {
        super(size);
    }

    @Override
    public double area() {
        return size * size;
    }

    public double perimeter() {
        return 4 * size;
    }

    @Override
    public String toString() {
        return String.format("Square: -> Side: %.2f Area: %.2f Perimeter: %.2f", size, area(), perimeter());
    }
}

class Canvas {
    HashMap<String, List<Shape>> map;

    public Canvas() {
        this.map = new HashMap<>();
    }

    private Shape shapeFactory(String l) throws InvalidDimensionException {
        String[] parts = l.split("\\s+");
        if (Double.parseDouble(parts[2]) == 0) {
            throw new InvalidDimensionException("Dimension 0 is not allowed!");
        }
        switch (parts[0]) {
            case "1":
                return new Circle(Double.parseDouble(parts[2]));
            case "2":
                return new Square(Double.parseDouble(parts[2]));
            case "3":
                if (Double.parseDouble(parts[3]) == 0) {
                    throw new InvalidDimensionException("Dimension 0 is not allowed!");
                }
                return new Rectangle(Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
        }
        return null;
    }

    public void readShapes(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        br.lines().forEachOrdered(l -> {
            boolean idOk = true;
            boolean shapeOk = true;
            String[] parts = l.split("\\s+");
            try {
                checkId(parts[1]);
            } catch (InvalidIDException e) {
                idOk = false;
                System.out.println(e.getMessage());
            }
            Shape newShape = null;
            try {
                newShape = shapeFactory(l);
            } catch (InvalidDimensionException e) {
                shapeOk = false;
                System.out.println(e.getMessage());
            }
            if (!shapeOk) return;
            if (idOk) {
                if (map.containsKey(parts[1])) {
                    map.get(parts[1]).add(newShape);
                } else {
                    ArrayList<Shape> tmp = new ArrayList<>();
                    tmp.add(newShape);
                    map.put(parts[1], tmp);
                }
            }
        });
        map.values().stream().forEach(i -> i.sort(Comparator.naturalOrder()));
    }

    private void checkId(String id) throws InvalidIDException {
        if (id.length() != 6) {
            throw new InvalidIDException(String.format("ID %s is not valid", id));
        }
        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i)) && !Character.isAlphabetic(id.charAt(i))) {
                throw new InvalidIDException(String.format("ID %s is not valid", id));

            }
        }
    }

    public void printAllShapes(OutputStream out) {
        List<Shape> sorted = new ArrayList<>();
        map.values().forEach(sorted::addAll);
        Collections.sort(sorted);
        sorted.forEach(System.out::println);
//        map.entrySet().forEach(i -> System.out.println(i.getValue()));
    }

    public void scaleShapes(String userId, double coef) {
        map.get(userId).forEach(i -> i.scale(coef));
    }

    @SuppressWarnings("unchecked")
    public void printByUserId(PrintStream out) {
        map.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(e -> ((Map.Entry<String, List<Shape>>) e).getValue().size()).reversed().thenComparing(e -> ((Map.Entry<String, List<Shape>>) e).getValue().stream().mapToDouble(Shape::area).sum()))
                .forEach(i -> {
                    System.out.printf("Shapes of user: %s\n", i.getKey());
                    i.getValue().forEach(System.out::println);
                });
    }

    public void statistics(OutputStream out) {
        DoubleSummaryStatistics dss = map.values()
                .stream()
                .flatMap(List::stream)
                .mapToDouble(Shape::area)
                .summaryStatistics();
        System.out.printf("count: %d\n", dss.getCount());
        System.out.printf("sum: %.2f\n", dss.getSum());
        System.out.printf("min: %.2f\n", dss.getMin());
        System.out.printf("average: %.2f\n", dss.getAverage());
        System.out.printf("max: %.2f\n", dss.getMax());
    }
}

public class CanvasTest {

    public static void main(String[] args) {
        Canvas canvas = new Canvas();

        System.out.println("READ SHAPES AND EXCEPTIONS TESTING");
        canvas.readShapes(System.in);

        System.out.println("BEFORE SCALING");
        canvas.printAllShapes(System.out);
        canvas.scaleShapes("123456", 1.5);
        System.out.println("AFTER SCALING");
        canvas.printAllShapes(System.out);

        System.out.println("PRINT BY USER ID TESTING");
        canvas.printByUserId(System.out);

        System.out.println("PRINT STATISTICS");
        canvas.statistics(System.out);
    }
}