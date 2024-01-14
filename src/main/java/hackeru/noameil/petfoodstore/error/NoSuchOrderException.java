package hackeru.noameil.petfoodstore.error;

public class NoSuchOrderException extends Exception {
    public NoSuchOrderException(String message) {
        super(message);
    }
}
