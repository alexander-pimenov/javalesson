package ru.pimalex1978.concurrent.pools.example4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    //Создадим обычную очередь для работы с ней.
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    //максимальное количество элементов в очереди
    private final int maxSizeQueue;

    public SimpleBlockingQueue(int maxSizeQueue) {
        this.maxSizeQueue = maxSizeQueue;
    }


    public synchronized void offer(T value) {
        while (queue.size() >= maxSizeQueue) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.offer(value);
        System.out.println("Добавлено " + value + " size = " + queue.size());
        notifyAll();
    }

    public synchronized T poll() {
        T value;
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        value = queue.poll();
        notifyAll();
        System.out.println("извлечено " + value);
        return value;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}