package ru.pimalex1978.concurrent.pools.example2;

import org.junit.Test;

public class ThreadPoolTest {

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
        ThreadPool threadPool = ThreadPool.newFixedThreadPool(2);
        threadPool.work(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello!");
            }
        });
        threadPool.work(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello!");
            }
        });
        threadPool.work(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello!");
            }
        });

        threadPool.shutdown();
    }
}