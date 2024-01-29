package algoritmi.labs.lab1;

import java.util.Scanner;

public class PushZero {
    static void pushZerosToBeginning(int arr[], int n) {
        int[] newArr = new int[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                newArr[j++] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                newArr[j++] = arr[i];
            }
        }
        arr = newArr;
        System.out.println("Transformiranata niza e:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        pushZerosToBeginning(arr, n);
    }
}
