package mk.ukim.finki.av4.calc;

public class Calculator {
    private double result;
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';

    private Operation operation;

    public Calculator() {
        this.result = 0;
        this.operation = new Addition();
    }

    public String calculate(char op, double num1, double num2)
            throws OperationNotSupported {
        if (op == PLUS) {
            operation = new Addition();
        } else if (op == MINUS) {
            operation = new Substraction();
        } else if (op == MULTIPLY) {
            operation = new Multiplition();
        } else if (op == DIVIDE) {
            operation = new Division();
        } else {
            throw new OperationNotSupported(op);
        }
        return String.format("result: %f %c %f = %f", num1, op, num2, operation.execute(num1, num2));
    }
}
