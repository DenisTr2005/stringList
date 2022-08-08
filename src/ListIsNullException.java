public class ListIsNullException extends RuntimeException {
    public ListIsNullException() {
    }

    public ListIsNullException(String message) {
        super(message);
    }

    public ListIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListIsNullException(Throwable cause) {
        super(cause);
    }

    public ListIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
