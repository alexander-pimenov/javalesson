package ru.pimalex1978.concurrent.pools.example7;

import ru.pimalex1978.concurrent.queue.BlockingQueueGeneric;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    private final int MAX_T = Runtime.getRuntime().availableProcessors();
    //Количество нитей всегда одинаковое и равно MAX_T. Это для лучшей производительности.
    private final List<Thread> pool = new ArrayList<>();
    private final BlockingQueueGeneric<Runnable> tasks = new BlockingQueueGeneric<>(5);

    public ThreadPool() {
        for (int i = 0; i < MAX_T; i++) {
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

    /*
     * Метод добавляет задачи (task) в блокирующую очередь.
     * */
    public void work(Runnable job) {
        if (job == null) {
            throw new NullPointerException();
        }
        tasks.offer(job);
    }

    /*Метод закрывает пул потоков*/
    public void shutdown() {
        synchronized (this) {
            for (Thread thread : pool) {
                thread.interrupt();
            }
        }
    }
}

