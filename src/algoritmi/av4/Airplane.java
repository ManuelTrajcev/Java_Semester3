package algoritmi.av4;

import java.util.Scanner;

public class Airplane {

    public static int bestPrice(int[] prices, int[] taxes) {
        int[] minSum = new int[prices.length];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            minSum[i] = prices[i] + taxes[i];
            for (int j = 0; j < i; j++) {
                if (prices[j] + taxes[j] < minSum[j]) {
                    minSum[j] = prices[j] + taxes[j];
                }
            }
        }

        return min;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] prices = new int[n];
        int[] taxes = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
            taxes[i] = scanner.nextInt();
        }
        System.out.println(bestPrice(prices, taxes));
    }
}
