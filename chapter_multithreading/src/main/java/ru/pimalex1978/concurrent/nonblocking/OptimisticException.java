package ru.pimalex1978.concurrent.nonblocking;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }

    public OptimisticException() {
        super("Optimistic Exception");
    }
}
