package algoritmi.labs.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OddEvenSort {

    static void swap(int[] a, int i) {
        int tmp = a[i];
        a[i] = a[i + 1];
        a[i + 1] = tmp;
    }

    static void oddEvenSort(int a[], int n) {
        boolean swap = true;
        while (swap) {
            swap = false;
            for (int i = 0; i < n - 1; i++) {
                if (a[i] % 2 == 0 && a[i + 1] % 2 != 0) {
                    swap(a, i);
                    swap = true;
                } else if (a[i] % 2 == 0 & a[i + 1] % 2 == 0) {
                    if (a[i + 1] > a[i]) {
                        swap(a, i);
                        swap = true;
                    }
                } else if (a[i] % 2 != 0 & a[i + 1] % 2 != 0) {
                    if (a[i + 1] < a[i]) {
                        swap(a, i);
                        swap = true;
                    }
                }
            }
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
        oddEvenSort(a, n);
        for (i = 0; i < n - 1; i++)
            System.out.print(a[i] + " ");
        System.out.print(a[i]);
    }
}