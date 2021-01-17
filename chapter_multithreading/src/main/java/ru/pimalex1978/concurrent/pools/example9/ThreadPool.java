package ru.pimalex1978.concurrent.pools.example9;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private final int poolSize = Runtime.getRuntime().availableProcessors();
    private final BlockingQueue<Runnable> workQueue = new BlockingQueue<>(8);
    private final List<Thread> threads = new ArrayList<>();

    public ThreadPool() {
        for (int i = 0; i < poolSize; i++) {
            Thread worker = new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            /*поток будет обрабатывать задачи, которые
                             * ему приходят, пока в условии мы не поймаем запрос
                             * на прерывание потока: !Thread.currentThread().isInterrupted()
                             * Задачи это объекты Runnable.
                             * И у Runnable вызываем метод run(). */
                            while (!Thread.currentThread().isInterrupted()) {
                                /*Получаем задачу из очереди.*/
                                Runnable task;
                                try {
                                    task = workQueue.get();
                                    /*выполняем задачу.*/
                                    task.run();
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }
                    }
            );
            threads.add(worker);
            worker.start();
        }
    }

    public void work(Runnable job) {
        try {
            workQueue.put(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        for (Thread th : threads) {
            th.interrupt();
        }
    }
}
