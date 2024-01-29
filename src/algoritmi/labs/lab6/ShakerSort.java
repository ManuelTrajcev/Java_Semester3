package algoritmi.labs.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ShakerSort {

    static void swap(int[] a, int i) {
        int tmp = a[i];
        a[i] = a[i + 1];
        a[i + 1] = tmp;
    }

    static void printArr(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    static void shakerSort(int[] a, int n) {
        boolean swaps = true;
        int i = 0;
        while (swaps) {
            swaps = false;
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            int max = Integer.MIN_VALUE;
            int maxIndex = 0;
            for (int j = n - 1 - i; j > i; j--) {
                if (a[j - 1] > a[j]) {
                    swap(a, j - 1);
                    swaps = true;
                }
            }
            printArr(a);
            for (int j = i; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j);
                    swaps = true;
                }
            }
            printArr(a);
            i++;
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String[] pom = s.split(" ");
        int[] a = new int[n];
        for (i = 0; i < n; i++)
            a[i] = Integer.parseInt(pom[i]);
        shakerSort(a, n);
    }
}