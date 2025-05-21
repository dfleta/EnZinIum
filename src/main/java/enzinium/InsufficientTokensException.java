package enzinium;

public class InsufficientTokensException extends RuntimeException {

    /**
     * all of the unchecked throwables you implement 
     * should subclass RuntimeException (directly or indirectly)
    */

    public InsufficientTokensException () {
        super();
    }

    public InsufficientTokensException(String message) {
        super(message);
    }
}
