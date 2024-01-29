package algoritmi.av4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Mars {

    static int maxSum(int[][] dp) {
        int[][] tmp = new int[dp.length][dp[0].length];

        // Initialize the first row and column of tmp
        tmp[0][0] = dp[0][0];
//        for (int i = 1; i < dp.length; i++) {
//            tmp[i][0] = tmp[i-1][0] + dp[i][0];
//        }
//        for (int j = 1; j < dp[0].length; j++) {
//            tmp[0][j] = tmp[0][j-1] + dp[0][j];
//        }
//
//        // Fill the rest of tmp
//        for (int i = 1; i < dp.length; i++) {
//            for (int j = 1; j < dp[0].length; j++) {
//                tmp[i][j] = Math.max(tmp[i-1][j], tmp[i][j-1]) + dp[i][j];
//            }
//        }
        for (int i = 1; i < dp.length; i++) {
            tmp[i][0] = tmp[i-1][0] + dp[i][0];
        }
        for (int i = 1; i < dp[0].length; i++) {
            tmp[0][i] = dp[0][i] + tmp[0][i-1];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                tmp[i][j] = Math.max(tmp[i-1][j], tmp[i][j-1]) + dp[i][j];
            }
        }

        return tmp[dp.length-1][dp[0].length-1];
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = scanner.nextInt();
        int j = scanner.nextInt();
        int[][] dp = new int[i][j];
        for (int k = 0; k < i; k++) {
            for (int l = 0; l < j; l++) {
                dp[k][l] = scanner.nextInt();
            }
        }
        System.out.println(maxSum(dp));
    }

}
