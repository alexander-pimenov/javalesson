package ru.pimalex1978.concurrent.synch;

/**
 * В эотом примере смотрим каким будет выполнение программы.
 */

public class Example implements Runnable {
    private int x, y;

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
            System.out.print(Thread.currentThread().getName()
                    + " i = " + i + "\t" + x + " " + y + " \r\n");
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
