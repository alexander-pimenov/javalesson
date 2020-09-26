package ru.pimalex1978.basepatterns.behavioral.strategy.example3;

/**
 * Общий интерфейс всех стратегий.
 * Общий интерфейс стратегий оплаты.
 */
public interface PayStrategy {
    boolean pay(int paymentAmount);

    void collectPaymentDetails();
}
