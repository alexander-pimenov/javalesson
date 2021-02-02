package ru.pimalex1978.concurrent.pools.threadpooljenkov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*http://tutorials.jenkov.com/java-concurrency/thread-pools.html */
public class ThreadPool {
    private BlockingQueue<Runnable> taskQueue = null;
    private List<PoolThreadRunnable> runnables;
    private boolean isStopped = false;
    private int maxNoOfTasks = 10;

    public ThreadPool(int noOfThreads, int maxNoOfTasks) {
        this.taskQueue = new ArrayBlockingQueue<>(maxNoOfTasks);
        this.runnables = new ArrayList<>();
        for (int i = 0; i < noOfThreads; i++) {
//            PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable(taskQueue);
//            runnables.add(poolThreadRunnable);
            runnables.add(new PoolThreadRunnable(taskQueue));
        }

        for (PoolThreadRunnable runnable : runnables) {
            new Thread(runnable).start();
        }
    }

    public ThreadPool() {
        this.taskQueue = new ArrayBlockingQueue<>(maxNoOfTasks);
        this.runnables = new ArrayList<>();
        int noOfThreads = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < noOfThreads; i++) {
//            PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable(taskQueue);
//            runnables.add(poolThreadRunnable);
            runnables.add(new PoolThreadRunnable(taskQueue));
        }

        for (PoolThreadRunnable runnable : runnables) {
            new Thread(runnable).start();
        }
    }

    public synchronized void execute(Runnable task) throws Exception {
        if (this.isStopped) {
            throw new IllegalStateException("ThreadPool is stopped");
        }
        this.taskQueue.offer(task);
    }

    public synchronized void stop() {
        this.isStopped = true;
        for (PoolThreadRunnable runnable : runnables) {
            runnable.doStop();
        }
    }

    public synchronized void waitUntilAllTasksFinished() {
        while (this.taskQueue.size() > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
