package ru.pimalex1978.basepatterns.behavioral.strategy.example2;

public class HandJob implements IJob {
    @Override
    public void doJob() {
        System.out.println("Hand Job ...");
    }
}
