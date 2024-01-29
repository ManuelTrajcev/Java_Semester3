package mk.ukim.finki.lab1;

import java.util.Scanner;
import java.util.stream.IntStream;

public class RomanConverterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        IntStream.range(0, n)
                .forEach(x -> System.out.println(RomanConverter.toRoman(scanner.nextInt())));
        scanner.close();
    }
}


class RomanConverter {
    /**
     * Roman to decimal converter
     *
     * @param n number in decimal format
     * @return string representation of the number in Roman numeral
     */
    public static String toRoman(int n) {
        StringBuilder res = new StringBuilder();
        if (n / 1000 >= 0) {
            int f = n / 1000;
            for (int i = 0; i < f; i++) {
                res.append("M");
            }
            n %= 1000;
        }
        if (n >= 900) {
            res.append("CM");
            n -= 900;
        } else if (n >= 600) {
            res.append("DC");
            n -= 600;
        } else if (n / 500 > 0) {
            res.append("D");
            n %= 500;
        }
        if (n > 400) {
            res.append("CD");
            n -= 400;
        } else if (n / 100 > 0) {
            int f = n / 100;
            for (int i = 0; i < f; i++) {
                res.append("C");
            }
            n %= 100;
        }
        if (n >= 90) {
            res.append("XC");
            n -= 90;
        } else if (n >= 60) {
            res.append("LX");
            n -= 60;
        } else if (n >= 50) {
            res.append("L");
            n -= 50;
        } else if (n >= 40) {
            res.append("XL");
            n -= 40;
        }
        if (n / 10 >= 0) {
            int f = n / 10;
            for (int i = 0; i < f; i++) {
                res.append("X");
            }
            n %= 10;
        }
        if (n >= 5) {
            if (n == 9) {
                res.append("IX");
            } else {
                res.append("V");
                n %= 5;
                for (int i = 0; i < n; i++) {
                    res.append("I");
                }
            }
        } else if (n == 4) {
            res.append("IV");
        } else {
            for (int i = 0; i < n; i++) {
                res.append("I");
            }
        }
        return res.toString();
    }

}
