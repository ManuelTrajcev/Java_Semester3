package mk.ukim.finki.av5;

import java.awt.*;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BoxTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = Integer.parseInt(scanner.nextLine());

        Box<Circle> circleBox = new Box<>();
        Box<Triangle> triangleBox = new Box<>();
        IntStream.range(0, 3).forEach(i -> circleBox.addElement(new Circle()));
//        IntStream.range(0, 4).forEach(i -> triangleBox.addElement(new Triangle()));

        if (option == 1) {
            System.out.println("---test isEmpty()---");
            System.out.println(circleBox.isEmpty());
            System.out.println(triangleBox.isEmpty());
        } else if (option == 2) {
            System.out.println("---test getItemsFromBox()");
            System.out.println(circleBox.getIemFromBox());
            System.out.println(triangleBox.getIemFromBox());
        } else if (option == 3) {
            System.out.println("---test drawBox()---");
            try {
                circleBox.drawBox();
                triangleBox.drawBox();
            } catch (EmptyBoxException exception) {
                System.out.println(exception.getMessage());
            }

        }

    }
}
