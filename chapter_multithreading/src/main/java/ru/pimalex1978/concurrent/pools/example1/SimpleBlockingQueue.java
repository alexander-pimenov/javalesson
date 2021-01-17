package ru.pimalex1978.concurrent.pools.example1;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Bounded blocking queue
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private int capacity = 8;

    private int size = 0;

    public synchronized void offer(T value) throws InterruptedException {
        while (size == capacity) {
            wait();
        }
        queue.offer(value);
        System.out.println(String.format("Пушнули %s", value));
        size++;
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (size == 0) {
            wait();
        }
        T rsl = queue.poll();
        size--;
        System.out.println(String.format("Дернули %s", rsl));
        notifyAll();
        return rsl;
    }

    public synchronized int getSize() {
        return size;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}