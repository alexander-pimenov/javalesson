package ru.pimalex1978.concurrent.pools.threadpoolimproved;

/**
 * Вызывается, когда есть исключение RuntimeException или
 * InterruptedException при выполнении исполняемого файла из очереди
 * или ожидании завершения.
 */
public class ThreadpoolException extends RuntimeException {
    public ThreadpoolException(Throwable cause) {
        super(cause);
    }
}
