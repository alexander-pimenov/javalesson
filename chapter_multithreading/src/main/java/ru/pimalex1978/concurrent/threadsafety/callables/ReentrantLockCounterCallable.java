package ru.pimalex1978.concurrent.threadsafety.callables;

import ru.pimalex1978.concurrent.threadsafety.services.ReentrantLockCounter;

import java.util.concurrent.Callable;

/*https://www.baeldung.com/java-thread-safety*/

public class ReentrantLockCounterCallable implements Callable<Integer> {

    private final ReentrantLockCounter counter;

    public ReentrantLockCounterCallable(ReentrantLockCounter counter) {
        this.counter = counter;
    }

    @Override
    public Integer call() throws Exception {
        counter.incrementCounter();
        return counter.getCounter();
    }
}