package mk.ukim.finki.av4.calc;

public class Division implements Operation {
    @Override
    public double execute(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException();
        }
        return num1 / num2;
    }
}
