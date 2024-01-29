package mk.ukim.finki.av5;

public class EmptyBoxException extends  Exception{
    public EmptyBoxException() {
        super("The box is empty");
    }
}
