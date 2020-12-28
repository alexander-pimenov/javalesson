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
     *
     * @return возвращаем полученный объект, хранящийся в очереди.
     */
    synchronized T get() {
        /*Пока в очереди ничего нет - ждать.*/
        while (tasks.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*Берем первый элемент из очереди.*/
        T task = tasks.get(0);
        /*Удаляем этот элемент из очереди.*/
        tasks.remove(task);
        /*Возвращаем взятый элемент.*/
        return task;
    }
}
