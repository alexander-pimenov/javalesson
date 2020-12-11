package ru.pimalex1978.concurrent.threadsafety.callables;

import ru.pimalex1978.concurrent.threadsafety.services.Counter;

import java.util.concurrent.Callable;

/*https://www.baeldung.com/java-thread-safety*/
public class CounterCallable implements Callable<Integer> {

    private final Counter counter;

    public CounterCallable(Counter counter) {
        this.counter = counter;
    }

    @Override
    public Integer call() throws Exception {
        counter.incrementCounter();
        return counter.getCounter();
    }
}