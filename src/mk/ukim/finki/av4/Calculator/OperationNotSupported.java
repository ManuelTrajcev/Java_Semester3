package mk.ukim.finki.av4.Calculator;

public class OperationNotSupported extends Exception{
    public OperationNotSupported(char operator) {
        super(String.format("The operator %c is not supported.\n", operator));
    }
}
