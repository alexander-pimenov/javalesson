package ru.pimalex1978.queue;

import java.util.ArrayList;

/**
 * Класс блокирующей очереди.
 * Методы добавления элемента в очередь put() и получения элемента из
 * очереди get() синхронизованны. Монитором синхронизации выступает
 * сам объект блокирующей очереди BlockingQueue.
 * Наша очередь параметризована Generic.
 */
public class BlockingQueue<T> {
    /*Контейнер для элементов, хранящихся в очереди.*/
    private ArrayList<T> tasks = new ArrayList<>();

    /**
     * Метод добавления элемента в очередь.
     *
     * @param task объект, который будем добавлять в очередь.
     */
    synchronized void put(T task) {
        tasks.add(task);
        notify();
    }

    /**
     * Метод получения элемента из очереди.
     * Если очередь будет пуста, то поток пытающийся взять элемент из пустой
     * очереди остановится в ожидании. Пока другой поток не положит в
     * эту очередь элементы.
     * <p>
     * Пробрасываем InterruptedException в сигнатуре метода,
     * чтобы правильно обрабатывать его в клиентском коде, т.е.
     * в той программе, которая будет использовать очередь.
     */
    synchronized T get() throws InterruptedException {
        /*Пока в очереди ничего нет - ждать.*/
        while (tasks.isEmpty()) {
            wait();
        }
        /*Берем первый элемент из очереди.*/
        T task = tasks.get(0);
        /*Удаляем этот элемент из очереди.*/
        tasks.remove(task);
        /*Возвращаем взятый элемент.*/
        return task;
    }
}
