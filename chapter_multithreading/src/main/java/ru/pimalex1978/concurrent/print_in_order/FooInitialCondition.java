package ru.pimalex1978.concurrent.print_in_order;

/* LeetCode #1114 - Print in Order (concurrency)
 * Разбор задачи: https://www.youtube.com/watch?v=pyZSh84eY0I
 * Один и то тже экземпляр Foo будет передан трем разным потока. Поток А вызовет метод first(),
 * поток В вызовет second(), а поток С вызовет third().
 * Разработаем программу так, что бы гарантировать, что second() вызовется после first(),
 * а third() после second()
 * Сталкиваемся и боремся с таким понятием, как race-condition */
public class FooInitialCondition {
    public FooInitialCondition() {
    }

    public void first(Runnable printFirst) throws InterruptedException {

        //printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        //printFirst.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        //printFirst.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
