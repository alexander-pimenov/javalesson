package ru.pimalex1978.concurrent.threadsafety.services;

import java.util.concurrent.locks.ReentrantLock;

/*https://www.baeldung.com/java-thread-safety*/
public class ReentrantLockCounter {

    private int counter;
    private final ReentrantLock reLock = new ReentrantLock(true);

    public ReentrantLockCounter() {
        this.counter = 0;
    }

    public void incrementCounter() {
        reLock.lock();
        try {
            counter += 1;
        } finally {
            reLock.unlock();
        }
    }

    public int getCounter() {
        return counter;
    }
}