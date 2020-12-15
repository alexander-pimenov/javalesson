package ru.pimalex1978.concurrent.alishev;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Показана работа класса, который синхронизирует потоки.
 * Т.е. то же что и использовать ключевое слово synchronized.
 * Но у этого класса ReentrantLock есть свои плюсы, которые
 * будут рассмотрены в примере с Deadlock. Его используют,
 * чтобы не попасть в Deadlock.
 * Без использования синхронизации между потоками возникнет
 * состояние гонки и переменная counter будет иметь разные
 * значения при разных запусках программы. Хоть мы два раза
 * вызываем методы инкрементирующие counter до 10_000 но из-за
 * состояния гонки результат не всегда будет равен 20_000.
 * <p>
 * Используем для синхронизации потоков класс ReentrantLock,
 * который реализует интерфейс Lock.
 * Только один поток может вызывать метод .lock(), если какой то
 * поток вырывается вперед и завладевает этим методом, то другие
 * потоки будут ждать пока не будет вызван метод .unlock()
 * <p>
 * Если на одном потоке мы несколько раз вызовем метод lock(),
 * то чтобы разлочить его и чтоб другие потоки начали свою работу,
 * нужно столько же раз вызвать метод unlock().
 * <p>
 * Метод unlock() нужно всегда вызывать в блоке finally.
 */
public class TestReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();

        /*В первом потоке вызываем метод firstThread()*/
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.firstThread();
            }
        });
        /*Во втором потоке вызываем метод secondThread()*/
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.secondThread();
            }
        });

        //стартуем два потока
        thread1.start();
        thread2.start();
        //ждем в потоке main исполнения двух потоков
        thread1.join();
        thread2.join();
        //выведем counter на экран
        task.showCounter();
    }
}

/**
 * В этом классе мы что будем делать в двух
 * потоках.
 */
class Task {
    /*Имеем переменную counter*/
    private int counter;
    /*Создаем поле Lock*/
    private Lock lock = new ReentrantLock();

    /**
     * Метод для инкрементирования переменной counter.
     */
    private void increment() {
        for (int i = 0; i < 10000; i++) {
            counter++;
        }
    }

    /**
     * Метод - Работа для первого потока
     * Код будет потокобезопасным, т.е. два потока смогут
     * писать в одну переменную в один и тот же момент времени.
     * (аналогично с блоком synchronized(lock))
     */
    public void firstThread() {
        lock.lock();
        increment();
        lock.unlock();
    }

    /**
     * Метод - Работа для второго потока
     * Код будет потокобезопасным, т.е. два потока смогут
     * писать в одну переменную в один и тот же момент времени.
     * (аналогично с блоком synchronized(lock))
     */
    public void secondThread() {
        lock.lock();
        increment();
        lock.unlock();
    }

    /**
     * Метод для вывода переменной counter на экран.
     */
    public void showCounter() {
        System.out.println("Counter is " + counter);
    }
}