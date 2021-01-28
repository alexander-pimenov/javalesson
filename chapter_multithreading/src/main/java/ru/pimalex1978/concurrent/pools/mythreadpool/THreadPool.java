package ru.pimalex1978.concurrent.pools.mythreadpool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class THreadPool implements TPExecutor {
    private BlockingQueue<TaskExecutor> taskPool;
    private final List<Thread> threadList = new ArrayList<>();

    public THreadPool() {
        fillThread(3);
    }

    public THreadPool(int size) {
        fillThread(size);
    }

    private void fillThread(int size) {
        for (int i = 0; i < size; i++) {
            threadList.add(
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                try {
                                    taskPool.take().go();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    })
            );
        }
    }

    @Override
    public void addTask(TaskExecutor... task) {
        taskPool = new ArrayBlockingQueue<TaskExecutor>(task.length);
        taskPool.addAll(Arrays.asList(task)); // требуется доработка для добавления новых задач
    }

    @Override
    public void execute() {
        for (Thread th : threadList) {
            th.start();
        }
    }
}
