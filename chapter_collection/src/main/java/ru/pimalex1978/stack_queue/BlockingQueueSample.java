package ru.pimalex1978.stack_queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Пример блокирующей очереди.
 * Здесь очередь берет на себя всю работу по сонхронизации и
 * взаимодествию между двумя потоками.
 * В самих потоках никакой синхронизации писать не нужно.
 * Достаточно только добавлять из потока производитель в очередь
 * элементы и забирать из очереди элементы в потоке потребителе.
 * Т.е. всю синхронизацию между потоками выполняет готовая
 * блокирующая очередь ArrayBlockingQueue.
 * Хотя источник данных может работать быстрее, чем изымает из очереди элементы
 * потребитель. Очередь блокируется и ждет пока из нее возьмут элемент,
 * и только после этого в нее опять можно записать элемент от производителя.
 * Можно рекомендовать пользоваться такими вещами и тогда наши
 * многопоточные программы будут хорошо работать.
 *
 * @author Alexander Pimenov (pimalex1978@ya.ru)
 * @version v1.0
 */

public class BlockingQueueSample {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3); //размер очереди 3 элемента.

        //поток производитель.
        //Он в цикле добавляет в очередь слова из массива words, с задержкой в 5 сек.
        Thread producer = new Thread(() -> {
            String[] words = new String[]{"123", "abc", "qwerty", "queue", "stack", "array", "list"};

            for (int i = 0; i < words.length && !Thread.interrupted(); ) {
                try {
                    Thread.sleep(5000);
                    queue.put(words[i]);
                    System.out.println("producer: записал в очередь " + "\'" + words[i] + "\'" + ", число эелентов в очереди: " + queue.size());
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //поток потребитель.
        //Он с задержкой в 8 сек. берет слово из очереди и печатает это слово перевернотое
        //наоборот.
        Thread consumer = new Thread(() -> {
            StringBuilder sb = new StringBuilder();
            while (!Thread.interrupted()) {
                try {
                    sb.setLength(0);
                    Thread.sleep(8000);
                    sb.append(queue.take());
                    System.out.println("consumer: обработал из очереди " + "\'" + sb.reverse() + "\'" + ", число элементов: " + queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        });

        producer.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumer.start();
    }
}
