package mk.ukim.finki.av7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFinalistPicker {

    public static List<Integer> pickFinalist() {
        Random random = new Random();
        List<Integer> finalists = new ArrayList<>();
        while (finalists.size() < 3) {
            int n = random.nextInt(30) + 1;
            if (finalists.contains(n)) {
                continue;
            } else {
                finalists.add(n);
            }
        }
        return finalists;
    }

    public static void main(String[] args) {
        System.out.println(pickFinalist());
    }
}
