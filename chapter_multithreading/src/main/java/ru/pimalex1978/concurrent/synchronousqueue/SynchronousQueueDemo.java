package ru.pimalex1978.concurrent.synchronousqueue;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/*
 * Рассмотрим пример Синхронной очереди. Емкость этой очереди равна 1,
 * но она никогда не может быть заполнена. Т.е. она всегда пустая.
 * Она служит, для синхронизации потоков, т.е. когда один поток Producer
 * делает put(), в эту очередь, то он сразу засыпает, пока другой поток
 * Consumer не сделает take() из этой очереди. Эту данную очередь применяют
 * тогда, когда этим двум потокам нужно обменяться данными. И во воремя
 * обмена данными они должны подождать друг друга. Т.е. пока обмен не
 * произойдет, они не могут продолжать работу.
 * put() блокирует поток пока кто другой не сделает take() и наоборот.
 *
 * Эта очередь удобна, когда нам нужно чтобы два потока работали синхронно
 * не ускорялись и не замедлялись. И они не только подождали друг друга, а
 * еще и данными обменялись.
 *
 * Источник:
 * Блокирующая очередь - Collections #5 - Advanced Java
 * https://www.youtube.com/watch?v=nUYOGkh9XqE
 * */
public class SynchronousQueueDemo {
    private static final SynchronousQueue<Integer> queue = new SynchronousQueue<>();
    private static final int CNT = 5;
    private static Random random = new Random();

    public static void main(String[] args) {

        final Thread producer = new Thread(() -> {
            int random_work_time;
            for (int i = 1; i <= CNT; i++) {
                random_work_time = random.nextInt(700 + 1) + 100;
                try {
                    System.out.println(Thread.currentThread().getName() + " сейчас будет работать.");

                    Thread.sleep(random_work_time); //имитация работы
                    System.out.println(Thread.currentThread().getName() + " поработал. Кладу в очередь элемент " + i);

                    queue.put(i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer");
        producer.start();

        final Thread consumer = new Thread(() -> {
            int random_work_time;
            int j;
            for (int i = 1; i <= CNT; i++) {
                random_work_time = random.nextInt(900 + 1) + 400;
                try {
                    System.out.println(Thread.currentThread().getName() + " жду из очереди элемент.");
                    j = queue.take();
                    System.out.println(Thread.currentThread().getName() + " получил из очереди элемент " + i + " и будет работать.");
                    Thread.sleep(random_work_time); //имитация работы
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer");
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
