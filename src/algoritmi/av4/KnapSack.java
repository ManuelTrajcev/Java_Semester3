package algoritmi.av4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class KnapSack {
    public static class Item {
        int profit;
        int weight;

        public Item(int profit, int weight) {
            this.profit = profit;
            this.weight = weight;
        }

        public int getProfit() {
            return profit;
        }

        public void setProfit(int profit) {
            this.profit = profit;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "profit=" + profit +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static int maxProfit(ArrayList<Item> items, int c) {
        int[][] tmp = new int[items.size() + 1][c + 1];
        for (int i = 0; i <= items.size(); i++) {
            tmp[i][0] = 0;
        }
        for (int i = 0; i <= c; i++) {
            tmp[0][i] = 0;
        }

        for (int i = 1; i <= items.size(); i++) {
            for (int j = 1; j <= c; j++) {
                if (j >= items.get(i - 1).getWeight()) {
                    tmp[i][j] = Math.max(items.get(i - 1).getProfit() + tmp[i - 1][j - items.get(i - 1).getWeight()], tmp[i - 1][j]);
                } else {
                    tmp[i][j] = tmp[i - 1][j];
                }
            }
        }

        return tmp[items.size()][c];
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Item> items = new ArrayList<>();
        int c;

        for (int i = 0; i < n; i++) {
            items.add(new Item(scanner.nextInt(), scanner.nextInt()));
        }

        c = scanner.nextInt();

//        items.forEach(System.out::println);

        System.out.println(maxProfit(items, c));
    }
}
