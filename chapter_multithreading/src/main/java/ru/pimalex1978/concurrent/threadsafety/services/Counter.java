package ru.pimalex1978.concurrent.threadsafety.services;

/*https://www.baeldung.com/java-thread-safety*/
public class Counter {

    private volatile int counter;

    public Counter() {
        this.counter = 0;
    }

    public synchronized void incrementCounter() {
        counter += 1;
    }

    public int getCounter() {
        return counter;
    }
}