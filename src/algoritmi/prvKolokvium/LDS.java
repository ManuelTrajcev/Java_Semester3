package algoritmi.prvKolokvium;

import java.util.Scanner;

public class LDS {


//    private static int najdolgaOpagackaSekvenca(int[] a) {
//        int n = a.length;
//        int[] tmp = new int[n];
//        int max = 1;
//
//        for (int i = 0; i < n; i++) {
//            tmp[i] = 1;
//            for (int j = 0; j < i; j++) {
//                if (a[i] < a[j] && tmp[i] < tmp[j] + 1) {
//                    tmp[i] = tmp[j] + 1;
//                }
//            }
//            max = Math.max(max, tmp[i]);
//        }
//
//        return max;
//    }

    private static int najdolgaOpagackaSekvenca(int[] a) {
        int max = 1;
        int[] tmp = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            tmp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j] && tmp[i] < tmp[j] + 1) {
                    tmp[i] = tmp[j] + 1;
                }
            }
            max = Math.max(max, tmp[i]);
        }


        return max;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}