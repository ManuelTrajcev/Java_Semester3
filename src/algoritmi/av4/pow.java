package algoritmi.av4;

import java.util.Scanner;

public class pow {

    public static int pow(int n, int p) {
        int r = 1;
        if (p == 1) {
            return n;
        } else if (p == 0) {
            return 1;
        } else if (p % 2 == 0) {
            r = pow(n, p / 2);
            return r*r;
        } else {
            r = pow(n, p / 2);
            r *= r * n;
        }
        return r;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int p = scanner.nextInt();

        System.out.println(pow(n, p));
    }
}
