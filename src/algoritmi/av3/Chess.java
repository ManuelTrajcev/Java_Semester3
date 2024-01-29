package algoritmi.av3;

import java.util.Scanner;

public class Chess {

    public static boolean canAttack(int a1, int b1, int a2, int b2) {
        if (a1 == a2 || b1 == b2 || Math.abs(a1 - a2) == Math.abs(b1 - b2)) {
            return true;
        } else {
            return false;
        }
    }

    public static int posibilities(int n) {
        int pos = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        if (!canAttack(i, j, k, l)) {
                            pos++;
                        }
                    }
                }
            }
        }
        return pos;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.printf("Posibilities of ataccking:%d", posibilities(n));
    }

}
