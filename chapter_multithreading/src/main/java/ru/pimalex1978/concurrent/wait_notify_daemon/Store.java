package ru.pimalex1978.concurrent.wait_notify_daemon;

/**
 * Рассмотрим пример «Производитель-Склад-Потребитель» (Producer-Store-Consumer).
 * Пока производитель не поставит на склад продукт, потребитель не может его забрать.
 * Допустим производитель должен поставить 5 единиц определенного товара. Соответственно
 * потребитель должен весь товар получить. Но, при этом, одновременно на складе может
 * находиться не более 3 единиц товара. При реализации данного примера используем
 * методы wait() и notify().
 * <p>
 * Класс Store содержит два синхронизированных метода для получения товара get() и
 * для добавления товара put(). При получении товара выполняется проверка счетчика
 * counter. Если на складе товара нет, то есть counter < 1, то вызывается метод
 * wait(), который освобождает монитор объекта Store и блокирует выполнение метода
 * get(), пока для этого монитора не будет вызван метод notify().
 * <p>
 * При добавлении товара также выполняется проверка количества товара на складе.
 * Если на складе больше 3 единиц товара, то поставка товара приостанавливается и
 * вызывается метод notify(), который передает управление методу get() для
 * завершения цикла while().
 */

public class Store {
    private int counter = 0;

    public synchronized void get() {
        while (counter < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        counter--;
        System.out.println("-1 : товар забрали");
        System.out.println(
                "\tколичество товара на складе : " + counter);
        notify();
    }

    public synchronized void put() {
        while (counter >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        counter++;
        System.out.println("+1 : товар добавили");
        System.out.println(
                "\tколичество товара на складе : " + counter);
        notify();
    }
}
