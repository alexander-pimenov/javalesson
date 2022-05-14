package ru.pimalex1978.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Пример блокирующей очереди.
 * Здесь очередь берет на себя всю работу по синхронизации и
 * взаимодействию между двумя потоками.
 * В самих потоках никакой синхронизации писать не нужно.
 * Достаточно только добавлять из потока 'производитель' в очередь
 * элементы и забирать из очереди элементы в потоке 'потребителе'.
 * Т.е. всю синхронизацию между потоками выполняет готовая
 * блокирующая очередь ArrayBlockingQueue.
 * Хотя источник данных может работать быстрее, чем изымает из очереди элементы
 * потребитель. Очередь блокируется и ждет пока из нее возьмут элемент,
 * и только после этого в нее опять можно записать элемент от производителя.
 * Можно рекомендовать пользоваться такими вещами и тогда наши
 * многопоточные программы будут хорошо работать.
 * (Использовал для примера видео Sergey Arkhipov)
 *
 * @author Alexander Pimenov (pimalex1978@ya.ru)
 * @version v1.0
 */

public class BlockingQueueSample {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started.");

        //Контейнер - блокирующая очередь на массиве.
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3); //размер очереди 3 элемента.

        //Поток ПРОИЗВОДИТЕЛЬ.
        //Он в цикле добавляет в очередь слова из массива words, с задержкой в 3 сек.
        Thread producer = new Thread(() -> {
            String[] words = new String[]{"123", "abc", "qwerty", "queue", "stack", "array", "list", "TEST"};

            for (int i = 0; i < words.length && !Thread.interrupted(); ) {
                try {
                    TimeUnit.SECONDS.sleep(3);
//                    Thread.sleep(4000);
                    queue.put(words[i]);
                    System.out.println("producer: записал в очередь " + "\'" + words[i] + "\'"
                            + ", число элементов в очереди: " + queue.size());
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Производитель закончил работу");
        });

        //Поток ПОТРЕБИТЕЛЬ.
        //Он с задержкой в 7 сек. берет слово из очереди и печатает это слово
        // перевернотое наоборот.
        Thread consumer = new Thread(() -> {
            StringBuilder sb = new StringBuilder();
            while (!Thread.interrupted()) {
                try {
                    sb.setLength(0);
                    Thread.sleep(7000);
                    sb.append(queue.take());
                    System.out.println("consumer: обработал из очереди " + "\'" + sb.reverse()
                            + "\'" + ", число элементов: " + queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("Потребитель закончил работу");
        });

        producer.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " finished.");
    }
}
