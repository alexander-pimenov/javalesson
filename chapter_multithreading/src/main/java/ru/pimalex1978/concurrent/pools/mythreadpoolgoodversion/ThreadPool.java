package ru.pimalex1978.concurrent.pools.mythreadpoolgoodversion;

import ru.pimalex1978.concurrent.simpleblockingqueue.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {
    //Количество созданных пулов потоков
    private static AtomicInteger poolCount = new AtomicInteger(0);
    //Очередь запускаемых файлов (Runnable)
    private SimpleBlockingQueue<Runnable> taskQueue;
    // Контейнер для рабочих потоков в пуле
    private List<WorkerThread> threads;
    //Флаг выключения пула потоков
    private boolean isStopped;

    /**
     * Конструктор, в котором можно задавать количество создаваемых в пуле
     * рабочих потоков.
     * Устанавливаем размер внутренней блокирующей очереди, например, равной 10.
     *
     * @param noOfThreads количество потоков
     */
    public ThreadPool(int noOfThreads) {
        poolCount.incrementAndGet();
        this.isStopped = false;
        this.taskQueue = new SimpleBlockingQueue<>(10);
        this.threads = new ArrayList<>();

        for (int i = 0; i < noOfThreads; i++) {
            WorkerThread workerThread = new WorkerThread(taskQueue);
            workerThread.setName("ThreadPool-" + poolCount.get() + "-Thread-" + i);
            workerThread.start();
            this.threads.add(workerThread);
        }
    }

    /**
     * Конструктор по умолчанию. Создается два рабочих потока.
     * Устанавливаем размер внутренней блокирующей очереди, например, равной 10.
     */
    public ThreadPool() {
        poolCount.incrementAndGet();
        this.isStopped = false;
        this.taskQueue = new SimpleBlockingQueue<>(10);
        this.threads = new ArrayList<>();

        //Инициализация пула должна быть по количеству ядер в системе (это оптимально)
        int noOfThreads = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < noOfThreads; i++) {
            WorkerThread workerThread = new WorkerThread(taskQueue);
            workerThread.setName("ThreadPool-" + poolCount.get() + "-Thread-" + i);
            workerThread.start();
            this.threads.add(workerThread);
        }
    }

    /**
     * Конструктор, в котором можно задавать количество создаваемых в пуле
     * рабочих потоков и размер внутренней очереди для объектов Runnable.
     *
     * @param noOfThreads  количество рабочих потоков пула
     * @param maxNoOfTasks размер очереди
     */
    public ThreadPool(int noOfThreads, int maxNoOfTasks) {
        poolCount.incrementAndGet();
        this.isStopped = false;
        this.taskQueue = new SimpleBlockingQueue<>(maxNoOfTasks);
        this.threads = new ArrayList<>();

        for (int i = 0; i < noOfThreads; i++) {
            WorkerThread workerThread = new WorkerThread(taskQueue);
            workerThread.setName("ThreadPool-" + poolCount.get() + "-Thread-" + i);
            workerThread.start();
            this.threads.add(workerThread);
        }
    }

    /**
     * Добавляет Runnable объект в очередь на обработку.
     *
     * @param task Runnable объекты
     */
    public void work(Runnable task) {
        if (this.isStopped) {
            throw new IllegalStateException("Thread is stopped");
        }
        try {
            this.taskQueue.offer(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Метод устанавливает флаг isStopped в false, и работа пула потоков
     * продолжается до тех пор, пока очередь имеет Runnable объекты.
     */
    public void shutdown() {
        this.isStopped = true;
        for (WorkerThread th : threads) {
            th.doStop();
        }
    }

    /**
     * Метод ожидает пока есть задания в очереди.
     */
    public void waitUntilAllTasksFinished() {
        while (taskQueue.size() > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
