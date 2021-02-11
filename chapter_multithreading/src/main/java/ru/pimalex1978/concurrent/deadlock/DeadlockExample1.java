package ru.pimalex1978.concurrent.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//https://www.youtube.com/watch?v=3cgZbACBpxI&t=605s
//Deadlock in Java - Jakob Jenkov
//http://tutorials.jenkov.com/java-concurrency/deadlock.html
public class DeadlockExample1 {
    public static void main(String[] args) {

        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Runnable runnable1 = new Runnable1(lock1, lock2);
        Runnable runnable2 = new Runnable2(lock1, lock2);

        Thread th1 = new Thread(runnable1);
        Thread th2 = new Thread(runnable2);

        th1.start();
        th2.start();
    }
}
