package ru.pimalex1978.concurrent.pools.example4;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> queue = new SimpleBlockingQueue<>(size);
    private final List<Thread> list = new LinkedList<>();

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                queue.poll().run();
                            } catch (Exception e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    });
            list.add(thread);
            thread.start();
        }
    }

    public void work(Runnable job) {
        queue.offer(job);
    }

    /* Инициирует упорядоченное завершение работы, при котором ранее
     * отправленные задачи выполняются, но новые задачи не принимаются.
     *
     * Этот метод не ожидает завершения выполнения ранее отправленных задач.
     * Для этого используйте {@link #awaitTermination awaitTermination}.
     * */
    public void shutdown() {
        for (Thread thread : list) {
            thread.interrupt();
        }
    }
}
