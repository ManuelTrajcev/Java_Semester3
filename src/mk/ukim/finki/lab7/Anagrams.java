package mk.ukim.finki.lab7;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

    public static void main(String[] args) {
        findAll(System.in);
    }

    public static void findAll(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        List<String> words = br.lines().collect(Collectors.toList());
        TreeMap<String, List<String>> map = new TreeMap<>();
        for (String word : words) {
            char[] charArr = word.toCharArray();
            Arrays.sort(charArr);
            if (map.containsKey(Arrays.toString(charArr))) {
                map.get(Arrays.toString(charArr)).add(word);
            } else {
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(word);
                map.put(Arrays.toString(charArr), tmp);
            }
        }

        List<String> sorted = new ArrayList<>();

        map.values().forEach(i -> {
            StringBuilder str = new StringBuilder();
            i.stream().forEach(w -> str.append(w).append(" "));
            sorted.add(str.toString());
        });

        Collections.sort(sorted);

        sorted.forEach(System.out::println);
    }
}
