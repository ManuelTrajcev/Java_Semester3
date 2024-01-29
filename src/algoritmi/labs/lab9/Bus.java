package algoritmi.labs.lab9;

import java.util.Scanner;
import java.util.TreeMap;

public class Bus {
    static int convertTime(String s) {
        String[] parts = s.split(":");
        return Integer.parseInt(parts[0]) * 100 + Integer.parseInt(parts[1]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int capacity = scanner.nextInt();
        int n = scanner.nextInt();
        TreeMap<Integer, Integer> times = new TreeMap<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().split("\\s+");
            int enterTime = convertTime(parts[0]);
            int exitTime = convertTime(parts[1]);
            times.put(enterTime, times.getOrDefault(enterTime, 0) + 1);
            times.put(exitTime, times.getOrDefault(exitTime, 0) - 1); // Subtract for exit time
        }
        int passengers = 0;
        boolean isPossible = true;
        for (int t : times.values()) {
            passengers += t;
            if (passengers > capacity) {
                isPossible = false;
                break;
            }
        }
        System.out.println(isPossible);
    }
}