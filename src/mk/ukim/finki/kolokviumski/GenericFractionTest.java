 package mk.ukim.finki.kolokviumski;

import java.util.Scanner;

class ZeroDenominatorException extends Exception {
    public ZeroDenominatorException(String message) {
        super(message);
    }
}

class GenericFraction<T extends Number, U extends Number> {
    T numerator;
    U denominator;

    public GenericFraction(T numerator, U denominator) throws ZeroDenominatorException {
        if (denominator.equals(0)) {
            throw new ZeroDenominatorException("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    private int findNGD(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    private int findNZS(int a, int b) {
        return a * b / findNGD(a, b);
    }

    GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) throws ZeroDenominatorException {
//        int n = findNZS(this.denominator.intValue(), gf.denominator.intValue());
//        double thisNumerator = ((Integer) this.numerator).doubleValue() * n / this.denominator.doubleValue();
//        double thatNumerator = ((Integer) gf.numerator).doubleValue() * n / gf.denominator.doubleValue();
        int thisDenominator = this.denominator.intValue();
        int thatDenominator = gf.denominator.intValue();
        int n = findNZS(thisDenominator, thatDenominator);
        double thisNumerator = this.numerator.doubleValue() * n / thisDenominator;
        double thatNumerator = gf.numerator.doubleValue() * n / thatDenominator;
        return new GenericFraction<>(thisNumerator + thatNumerator, (double) n);
    }

    double toDouble() {
        return (double) numerator / (double) denominator;
    }

    @Override
    public String toString() {
        return String.format("%.2f / %.2f", (double) numerator, (double) denominator);
    }
}

public class GenericFractionTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
            GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
            GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
            GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
        } catch (ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

}

// вашиот код овде

