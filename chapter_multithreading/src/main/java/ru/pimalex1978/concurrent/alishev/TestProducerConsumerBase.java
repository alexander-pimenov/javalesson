package ru.pimalex1978.concurrent.alishev;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Пример реализации паттерна Производитель-Потребитель (Producer-
 * Consumer) с использованием только базовых методов подходящих
 * для многопоточности (wait((), notify())
 * https://www.udemy.com/course/javarussia/learn/lecture/8982272#questions
 * Методы класса ProducerConsumer produce() и consume() вызываются разными
 * потоками. Один поток будет добавлять какие то данные в очередь, а
 * другой забирать эти данные из очереди. Эти потоки работают совместно.
 * Все обращения из разных потоков к одной очереди Queue<Integer> queue
 * должны быть синхрнизованны.
 * В методах  produce() и consume() используем для примера бесконечные циклы,
 * чтоб программа работала постоянно.
 */
public class TestProducerConsumerBase {

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer(10);

        Thread thread1 = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
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

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ProducerConsumer {
    //Создадим обычную очередь для работы с ней.
    private Queue<Integer> queue = new LinkedList<>();

    //константа, максимальное количество элементов в очереди
    private final int LIMIT;
//    private final int LIMIT = 10;

    //Так же нужно создать конструктор, что бы в параметре задавать нужный
    //размер очереди. Если не использовать конструктор, то инициализировать
    //поле LIMIT нужно сразу целым числом.
    ProducerConsumer(int limit) {
        LIMIT = limit;
    }

    //Создадим объект для синхронизации (константа тоже)
    private final Object lock = new Object();

    /**
     * В этом методе мы кладем элементы value в очередь.
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
                //будем добавлять.
                //Используем цикл while(), как дополнительную проверку,
                //просто хотим себя обезопасить. Мы хотим еще раз
                //удостовериться, что размер очереди не = 10 и
                //что мы получили notify().
                //Можно использовать if(queue.size() == LIMIT).
                //Но тут не будет повторной проверки на 10.
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
                //когда очередь равна 0 (пустая), то будем ждать
                //используем цикл while(), как дополнительную проверку.
                //Просто хотим себя обезопасить. Мы хотим еще раз
                //удостовериться, что размер очереди не = 0 и
                //что мы получили notify().
                //Можно использовать if(queue.size() == 0).
                //Но тут не будет повторной проверки на 0.
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
