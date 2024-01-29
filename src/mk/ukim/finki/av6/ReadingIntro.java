package mk.ukim.finki.av6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReadingIntro {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> lines = new ArrayList<>();

//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            lines.add(line);
//        }

//        List<Integer> numbers = new ArrayList<>();
//        int n = scanner.nextInt();
//        for (int i = 0; i < n; i++) {
//            numbers.add(scanner.nextInt());
//        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    //sekoj element pretstavuva linija
        lines = br.lines().collect(Collectors.toList());    //KOLOKVIUM!!!!!!!
        System.out.println(lines);

    }
}
