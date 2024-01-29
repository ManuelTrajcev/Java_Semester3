package algoritmi.av3;

import java.util.Arrays;
import java.util.Scanner;

public class WaterConnection {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int p = scanner.nextInt();
        int[][] matrix = new int[n][3];
        for (int i = 0; i < n; i++) {
            matrix[i][0] = 0;   //vlez
            matrix[i][1] = -1;  //izlez kon
            matrix[i][2] = 0;   //dijametar
        }
        for (int i = 0; i < p; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int diameter = scanner.nextInt();
            matrix[from][0] = 1;
            matrix[from][1] = to;
            matrix[from][2] = diameter;
        }

        int c = 0;
        int[] arr = new int[n];
        Arrays.stream(arr).forEach(i -> arr[i] = 0);

        while (c < n) {
            if (arr[c] == 1) {
                c++;
                continue;
            }
            if (matrix[c][0] != 0) {
                arr[c] = 1;
                System.out.print(c +"-d("+matrix[c][2]+")"+ "->" + matrix[c][1]);
                c = matrix[c][1];
                while (true) {
                    arr[c] = 1;
                    if (matrix[c][1] != -1) {
                        System.out.print("-d("+matrix[c][2]+")"+"->" + matrix[c][1] );
                        c = matrix[c][1];
                    } else
                        break;
                }
                System.out.print("\n");
                c = 0;
            }
        }
    }
}
