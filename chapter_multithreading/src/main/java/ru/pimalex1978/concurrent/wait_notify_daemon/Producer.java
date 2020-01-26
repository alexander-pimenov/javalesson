package ru.pimalex1978.concurrent.wait_notify_daemon;

/**
 * Классы Producer и Consumer реализуют интерфейс Runnable, методы run() у
 * них переопределены. Конструкторы этих классов в качестве параметра получают
 * объект склад Store. При старте данных объектов в виде отдельных потоков в
 * цикле вызываются методы put() и get() класса Store для «добавления» и
 * «получения» товара.
 */

public class Producer implements Runnable {
    Store store;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            store.put();
        }
    }
}
