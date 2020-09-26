package ru.pimalex1978.basepatterns.behavioral.strategy.example2;

public class Worker {
    //IJob job;

    /**
     * Метод выполнения работы.
     * Поле IJob можно не писать, т.к. сам метод
     * принимает объект интерфейса.
     *
     * @param job объект реализующий интерфейс.
     */
    public void doWork(IJob job) {
        if (job != null) {
            job.doJob();
        }
    }
}
