//package mk.ukim.finki.kolokviumski;
//
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//
//class IrregularCanvasException extends Exception {
//    public IrregularCanvasException(String message) {
//        super(message);
//    }
//}
//
//abstract class Shape implements Comparable<Shape> {
//
//    abstract double area();
//
//    @Override
//    public int compareTo(Shape o) {
//        return Double.compare(this.area(), o.area());
//    }
//}
//
//class Circle extends Shape {
//    double r;
//
//    public Circle(double r) {
//        this.r = r;
//    }
//
//    @Override
//    double area() {
//        return r * r * Math.PI;
//    }
//}
//
//class Square extends Shape {
//    double a;
//
//    public Square(double a) {
//        this.a = a;
//    }
//
//    @Override
//    double area() {
//        return a * a;
//    }
//}
//
//class Canvas {
//    String Id;
//    List<Shape> shapes;
//
//    public Canvas(String id) {
//        Id = id;
//        this.shapes = new ArrayList<>();
//    }
//
//    void addShape(String type, double a) {
//        if (type.equals("C")) {
//            shapes.add(new Circle(a));
//        } else if (type.equals("S")) {
//            shapes.add(new Square(a));
//        }
//    }
//
//    int getNumSquares() {
//        int counter = 0;
//        for (Shape s : shapes) {
//            if (s instanceof Square) {
//                counter++;
//            }
//        }
//        return counter;
//    }
//
//    int getNumCircles() {
//        int counter = 0;
//        for (Shape s : shapes) {
//            if (s instanceof Circle) {
//                counter++;
//            }
//        }
//        return counter;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(Id).append(" ").append(shapes.size()).append(" ");
//        stringBuilder.append(getNumCircles()).append(" ");
//        stringBuilder.append(getNumSquares()).append(" ");
//        shapes.sort(Comparator.reverseOrder());
//        stringBuilder.append(shapes.get(shapes.size() - 1).area()).append(" ");
//        stringBuilder.append(shapes.get(0).area()).append(" ");
//        double avg = shapes.stream().mapToDouble(i -> i.area()).average().getAsDouble();
//        stringBuilder.append(avg);
//        return String.format("%s %d %d %d %.2f %.2f %.2f", Id, shapes.size(), getNumCircles(), getNumSquares(), shapes.get(shapes.size() - 1).area(), shapes.get(0).area(), avg);
//
//    }
//}
//
//class ShapesApplication {
//    double maxArea;
//    List<Canvas> canvas;
//
//    public ShapesApplication(double maxArea) {
//        this.maxArea = maxArea;
//        this.canvas = new ArrayList<>();
//    }
//
//    void readCanvases(InputStream inputStream) throws IOException, IrregularCanvasException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//        String line;
//        while ((line = br.readLine()) != null) {
//            String[] parts = line.split("\\s+");
//            Canvas c = new Canvas(parts[0]);
//            for (int i = 1; i < parts.length; i++) {
//                String type = parts[i];
//                int a = Integer.parseInt(parts[++i]);
//                c.addShape(type, a);
//            }
//            double sum = c.shapes.stream().mapToDouble(s -> s.area()).sum();
//            if (sum > maxArea) {
//                try {
//                    throw new IrregularCanvasException(String.format("Canvas %s has a shape with area larger than %.2f", c.Id, maxArea));
//
//                } catch (IrregularCanvasException e) {
//                    System.out.println(e.getMessage());
//                }
//            } else
//                canvas.add(c);
//        }
//    }
//
//    public void printCanvases(PrintStream out) {
//        PrintWriter pws = new PrintWriter(out);
//        canvas.forEach(c -> pws.println(c));
//
//
//        pws.flush();
//        pws.close();
//    }
//}
//
//public class Shapes2Test {
//
//    public static void main(String[] args) {
//
//        ShapesApplication shapesApplication = new ShapesApplication(10000);
//
//        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
//        try {
//            shapesApplication.readCanvases(System.in);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (IrregularCanvasException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
//        shapesApplication.printCanvases(System.out);
//
//
//    }
//}