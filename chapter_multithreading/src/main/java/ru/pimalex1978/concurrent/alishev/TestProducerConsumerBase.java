package ru.pimalex1978.concurrent.alishev;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Пример реализации паттерна Производитель-Потребитель (Producer-
 * Consumer) с исрользованием только базовых методов подходящих
 * для многопоточности (wait((), notify())
 */
public class TestProducerConsumerBase {

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}

class ProducerConsumer {
    //Создадим обычную очередь для работы с ней.
    private Queue<Integer> queue = new LinkedList<>();

    //константа, максимальное количество элементов в очереди
    private final int LIMIT = 10;

    //Создадим объект для синхронизации (константа тоже)
    private final Object lock = new Object();

    /**
     * В этом методе мы кладем элемент в очередь.
     * Если очередь заполнена то будем ждать, пока
     * Потребитель заберет хоть оди элемент из очереди.
     *
     * @throws InterruptedException исключение
     */
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                //Условие, если очередь полна то новый элемент не
                //будем добавлять
                //используем цикл как дополнительную проверку
                while (queue.size() == LIMIT) {
                    lock.wait();
                }
                queue.offer(value++);
                //как только в очередь закинем хоть один элемент,
                //то сразу передадим Потребителю уведомление notify(),
                //что он может что то брать из очереди
                lock.notify();//Чаще всего notify вызывается последним в синх. блоке
            }
        }
    }

    /**
     * В этом методе мы будем брать элемент из очереди.
     * Если в очереди нет элементов, то ждем пока Продюсер
     * закинет элементы в очередь.
     *
     * @throws InterruptedException исключение.
     */
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                //когда очередь равна 0, то будем ждать
                //используем цикл как дополнительную проверку
                while (queue.size() == 0) {
                    lock.wait();
                }

                final Integer value = queue.poll();
                System.out.println("Item from queue is " + value);
                System.out.println("Queue size is " + queue.size());
                //Забрав из очереди элемент, мы хотим оповестить
                //Продюсера, что он может еще закинуть в очередь элемент.
                lock.notify(); //Чаще всего notify вызывается последним в синх. блоке
            }
            //Спим 1 сек, чтоб Производитель успевал закинуть в очередь
            //элементы.
            Thread.sleep(1000);
        }
    }
}
