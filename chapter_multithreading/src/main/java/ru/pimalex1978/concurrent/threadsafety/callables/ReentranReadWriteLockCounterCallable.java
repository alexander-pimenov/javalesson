package ru.pimalex1978.concurrent.threadsafety.callables;

import ru.pimalex1978.concurrent.threadsafety.services.ReentrantReadWriteLockCounter;

import java.util.concurrent.Callable;

/*https://www.baeldung.com/java-thread-safety*/

public class ReentranReadWriteLockCounterCallable implements Callable<Integer> {

    private final ReentrantReadWriteLockCounter counter;

    public ReentranReadWriteLockCounterCallable(ReentrantReadWriteLockCounter counter) {
        this.counter = counter;
    }

    @Override
    public Integer call() throws Exception {
        counter.incrementCounter();
        return counter.getCounter();
    }

}