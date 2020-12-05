package ru.pimalex1978.concurrent.intro_in_multithreading;

import static ru.pimalex1978.concurrent.ColorScheme.*;

public class Test {
    public static void main(String[] args) throws InterruptedException {
//        MyThread myThread1 = new MyThread();
//        myThread1.start();
//        MyThread myThread2 = new MyThread();
//        myThread2.start();

        Thread th1 = new Thread(new Runner());
        th1.start();

        Thread th2 = new MyThread();
        th2.start();

        th1.join();
        th2.join();

    }
}

class Runner implements Runnable {
    @Override
    public void run() {
        System.out.printf(RED + "%s started...\n", Thread.currentThread().getName());
        for (int i = 0; i < 500; i++) {
            //Будем спать на каждой итерации по 0.9 сек = 900 мс
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");

                //e.printStackTrace();
            }
            System.out.println(YELLOW + "Hello from " + Runner.class.getSimpleName() + " " + i);
        }
        System.out.printf(RED + "%s finished... \n", Thread.currentThread().getName());
    }
}

class MyThread extends Thread {
    public void run() {
        System.out.printf(PURPLE + "%s started...\n", Thread.currentThread().getName());
        for (int i = 0; i < 500; i++) {
            //Будем спать на каждой итерации по 1 сек = 1000 мс
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");

//                e.printStackTrace();
            }
            System.out.println(BLUE + "Hello from " + MyThread.class.getSimpleName() + " " + i);
        }
        System.out.printf(PURPLE + "%s finished... \n", Thread.currentThread().getName());
    }
}
