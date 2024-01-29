package algoritmi.av3;

import java.util.ArrayList;
import java.util.Scanner;

public class FractionalKnapsack {

    public static class Packet {
        double profit;
        double weight;

        public Packet(double profit, double weight) {
            this.profit = profit;
            this.weight = weight;
        }

        public double getProfit() {
            return profit;
        }

        public double getWeight() {
            return weight;
        }
    }

    public static double getProfit(ArrayList<Packet> packets, double capacity) {
        int i = 0;
        double profit = 0;
        while (capacity != 0) {
            if (packets.get(i).getWeight() < capacity) {
                capacity -= packets.get(i).getWeight();
                profit += packets.get(i).getProfit();
                i++;
            } else {
                double proc = capacity / packets.get(i).getWeight();
                profit += (packets.get(i).getProfit() * proc);
                capacity = 0;
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Packet> packets = new ArrayList<>();
        int n = sc.nextInt();
        double capacity;

        for (int i = 0; i < n; i++) {
            double p = sc.nextDouble();
            double w = sc.nextDouble();
            packets.add(new Packet(p, w));
        }

        capacity = sc.nextDouble();

        System.out.println(getProfit(packets, capacity));
    }
}
