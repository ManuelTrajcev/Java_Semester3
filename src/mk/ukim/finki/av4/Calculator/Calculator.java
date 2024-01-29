package mk.ukim.finki.av4.Calculator;

public class Calculator {
    private double result;
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';
    private double state = 0.0;
    private Strategy strategy;

    public Calculator() {
        state = 0;
        strategy = new Addition();
    }

    public String execute(char operator, double value)
            throws OperationNotSupported {
        if (operator == PLUS) {
            strategy = new Addition();
        } else if (operator == MINUS) {
            strategy = new Substraction();
        } else if (operator == MULTIPLY) {
            strategy = new Multiplition();
        } else if (operator == DIVIDE) {
            strategy = new Division();
        } else {
            throw new OperationNotSupported(operator);
        }
        state = strategy.execute(operator, value);
        return String.format("result %c %f = %f", operator, value, state);
    }

    @Override
    public String toString() {
        return String.format("result = %f", state);
    }
}