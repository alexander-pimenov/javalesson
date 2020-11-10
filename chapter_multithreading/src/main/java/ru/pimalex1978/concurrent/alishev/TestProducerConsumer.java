package ru.pimalex1978.concurrent.alishev;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Пример реализации паттерна Производитель-Потребитель (Producer-
 * Consumer).
 * Для большинства задач связанных с многопоточностью, есть уже много
 * полезных реализованных классов в пакете java.util.concurrent.*
 * Эти классы потокобезопасны (синхронизованы) и в них не будет возникать
 * состояние гонки.
 * Для его реализации будем использовать ArrayBlockingQueue, потокобезопасная
 * структура очередь на массиве (FIFO).
 * Здесь показана "как бы" система обрабатывающая запросы от клиентов, и она
 * более 10 запросов обработать не может. Если запросов приходит больше, чем 10,
 * то они будут ждать в очереди.
 */
public class TestProducerConsumer {
    //Сразу устанавливаем предельный размер очереди, capacity 10
    //Работать будем с целыми числами Integer
    //static чтоб иметь к ней доступ в статических методах
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {

        /*Создаем два потока, один Производитель, второй - Потребитель.*/
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //вызовем метод produce()
                try {
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //вызовем метод consumer()
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //Запускаем потоки
        thread1.start();
        thread2.start();

        //Ждем выполнения потоков, чтобы метод main не закончил выпонение работы.
        thread1.join();
        thread2.join();
    }

    /**
     * Этот метод будет реализовывать Производителя.
     * Будет класть элементы в очередь.
     */
    private static void produce() throws InterruptedException {

        Random random = new Random();
        while (true) {
            //Будем генерировать и закидывать в очередь случайные числа от 0 до 99
            /*Используем put() - этот метод добавляет элемент в очередь,
             * но если очередь заполнена, то будет ждать пока там освободится
             * место.*/
            queue.put(random.nextInt(100));
        }
    }

    /**
     * Этот метод будет реализовывать Потребителя.
     * Будет брать элементы из очереди.
     */
    private static void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            Thread.sleep(100);
            //Чтобы реже брать элементы из очереди
            if (random.nextInt(10) == 5) {
                /*Используем take() - этот метод забирает и удаляет элемент из
                 * очереди, но если очередь пуста, то будет ждать пока там что то
                 * появится.*/
                System.out.println("Taken item from the queue " + queue.take());
                //Посмотрим на размер очереди
                System.out.println("Queue size is " + queue.size());
            }
        }
    }
}
