package mk.ukim.finki.lab7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class Word {
    String word;
    int count;

    public Word(String word) {
        this.word = word;
        count = 1;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }
}

class TermFrequency {

    HashMap<String, Word> words;

    public TermFrequency(InputStream in, String[] stop) {
        words = new HashMap<>();
        Scanner sc = new Scanner(in);

        while (sc.hasNext()) {
            String curr = sc.next();
            curr = cleanString(curr);

            if (!containsStopWord(curr, stop) && !curr.isEmpty()) {
                if (words.containsKey(curr)) {
                    words.get(curr).count++;
                } else {
                    words.put(curr, new Word(curr));
                }
            }

        }

    }

    private boolean containsStopWord(String curr, String[] stop) {
        for (String s : stop) {
            if (s.equals(curr)) {
                return true;
            }
        }
        return false;
    }

    private String cleanString(String curr) {
        return curr.replaceAll("\\.", "").replaceAll(",", "").toLowerCase();
    }

    public int countTotal() {
        return words
                .values()
                .stream()
                .mapToInt(w -> w.count)
                .sum();
    }

    public int countDistinct() {
        return words.size();
    }

    public List<String> mostOften(int k) {
        return words
                .values()
                .stream()
                .sorted(Comparator.comparing(Word::getCount).reversed().thenComparing(Word::getWord))
                .map(i -> i.word)
                .collect(Collectors.toList())
                .subList(0, k);
    }
}

public class TermFrequencyTest {
    public static void main(String[] args) throws FileNotFoundException {
        String[] stop = new String[]{"во", "и", "се", "за", "ќе", "да", "од",
                "ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
                "што", "на", "а", "но", "кој", "ја"};
        TermFrequency tf = new TermFrequency(System.in, stop);
        System.out.println(tf.countTotal());
        System.out.println(tf.countDistinct());
        System.out.println(tf.mostOften(10));
    }
}
