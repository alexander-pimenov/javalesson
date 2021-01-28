package ru.pimalex1978.concurrent.pools.mythreadpool;

public interface TPExecutor {
    void addTask(TaskExecutor... task);

    void execute();
}
