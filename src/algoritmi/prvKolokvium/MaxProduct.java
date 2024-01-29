package algoritmi.prvKolokvium;

import java.util.Scanner;

public class MaxProduct {

    public static int maxProductOf(int[] arr) {
        int max = 1;
        int[] products = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            products[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && products[i] < products[j] * arr[i]) {
                    products[i] = products[i] * arr[j];
                }
            }
            if (products[i] > max) {
                max = products[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(maxProductOf(arr));
    }
}
