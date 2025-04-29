package exception;

public class InvalidNumberOfPlayers extends RuntimeException{
    public InvalidNumberOfPlayers(String message) {
        super(message);
    }
}
