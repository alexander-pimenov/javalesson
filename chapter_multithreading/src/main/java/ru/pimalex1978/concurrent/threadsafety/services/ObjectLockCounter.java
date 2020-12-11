package ru.pimalex1978.concurrent.threadsafety.services;

/*https://www.baeldung.com/java-thread-safety*/
public class ObjectLockCounter {

    private int counter;
    private final Object lock = new Object();

    public ObjectLockCounter() {
        this.counter = 0;
    }

    public void incrementCounter() {
        synchronized (lock) {
            counter += 1;
        }
    }

    public int getCounter() {
        synchronized (lock) {
            return counter;
        }
    }
}