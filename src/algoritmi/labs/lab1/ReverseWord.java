package algoritmi.labs.lab1;

import algoritmi.av2.Array;

import java.util.Scanner;

public class ReverseWord {
    public static void printReversed(String word) {
        int i = word.length();
        String newWord = "";
        while (i != 0) {
            newWord += word.charAt(i - 1);
            i--;
        }
        System.out.println(newWord);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String word = "";
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            word = scanner.nextLine();
            printReversed(word);
        }

    }
}
