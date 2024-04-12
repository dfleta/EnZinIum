package enzinium;

public class InsufficientTokensException extends Exception {

    public InsufficientTokensException () {
        super();
    }

    public InsufficientTokensException(String message) {
        super(message);
    }
}
