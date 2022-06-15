package ru.pimalex1978.concurrent.print_in_order;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* LeetCode #1114 - Print in Order (concurrency)
 * Разбор задачи: https://www.youtube.com/watch?v=pyZSh84eY0I
 * Один и то тже экземпляр Foo будет передан трем разным потока. Поток А вызовет метод first(),
 * поток В вызовет second(), а поток С вызовет third().
 * Разработаем программу так, что бы гарантировать, что second() вызовется после first(),
 * а third() после second()
 * Сталкиваемся и боремся с таким понятием, как race-condition
 *
 * Здесь будем использовать более гибкий ReentrantLock и Condition*/
public class FooSecondSolution {
    //переменная отвечающая за то какой поток может обратиться к нашему методу
    public int threadNumber;
    //поля используемые для блокировки и условия
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public FooSecondSolution() {
        threadNumber = 1;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        //заходим в метод и ставим лок
        lock.lock();
        //Далее работаем в блоке try, т.к. нам понадобится блок finally, чтобы
        //мы могли снять блокировку в любом случае !!!
        try {
            //printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            //после того как отработал метод, то увеличиваем threadNumber на 1
            this.threadNumber++;
            //пробуждаем всех
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while (threadNumber != 2) {
                condition.await();
            }
            //printFirst.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            this.threadNumber++;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    synchronized public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            while (threadNumber != 3) {
                condition.await();
            }
            //printFirst.run() outputs "third". Do not change or remove this line.
            printThird.run();
            this.threadNumber++;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
