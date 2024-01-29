package algoritmi.av5;
//Raspored Ispit

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class queue1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        Queue<String> eTest = new PriorityQueue<>();
        Queue<String> exercises = new PriorityQueue<>();
        Queue<String> both = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            eTest.add(scanner.nextLine());
        }

        for (int i = 0; i < n; i++) {
            exercises.add(scanner.nextLine());
        }
        for (int i = 0; i < n; i++) {
            both.add(scanner.nextLine());
        }

        int c = 20;
        int termin = 2;

        System.out.println("Polagaat E-Test:");
        System.out.println("Termin 1:");
        while (!eTest.isEmpty()) {
            System.out.println(eTest.peek());
            eTest.remove();
            c--;
            if (c == 0) {
                termin++;
                c = 20;
                System.out.println("Termin " + termin + ":");
            }
        }

        while (!both.isEmpty()) {
            System.out.println(both.peek());
            exercises.add(both.peek());
            both.remove();
            c--;
            if (c == 0) {
                termin++;
                c = 20;
                System.out.println("Termin " + termin + ":");            }
        }

        while (!exercises.isEmpty()) {
            System.out.println(exercises.peek());
            exercises.remove();
            c--;
            if (c == 0) {
                termin++;
                c = 20;
                System.out.println("Termin " + termin + ":");
            }
        }

    }
}
