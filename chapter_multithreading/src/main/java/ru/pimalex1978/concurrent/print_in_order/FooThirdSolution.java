package ru.pimalex1978.concurrent.print_in_order;

import java.util.concurrent.Semaphore;
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
 * Здесь будем использовать Семафоры. Можем поставить две перегородки.*/
public class FooThirdSolution {
    //Роль перегородки между тремя методами выполняют два семафора.
    private Semaphore semaphore1 = new Semaphore(0);
    private Semaphore semaphore2 = new Semaphore(0);

    public FooThirdSolution() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        //printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        semaphore1.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        //ставим перегородку 1 наместо
        semaphore1.acquire();
        //printFirst.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        //освобождаем дорогу к третьему методу убирая перегородку
        semaphore2.release();
    }

    synchronized public void third(Runnable printThird) throws InterruptedException {
        //ставим перегородку 2 наместо
        semaphore2.acquire();
        //printFirst.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
