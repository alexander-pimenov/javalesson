package ru.pimalex1978.basepatterns.behavioral.strategy.example2;

public class Main {
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.doWork(new FootJob());
        worker.doWork(new HandJob());
    }
}
