package ru.pimalex1978.concurrent.threadsafety.callables;

import ru.pimalex1978.concurrent.threadsafety.services.AtomicCounter;

import java.util.concurrent.Callable;

/*https://www.baeldung.com/java-thread-safety*/

public class AtomicCounterCallable implements Callable<Integer> {

    private final AtomicCounter counter;

    public AtomicCounterCallable(AtomicCounter counter) {
        this.counter = counter;
    }

    @Override
    public Integer call() throws Exception {
        counter.incrementCounter();
        return counter.getCounter();
    }
}