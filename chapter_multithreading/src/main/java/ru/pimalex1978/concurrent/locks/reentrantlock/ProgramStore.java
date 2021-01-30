package ru.pimalex1978.concurrent.locks.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

import static ru.pimalex1978.concurrent.ColorScheme.BLUE;
import static ru.pimalex1978.concurrent.ColorScheme.RED;

/*
 * https://metanit.com/java/tutorial/8.10.php
 *
 * В этом примере у нас есть склад, где могут одновременно быть размещено
 * не более 3 товаров. И производитель должен произвести 5 товаров, а
 * покупатель должен эти товары купить. В то же время покупатель не
 * может купить товар, если на складе нет никаких товаров.
 *
 * Меняя sleep() в run() производителя и потребителя, можно видеть, как
 * магазин сначала быстро заполняется товаром, а потом его заберет потребитель.
 *
 * В этом примере, мы можем использовать следующие методы интерфейса Condition:
 * - await: поток ожидает, пока не будет выполнено некоторое условие и пока другой
 *   поток не вызовет методы signal/signalAll. Во многом аналогичен методу wait
 *   класса Object
 * - signal: сигнализирует, что поток, у которого ранее был вызван метод await(),
 *   может продолжить работу. Применение аналогично использованию методу notify
 *   класса Object
 * - signalAll: сигнализирует всем потокам, у которых ранее был вызван метод
 *   await(), что они могут продолжить работу. Аналогичен методу notifyAll()
 *   класса Object
 *
 * */
public class ProgramStore {
    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

// Класс Магазин, хранящий произведенные товары
class Store {
    private int product = 0;
    private final ReentrantLock locker;
    private final Condition condition;

    Store() {
        locker = new ReentrantLock(); // создаем блокировку
        condition = locker.newCondition(); // получаем условие, связанное с блокировкой
    }

    public void get() {

        locker.lock();
        try {
            // пока нет доступных товаров на складе, ожидаем
            while (product < 1) {
                condition.await();
            }

            product--;
            System.out.println(RED + "Покупатель купил 1 товар");
            System.out.println("Товаров на складе: " + product);

            // сигнализируем
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }

    public void put() {

        locker.lock();
        try {
            // пока на складе 3 товара, ждем освобождения места
            while (product >= 3) {
                condition.await();
            }

            product++;
            System.out.println(BLUE + "Производитель добавил 1 товар");
            System.out.println("Товаров на складе: " + product);
            // сигнализируем
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }
}

// класс Производитель
class Producer implements Runnable {

    private Store store;

    Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.put();
            try {
                Thread.sleep(500); //иммитация задержки работы
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Класс Потребитель
class Consumer implements Runnable {

    private Store store;

    Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            try {
                Thread.sleep(2500); //иммитация задержки работы
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            store.get();
        }
    }
}
