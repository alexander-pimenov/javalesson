package ru.pimalex1978.concurrent.print_in_order;

/* LeetCode #1114 - Print in Order (concurrency)
 * Разбор задачи: https://www.youtube.com/watch?v=pyZSh84eY0I
 * Один и то тже экземпляр Foo будет передан трем разным потока. Поток А вызовет метод first(),
 * поток В вызовет second(), а поток С вызовет third().
 * Разработаем программу так, что бы гарантировать, что second() вызовется после first(),
 * а third() после second()
 * Сталкиваемся и боремся с таким понятием, как race-condition */
public class FooFirstSolution {
    //переменная отвечающая за то какой поток может обратиться к нашему методу
    public int threadNumber;

    public FooFirstSolution() {
        threadNumber = 1;
    }

    synchronized public void first(Runnable printFirst) throws InterruptedException {
        //пока threadNumber не =1 мы ждем
        while (threadNumber != 1) {
            wait();
        }
        //printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        //после того как отработал метод, то увеличиваем threadNumber на 1
        threadNumber++;
        //и пробуждаем наш поток
        notifyAll();
    }

    synchronized public void second(Runnable printSecond) throws InterruptedException {
        while (threadNumber != 2) {
            wait();
        }
        //printFirst.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        threadNumber++;
        notifyAll();
    }

    synchronized public void third(Runnable printThird) throws InterruptedException {
        while (threadNumber != 3) {
            wait();
        }
        //printFirst.run() outputs "third". Do not change or remove this line.
        printThird.run();
        threadNumber++;
        notifyAll();
    }
}
