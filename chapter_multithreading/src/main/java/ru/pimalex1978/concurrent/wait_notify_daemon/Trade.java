package ru.pimalex1978.concurrent.wait_notify_daemon;

/**
 * Рассмотрим пример «Производитель-Склад-Потребитель» (Producer-Store-Consumer).
 * Пока производитель не поставит на склад продукт, потребитель не может его забрать.
 * Допустим производитель должен поставить 5 единиц определенного товара. Соответственно
 * потребитель должен весь товар получить. Но, при этом, одновременно на складе может
 * находиться не более 3 единиц товара. При реализации данного примера используем
 * методы wait() и notify().
 * <p>
 * В главном потоке класса Trade (в методе main) создаются объекты
 * Producer-Store-Consumer и стартуются потоки производителя и потребителя.
 */
public class Trade {
    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
