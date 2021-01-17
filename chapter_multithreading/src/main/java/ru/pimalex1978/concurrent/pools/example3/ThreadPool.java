package ru.pimalex1978.concurrent.pools.example3;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads;
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool(int sizeTaskQueue) {
        this.threads = new LinkedList<>();
        this.tasks = new SimpleBlockingQueue<>(sizeTaskQueue);
    }

    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createThreads() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            threads.add(new SimpleThread(tasks));
        }
    }

    public void start() {
        createThreads();
        threads.forEach(Thread::start);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
