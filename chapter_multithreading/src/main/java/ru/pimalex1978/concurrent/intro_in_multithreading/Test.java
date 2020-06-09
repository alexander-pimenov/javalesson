package ru.pimalex1978.concurrent.intro_in_multithreading;

public class Test {
    public static void main(String[] args) {
//        MyThread myThread1 = new MyThread();
//        myThread1.start();
//        MyThread myThread2 = new MyThread();
//        myThread2.start();

        Thread thread = new Thread(new Runner());
        thread.start();

    }
}

class Runner implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            //Будем спать на каждой итерации по 1 сек = 1000 мс
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello from " + MyThread.class.getSimpleName() + " " + i);
        }
    }
}

class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 500; i++) {
            //Будем спать на каждой итерации по 1 сек = 1000 мс
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello from " + MyThread.class.getSimpleName() + " " + i);
        }
    }
}
