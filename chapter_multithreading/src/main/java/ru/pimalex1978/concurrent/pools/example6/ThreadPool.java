package ru.pimalex1978.concurrent.pools.example6;

import net.jcip.annotations.GuardedBy;

import java.util.LinkedList;

public class ThreadPool {

    private final Object lock = new Object();

    private final int nThreads;

    private final PoolWorker[] threads;

    private final LinkedList<Runnable> queue;



    public ThreadPool(int nThreads) {
        this.nThreads = nThreads;
        queue = new LinkedList<>();
        threads = new PoolWorker[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    public void execute (Runnable r){
        synchronized (lock){
            queue.addLast(r);
            lock.notifyAll();
        }
    }

    /* Инициирует упорядоченное завершение работы, при котором ранее
     * отправленные задачи выполняются, но новые задачи не принимаются.
     *
     * Этот метод не ожидает завершения выполнения ранее отправленных задач.
     * Для этого используйте {@link #awaitTermination awaitTermination}.
     * */
    public void shutdown(){
        for (Thread th :threads){
            th.interrupt();
        }
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            Runnable r;
            while (true) {
//            while (!Thread.currentThread().isInterrupted()) {
                synchronized (lock) {
                    while (queue.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {

                        }
                    }
                    r = queue.removeFirst();
                }
                // Если мы не поймем RuntimeException,
                // пул может пропускать потоки
                try {
                    r.run();
                } catch (RuntimeException e) {
                    //Возможно, мы захотим что-то здесь записать
                }
            }
        }
    }
}
