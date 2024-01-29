package mk.ukim.finki.av4.calc;

public class OperationNotSupported extends Exception{
    public OperationNotSupported(char operation) {
        super(String.format("%c is not a valid operation\n", operation));
    }
}
