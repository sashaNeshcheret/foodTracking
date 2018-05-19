package ua.trackingFood.exception;

public class TransactionException extends Throwable {
    public TransactionException() {
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
