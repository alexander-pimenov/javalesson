package ru.pimalex1978.concurrent.threadsafety.services;

import java.util.concurrent.atomic.AtomicInteger;

/*https://www.baeldung.com/java-thread-safety*/
public class AtomicCounter {

    private final AtomicInteger counter = new AtomicInteger();

    public AtomicCounter() {
    }

    public void incrementCounter() {
        counter.incrementAndGet();
    }

    public synchronized int getCounter() {
        return counter.get();
    }
}