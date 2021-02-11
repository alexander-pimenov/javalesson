package ru.pimalex1978.concurrent.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//https://www.youtube.com/watch?v=3cgZbACBpxI&t=605s
//Deadlock in Java - Jakob Jenkov
//http://tutorials.jenkov.com/java-concurrency/deadlock.html
public class DeadlockExampleSync {
    public static void main(String[] args) {

        Object lock1 = new Object();
        Object lock2 = new Object();

        Runnable runnable1 = new RunnableSync1(lock1, lock2);
        Runnable runnable2 = new RunnableSync2(lock1, lock2);

        Thread th1 = new Thread(runnable1);
        Thread th2 = new Thread(runnable2);

        th1.start();
        th2.start();
    }
}
