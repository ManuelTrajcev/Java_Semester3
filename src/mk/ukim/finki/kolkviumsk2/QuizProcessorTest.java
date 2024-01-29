package mk.ukim.finki.kolkviumsk2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

class invalidQuiz extends Exception {
    public invalidQuiz() {
        super("A quiz must have same number of correct and selected answers");
    }
}

class QuizProcessor {
    static double calculatePoints(String[] correct, String[] guesses) throws invalidQuiz {
        if (correct.length != guesses.length) {
            throw new invalidQuiz();
        }
        double points = 0;
        for (int i = 0; i < correct.length; i++) {
            if (correct[i].trim().equals(guesses[i].trim())) {
                points++;
            } else {
                points -= 0.25;
            }
        }
        return points;
    }

    static Map<String, Double> processAnswers(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        Map<String, Double> users = new TreeMap<>();
        br.lines().forEach(i -> {
            String[] parts = i.split(";");
            String id = parts[0];
            String[] correct = parts[1].split(",");
            String[] guesses = parts[2].split(",");
            try {
                users.put(id, calculatePoints(correct, guesses));
            } catch (invalidQuiz e) {
                System.out.println(e.getMessage());
            }
        });
        return users;
    }
}

public class QuizProcessorTest {
    public static void main(String[] args) {
        QuizProcessor.processAnswers(System.in).forEach((k, v) -> System.out.printf("%s -> %.2f%n", k, v));
    }
}