package ru.pimalex1978.concurrent.pools.threadpooljenkov;

import java.util.concurrent.BlockingQueue;

/*http://tutorials.jenkov.com/java-concurrency/thread-pools.html */
public class PoolThreadRunnable implements Runnable {
    private Thread thread = null;
    private BlockingQueue<Runnable> taskQueue = null;
    private boolean isStopped = false;

    public PoolThreadRunnable(BlockingQueue<Runnable> queue) {
        this.taskQueue = queue;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped()) {
            try {
                Runnable runnable = taskQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
                System.out.println("-------- " + e.getMessage());
                //                e.printStackTrace();
                //log или другим способом сообщить об exception,
                //но сохранить pool thread живым.
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        //прервать pool thread из вызова dequeue().
        this.thread.interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}
