package mk.ukim.finki.vezbi;

// prva zadaca

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Shapes1Test {

    public static class Canvas {
        private String id;
        private ArrayList<Integer> sizes;

        public Canvas(String line) {
            String[] parts = line.split("\\s+");
            this.id = parts[0];
            this.sizes = new ArrayList<>();
            for (int i = 1; i < parts.length; i++) {
                sizes.add(Integer.parseInt(parts[i]));
            }
        }

        public int getCanvasNumbers() {
            int s = sizes.size();
            return sizes.size();
        }

        public int getCanva(int i) {
            return sizes.get(i);
        }

        public int getPerimeter() {
            int l = 0;
            for (int i = 0; i < sizes.size(); i++) {
                l += sizes.get(i);
            }

            return 4 * l;
//           return sizes.stream()
//                    .mapToInt(Canvas::getCanva)
//                    .sum();
        }

        @Override
        public String toString() {
            return id + " " + getCanvasNumbers() + " " + getPerimeter();
        }
    }

    public static class ShapesApplication {

        private ArrayList<Canvas> canvas;

        public ShapesApplication() {
            this.canvas = new ArrayList<>();
        }

        int readCanvases(InputStream inputStream) {
            Scanner scanner = new Scanner(inputStream);
            int counter = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                canvas.add(new Canvas(line));
            }
            scanner.close();
            for (int i = 0; i < canvas.size(); i++) {
                counter += canvas.get(i).getCanvasNumbers();
            }
            return counter;
//            return canvas.stream()
//                    .mapToInt(Canvas::getCanvasNumbers)
//                    .sum();
        }

        void printLargestCanvasTo(OutputStream outputStream) {
            PrintWriter pw = new PrintWriter(outputStream);
            Canvas max = canvas.get(0);
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
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

        System.out.println(shapesApplication.canvas.get(0).getPerimeter());

    }
}