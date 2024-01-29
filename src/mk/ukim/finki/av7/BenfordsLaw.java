package mk.ukim.finki.av7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;

public class BenfordsLaw {
    LocalDateTime localDateTime;

    public static int getFirstDigit(int number) {
        while (number >= 10) {
            number /= 10;
        }
        return number;
    }

    public static String printBenfordLawCounts(String file) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        int[] arr = new int[9];
        Arrays.stream(arr).forEach(i -> i = 0);
        br.lines().forEach(i -> {
            int a = getFirstDigit(Integer.parseInt(i));
            arr[a - 1]++;
        });
        double[] occurency = new double[9];
        for (int i = 0; i < 9; i++) {
            occurency[i] = arr[i] / 9.0;
        }
        return Arrays.toString(occurency);
    }


    public static void main(String[] args) {
        try {
            System.out.println(printBenfordLawCounts("D:\\FINKI\\SEMESTAR III\\NAPREDNO PROGRAMIRANJE\\NP2023\\src\\mk\\ukim\\finki\\av7\\bfl"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
