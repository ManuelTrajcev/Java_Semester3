package mk.ukim.finki.vezbi;

// zadac 2


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Shapes2Test {

    public class IrregularCanvasException extends Exception {
        public IrregularCanvasException() {
            super("");
        }
    }

    public static abstract class Shape {
        protected int a;

        public Shape(int a) {
            this.a = a;
        }

        public abstract double area();

        public int getValue(){
            return a;
        }
    }

    public static class Circle extends Shape {
        public Circle(int a) {
            super(a);
        }

        @Override
        public double area() {
            return a*a*3.14;
        }
    }

    public static class Square extends Shape {
        public Square(int a) {
            super(a);
        }

        @Override
        public double area() {
            return a*a;
        }
    }

    public static class Canvas {

        private String id;
        private ArrayList<Shape> sizes;

        public Canvas(String line) {
            String[] parts = line.split("\\s+");
            this.id = parts[0];
            String type = parts[1];
            this.sizes = new ArrayList<>();
            for (int i = 2; i < parts.length; i++) {
                if (type.equals("C")) {
                    Circle c = new Circle(Integer.parseInt(parts[i]));
                    sizes.add(c);
                } else {
                    Square s = new Square(Integer.parseInt(parts[i]));
                    sizes.add(s);
                }
            }
        }

        public int getCanvasNumbers() {
            int s = sizes.size();
            return sizes.size();
        }

        public int getCanva(int i) {
            return  sizes.get(i).getValue();
        }

        public int getPerimeter() {
            int l = 0;
            for (int i = 0; i < sizes.size(); i++) {
                l += sizes.get(i).getValue();
            }

            return 4 * l;
        }

        @Override
        public String toString() {
            return id + " " + getCanvasNumbers() + " " + getPerimeter();
        }


    }


    public static class ShapesApplication {
        private double maxArea;

        private ArrayList<Shapes1Test.Canvas> canvas;

        public ShapesApplication(double maxArea) {
            this.maxArea = maxArea;
            this.canvas = new ArrayList<>();
        }

        int readCanvases(InputStream inputStream) {
            Scanner scanner = new Scanner(inputStream);
            int counter = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                canvas.add(new Shapes1Test.Canvas(line));
            }
            scanner.close();
            for (int i = 0; i < canvas.size(); i++) {
                counter += canvas.get(i).getCanvasNumbers();
            }
            return counter;

        }

        void printLargestCanvasTo(OutputStream outputStream) {
            PrintWriter pw = new PrintWriter(outputStream);
            Shapes1Test.Canvas max = canvas.get(0);
            for (int i = 1; i < canvas.size(); i++) {
                if (canvas.get(i).getPerimeter() > max.getPerimeter()) {
                    max = canvas.get(i);
                }
            }
            pw.println(max);
            pw.flush();
            pw.close();
        }

    }


    public static void main(String[] args) {

        ShapesApplication shapesApplication = new ShapesApplication(10000);

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
        shapesApplication.readCanvases(System.in);

        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
//        shapesApplication.printCanvases(System.out);


    }
}
