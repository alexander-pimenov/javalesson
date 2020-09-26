package ru.pimalex1978.basepatterns.behavioral.strategy.example1;

/**
 * Конкретная стратегия.
 * Активность разработчика - сон.
 */
public class Sleeping implements Activity {
    @Override
    public void justDoIt() {
        System.out.println("Sleeping...");
    }
}
