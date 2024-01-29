package algoritmi.prvKolokvium;

import java.util.Scanner;

public class LIS {


    public static int longestSubSequence(int[] arr) {
        int[] tmp = new int[arr.length];
        int max = 1;
//
//        for (int i = 0; i < arr.length; i++) {
//            tmp[i] = 1;
//            for (int j = 0; j < i; j++) {
//                if (arr[i] > arr[j] && tmp[j] + 1 > tmp[i]) {
//                    tmp[i] += 1;
//                }
//            }
//            if (tmp[i] > max) {
//                max = tmp[i];
//            }
//        }

        for (int i = 0; i < arr.length; i++) {
            tmp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && tmp[j] + 1 > tmp[i]) {
                    tmp[i] += 1;
                }

            }
            if (tmp[i] > max) {
                max = tmp[i];
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

        System.out.println(longestSubSequence(arr));
    }
}
