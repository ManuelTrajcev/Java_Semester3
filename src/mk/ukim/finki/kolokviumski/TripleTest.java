package mk.ukim.finki.kolokviumski;

import java.util.*;

class Triple<T extends Number & Comparable<T>> {
    List<T> nums;

    public Triple(T a, T b, T c) {
        nums = new ArrayList<>();
        nums.add(a);
        nums.add(b);
        nums.add(c);
    }

    double max() {
        return nums.stream().mapToDouble(Number::doubleValue).max().getAsDouble();
    }

    double avarage() {
        return nums.stream().mapToDouble(i -> i.doubleValue()).average().getAsDouble();
    }

    void sort() {
        Collections.sort(nums);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        nums.forEach(n -> str.append(String.format("%.2f ", n.doubleValue())));
        return str.toString();
    }
}

public class TripleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        Triple<Integer> tInt = new Triple<Integer>(a, b, c);
        System.out.printf("%.2f\n", tInt.max());
        System.out.printf("%.2f\n", tInt.avarage());
        tInt.sort();
        System.out.println(tInt);
        float fa = scanner.nextFloat();
        float fb = scanner.nextFloat();
        float fc = scanner.nextFloat();
        Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
        System.out.printf("%.2f\n", tFloat.max());
        System.out.printf("%.2f\n", tFloat.avarage());
        tFloat.sort();
        System.out.println(tFloat);
        double da = scanner.nextDouble();
        double db = scanner.nextDouble();
        double dc = scanner.nextDouble();
        Triple<Double> tDouble = new Triple<Double>(da, db, dc);
        System.out.printf("%.2f\n", tDouble.max());
        System.out.printf("%.2f\n", tDouble.avarage());
        tDouble.sort();
        System.out.println(tDouble);
    }
}
// vasiot kod ovde
// class Triple






