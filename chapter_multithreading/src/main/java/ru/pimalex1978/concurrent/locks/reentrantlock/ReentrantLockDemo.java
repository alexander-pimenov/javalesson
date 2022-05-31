package ru.pimalex1978.concurrent.locks.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/*
 * https://metanit.com/java/tutorial/8.9.php
 * Для управления доступом к общему ресурсу в качестве альтернативы оператору synchronized
 * мы можем использовать блокировки.
 * Функциональность блокировок заключена в пакете java.util.concurrent.locks.
 *
 * Здесь также используется общий ресурс CommonResource, для управления которым создается
 * пять потоков.
 * Только один(!!!) поток имеет доступ к критической секции, а остальные потоки ожидают снятия блокировки.
 * В блоке finally после всей окончания основной работы потока эта блокировка снимается. Причем
 * делается это обязательно в блоке finally, так как в случае возникновения ошибки все остальные
 * потоки окажутся заблокированными, что приведет к дедлоку.
 * */

public class ReentrantLockDemo {
    public static void main(String[] args) {

        CommonResource commonResource = new CommonResource();
        ReentrantLock locker = new ReentrantLock(); // создаем заглушку

        //В цикле запустим 5 потоков
        for (int i = 1; i < 6; i++) {
            Thread t = new Thread(new CountThread(commonResource, locker));
            t.setName("Thread-" + i);
            t.start();
        }
    }
}

//Класс общего ресурса
class CommonResource {

    int x = 0;
}

//Класс использующий общий ресурс
class CountThread implements Runnable {

    private CommonResource res;
    private final ReentrantLock locker;

    CountThread(CommonResource res, ReentrantLock lock) {
        this.res = res;
        locker = lock;
    }

    public void run() {

        locker.lock(); // устанавливаем блокировку
        try {
            res.x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s  %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock(); // снимаем блокировку
        }
    }
}

