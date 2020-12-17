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
 * Методы produce() и consume() работают в нашем примере в бесконечных циклах.
 * Т.е. очередь постоянно наполняется числами продюсером и эти же числа с
 * помощью консюмера достаются из очереди.
 */
public class TestProducerConsumer {
    //Сразу устанавливаем предельный размер очереди, capacity 10.
    //Работать будем с целыми числами <Integer>.
    //static чтоб иметь к ней доступ в статических методах.
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {

        /*Создаем два потока, один Производитель, второй - Потребитель.*/
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //вызовем метод produce()
                //Будем заполнять очередь.
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
                //Будем забирать из очереди элементы.
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

        //Ждем в потоке main выполнения потоков, чтобы метод main
        //не закончил выпонение работы.
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Этот метод будет реализовывать Производителя.
     * Т.е. изображаем поток, который пишет, т.е. производит, что то.
     * Будет класть элементы в очередь.
     */
    private static void produce() throws InterruptedException {

        //Будем генерировать случайные числа.
        Random random = new Random();
        //Будем крутиться в вечном цикле.
        //Т.к. здесь на используем никаких засыпаний или других
        //задерживающих условий,то очередь быстро наполниться числами.
        while (true) {
            /* Будем генерировать и закидывать в очередь случайные числа от 0 до 99
             * Используем put() - потокобезопасный метод - этот метод
             * добавляет элемент в очередь,
             * но если очередь заполнена, то БУДЕТ ЖДАТЬ пока там освободится
             * место!!!
             * Это благодаря использованию ArrayBlockingQueue<>(10)*/
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
            //Спим чтобы каждые 100 мс брать элемент из очереди.
            //Чтобы реже брать элементы из очереди поставим условие
            // random.nextInt(10) == 5, т.е. если число будет =5, то
            //будем забирать число из начала очереди.
            Thread.sleep(100);
            if (random.nextInt(10) == 5) {
                /*Используем take() - потокобезопасный метод - этот метод
                 * забирает и удаляет элемент из очереди,
                 * но если очередь пуста, то БУДЕТ ЖДАТЬ пока там что то
                 * появится!!!*/
                System.out.println("Taken item from the queue " + queue.take());
                //Посмотрим на размер очереди
                System.out.println("Queue size is " + queue.size());
            }
        }
    }
}
