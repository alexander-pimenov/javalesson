package ru.pimalex1978.basepatterns.behavioral.strategy.example1;

/**
 * Конкретная стратегия.
 * Активность разработчика - тренировка.
 */
public class Training implements Activity {
    @Override
    public void justDoIt() {
        System.out.println("Training...");
    }
}
