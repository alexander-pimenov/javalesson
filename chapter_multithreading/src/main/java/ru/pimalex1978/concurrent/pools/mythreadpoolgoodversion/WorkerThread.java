package ru.pimalex1978.concurrent.pools.mythreadpoolgoodversion;

import ru.pimalex1978.concurrent.simpleblockingqueue.SimpleBlockingQueue;

public class WorkerThread extends Thread {
    private SimpleBlockingQueue<Runnable> taskQueue = null;
    private boolean isStopped = false;

    public WorkerThread(SimpleBlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (!isStopped()) {
            Runnable runnable;
            try {
                while ((runnable = taskQueue.poll()) != null) {
                    runnable.run();
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * С помощью этого метода из пула потоков прерываем
     * отдельный рабочий поток.
     */
    public synchronized void doStop() {
        isStopped = true;
        this.interrupt();
    }

    private synchronized boolean isStopped() {
        return isStopped;
    }
}
