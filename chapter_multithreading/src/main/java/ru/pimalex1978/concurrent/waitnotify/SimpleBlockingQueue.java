package ru.pimalex1978.concurrent.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("lock")
    private final Object lock = this;

    private Queue<T> queue = new LinkedList<>();

    private final int limitBound;

    public SimpleBlockingQueue(int limitBound) {
        this.limitBound = limitBound;
    }

    /**
     * Заполняем очередь.
     *
     * @param value значение.
     */
    public void offer(T value) {
        synchronized (lock) {
            try {
                while (queue.size() == limitBound) {
                    System.out.println(String.format("%s waiting...", Thread.currentThread().getName()));
                    lock.wait();
                }
                queue.offer(value);
                System.out.println(String.format("%s put element \"%s\" to the queue. Queue size is %d",
                        Thread.currentThread().getName(), value, queue.size()));
                lock.notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Берем элемент из очереди.
     *
     * @return значение.
     */
    public T poll() {
        synchronized (lock) {
            T value = null;
            try {
                while (queue.size() == 0) {
                    System.out.println(String.format("%s waiting...", Thread.currentThread().getName()));
                    lock.wait();
                }
                value = queue.poll();
                System.out.println(String.format("%s took element \"%s\" from  the queue. Queue size is %d",
                        Thread.currentThread().getName(), value, queue.size()));
                lock.notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return value;
        }
    }

    /**
     * Метод, в котором мы создаем массив потоков из 10 шт.
     * Сперва они заполняют очередь случайными целыми числами.
     * Потом вызываем 10 потоков, которые читают данные из очереди.
     * Видим, что сначала потоки записывают в очередь числа, а
     * потом Консюмер вычитывает эти же числа.
     */
    public static void start1() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(15);

        Thread[] producers = new Thread[10];
        Random random = new Random();
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(
                    () -> {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int num = random.nextInt(100);
                        queue.offer(num);
                    }, "Producer-" + (i + 1));
        }

        Thread[] consumers = new Thread[10];
        for (int i = 0; i < producers.length; i++) {
            consumers[i] = new Thread(
                    () -> {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int num = queue.poll();
                    }, "Consumer-" + (i + 1));
        }

        /*Запускаем сначала потоки producers[i]*/
        for (int i = 0; i < producers.length; i++) {
            producers[i].start();
            try {
                producers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Спим 1 сек
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*Запускаем теперь потоки consumers[i]*/
        for (int i = 0; i < producers.length; i++) {
            consumers[i].start();
            try {
                consumers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод, в котором мы создаем массив потоков из 10 шт.
     * Одновременно в цикле запускаем потоки, заполняющие очередь случайными целыми числами.
     * Т.е. Продюсер. И потоки, которые читают данные из очереди.
     * Видим, что происходит запись одного элемента и следом идет его считывание.
     */
    public static void start2() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);

        Thread[] producers = new Thread[10];
        Random random = new Random();
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(
                    () -> {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int num = random.nextInt(100);
                        queue.offer(num);
                    }, "Producer-" + (i + 1));
        }

        Thread[] consumers = new Thread[10];
        for (int i = 0; i < producers.length; i++) {
            consumers[i] = new Thread(
                    () -> {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int num = queue.poll();
                    }, "Consumer-" + (i + 1));
        }

        /*Запускаем один за одним поток producers[i] и consumers[i]*/
        for (int i = 0; i < producers.length; i++) {
            producers[i].start();
            consumers[i].start();
            try {
                producers[i].join();
                consumers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        start1();
//        start2();

    }
}
