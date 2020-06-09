package ru.pimalex1978.concurrent.synchronize;

/**
 * В эотом присере смотрим каким будет выполнение программы.
 */

public class Example implements Runnable {
    int x, y;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                x = 12;
                y = 12;
            }
            System.out.print(" i = " + i + "\t");
            System.out.println(x + " " + y + " ");
        }
    }

    public static void main(String[] args) {
        Example run = new Example();
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        t1.start();
        t2.start();
    }
}
