package ru.pimalex1978.concurrent.pools.mythreadpool3;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public class TestSimpleThreadpool1 {
    public static void main(String[] args) {
        SimpleThreadpool threadpool = SimpleThreadpool.getInstance();
        int runnableCount = 10;
        final AtomicInteger count = new AtomicInteger(0);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                count.getAndIncrement();
                System.out.println(count.toString());
            }
        };
        for (int i = 0; i < runnableCount; i++) {
            threadpool.execute(r);
        }

        threadpool.stop();
        try {
            threadpool.awaitTermination();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
