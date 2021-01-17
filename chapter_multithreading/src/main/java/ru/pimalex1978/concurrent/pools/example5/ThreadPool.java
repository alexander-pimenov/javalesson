package ru.pimalex1978.concurrent.pools.example5;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final int poolSize = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(poolSize);;
    private final List<Thread> pool = new LinkedList<>();

    private void initPool() {
        for (int i = 0; i < poolSize; i++) {
            pool.add(new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        Runnable job = tasks.poll();
                        job.run();
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }));
        }
    }

    public ThreadPool() {
        for (int i = 0; i < poolSize; i++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            tasks.poll().run();
                        }
                    });
            pool.add(thread);
            thread.start();
        }
    }

    //    public void work(Runnable job) {
//        if (pool.isEmpty()) {
//            initPool();
//        }
//        tasks.offer(job);
//    }
        public void work(Runnable job) {
        tasks.offer(job);
        }

    public void shutdown() {
        pool.forEach(Thread::interrupt);
    }

}