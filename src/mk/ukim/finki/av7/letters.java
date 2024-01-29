package mk.ukim.finki.av7;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

class SentenceParser {
    public static void arrangeSentence(String sentence) {
        String[] words = sentence.split("\\s+");

        for (int i = 0; i < words.length; i++) {
            char[] word = words[i].toCharArray();
            Arrays.sort(word);
            words[i] = String.valueOf(word);
        }

        for (int i = 0; i < words.length; i++) {
            char[] word = words[i].toCharArray();
            Arrays.sort(word);
            words[i] = String.valueOf(word);
        }
        Arrays.sort(words);
        System.out.println(Arrays.toString(words));
    }
}

public class letters {

    public static void main(String[] args) {
        String test = "kO pSk sO";
        SentenceParser.arrangeSentence(test);
    }
}
