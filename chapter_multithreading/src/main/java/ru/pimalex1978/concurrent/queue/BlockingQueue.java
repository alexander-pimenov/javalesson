package ru.pimalex1978.concurrent.queue;

import java.util.ArrayList;

/**
 * Класс блокирующей очереди.
 * Методы добавления элемента в очередь put() и получения элемента из
 * очереди get() синхронизованны. Монитором синхронизации выступает
 * сам объект блокирующей очереди BlockingQueue.
 * Наша очередь параметризована объектом Runnable.
 */
public class BlockingQueue {
    private ArrayList<Runnable> tasks = new ArrayList<>();

    /**
     * Метод добавления элемента в очередь.
     *
     * @param task объект, который будем добавлять в очередь.
     */
    synchronized void put(Runnable task) {
        tasks.add(task);
        notify();
    }

    /**
     * Метод получения элемента из очереди.
     *
     * @return возвращаем полученный объект, хранящийся в очереди.
     */
    synchronized Runnable get() {
        /*Пока в очереди ничего нет - ждать.*/
        while (tasks.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*Берем первый элемент из очереди.*/
        Runnable task = tasks.get(0);
        /*Удаляем этот элемент из очереди.*/
        tasks.remove(task);
        /*Возвращаем взятый элемент.*/
        return task;
    }
}
