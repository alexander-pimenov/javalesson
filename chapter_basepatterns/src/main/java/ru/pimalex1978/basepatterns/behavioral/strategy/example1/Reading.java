package ru.pimalex1978.basepatterns.behavioral.strategy.example1;

/**
 * Конкретная стратегия.
 * Активность разработчика - чтение.
 */
public class Reading implements Activity {
    @Override
    public void justDoIt() {
        System.out.println("Reading...");
    }
}
