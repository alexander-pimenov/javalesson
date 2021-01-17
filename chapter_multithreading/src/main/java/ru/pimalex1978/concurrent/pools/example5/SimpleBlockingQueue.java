package ru.pimalex1978.concurrent.pools.example5;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("monitor")
    private final Object monitor = this;

    private Queue<T> queue = new LinkedList<>();

    private final int bound;

    public SimpleBlockingQueue(int bound) {
        this.bound = bound;
    }

    //добавление элемента в очередь
    public void offer(T value) {
        synchronized (monitor) {
            try {
                while (queue.size() == bound) {
                    System.out.printf("%s wait%n", Thread.currentThread().getName());
                    wait();
                }
                queue.add(value);
                notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    //извлечение элемента из начала очереди с его удалением.
    public T poll() {
        synchronized (monitor) {
            T result = null;
            try {
                while (queue.isEmpty()) {
                    System.out.printf("%s wait%n", Thread.currentThread().getName());
                    wait();
                }
                result = queue.poll();
                notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return result;
        }
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread[] producers = new Thread[10];
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(() -> {
                int num = (int) (Math.random() * 100);
                System.out.printf("%s produce %d%n", Thread.currentThread().getName(), num);
                queue.offer(num);
            });
            producers[i].setName("Producer" + (i + 1));
        }
        Thread[] consumers = new Thread[10];
        for (int i = 0; i < producers.length; i++) {
            consumers[i] = new Thread(() -> {
                int num = queue.poll();
                System.out.printf("%s consume %d%n", Thread.currentThread().getName(), num);
            });
            consumers[i].setName("Consumer" + (i + 1));
        }
        for (int i = 0; i < producers.length; i++) {
            consumers[i].start();
            producers[i].start();
        }
    }
}