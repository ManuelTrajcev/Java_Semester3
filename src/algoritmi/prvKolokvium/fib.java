package algoritmi.prvKolokvium;

import java.util.Scanner;

public class fib {


    public static int fib(int n, int[] memo) {
        if (memo[n] != 0) {
            return memo[n];
        }
        int result = 1;
        if (n == 1 || n == 2) {
            result = 1;
        } else {
            result = fib(n - 1, memo) + fib(n - 2, memo);
        }
        memo[n] = result;
        return result;
    }

    public static int fibBottomUp(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            int[] bUp = new int[n + 1];
            bUp[1] = 1;
            bUp[2] = 1;
            for (int i = 3; i < n + 1; i++) {
                bUp[i] = bUp[i - 1] + bUp[i - 2];
            }
            return bUp[n];
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] memo = new int[n + 1];

//        System.out.println(fib(n, memo));

        System.out.println(fibBottomUp(n));
    }
}
