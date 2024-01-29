package mk.ukim.finki.kolokviumski;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MinMax<T extends Comparable<T>> {
    T min;
    T max;
    int counter;

    public MinMax() {
        counter = 0;
    }

    public T max() {
        return max;
    }

    public T min() {
        return min;
    }

    public void update(T s) {
        if (max != null && min != null && !max.equals(s) && !min.equals(s)) {
            counter++;
        }
        if (max == null) {
            max = s;
            return;
        } else if (min == null && !max.equals(s)) {
            if (s.compareTo(max) > 0) {
                min = max;
                max = s;
//                counter++;
                return;
            } else
                min = s;
            return;
        }
        if (s.equals(max) || s.equals(min)) {
            return;
        } else {
//            counter++;
            if (max == null || s.compareTo(max) > 0) {
                max = s;
            }
            if (min == null || s.compareTo(min) < 0) {
                min = s;
            }
        }
    }

    @Override
    public String toString() {
        return String.format(min() + " " + max()) + " " + counter + "\n";
    }


}

public class MinAndMax {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for (int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
        System.out.println(strings);
        MinMax<Integer> ints = new MinMax<Integer>();
        for (int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
    }
}
