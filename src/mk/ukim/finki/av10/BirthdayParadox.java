package mk.ukim.finki.av10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

class Experiment {
    static int TRAILS = 500;

    static double run(int people) {
//        int successful = 0;
//        for (int i = 0; i < TRAILS; i++) {
//            if (Trial.run(people)) {
//                successful++;
//            }
//        }
//        return (float) successful / people;
        int counter = 0;
        for (int i = 0; i < TRAILS; i++) {
            if (Trial.run(people)){
                counter++;
            }
        }
        return (float) counter / TRAILS;
    }
}

class Trial {
    static Random RANDOM = new Random();

    static boolean run(int people) {
//        HashSet<Integer> birthdays = new HashSet<>();
//        for (int i = 0; i < people; i++) {
//            int birthday = RANDOM.nextInt(365) + 1;
//            if (birthdays.contains(birthday)) {
//                return true;
//            }
//            birthdays.add(birthday);
//        }
//        return false;
        HashSet<Integer> birthdays = new HashSet<>();
        for (int i = 0; i < people; i++) {
            int b = RANDOM.nextInt(365) + 1;
            if (birthdays.contains(b)) {
                return true;
            }
            birthdays.add(b);
        }
        return false;
    }
}

public class BirthdayParadox {
    public static void main(String[] args) {
        System.out.println(Experiment.run(50));
    }
}
