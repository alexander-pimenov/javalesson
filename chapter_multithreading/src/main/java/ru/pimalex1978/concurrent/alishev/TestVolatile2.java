package ru.pimalex1978.concurrent.alishev;

import java.util.Scanner;

/**
 * Класс показывает, как работать со словом volatile
 * и прерыванием потока.
 */
public class TestVolatile2 {
    public static void main(String[] args) {

        Thread th1 = new Thread(() -> {
            Thread.currentThread().setName("RUNNABLE_THREAD");
            try {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println(ColorScheme.PURPLE + Thread.currentThread().getName() + " was interrupted");
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println(ColorScheme.BLUE + "Hello from " + Thread.currentThread().getName());

                    Thread.sleep(250);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted");
            }
        });
        MyThread myThread = new MyThread();
        myThread.start();
        th1.start();

        Scanner scanner = new Scanner(System.in);

        /*Здесь мы останавливаемся и ждем следующую линию.
         * Как получили ввод линии, мы пройдем дальше по
         * коду к методу shutDown()*/
        scanner.nextLine(); //1-й enter

        myThread.shutDown();

        scanner.nextLine(); //2-й enter

        th1.interrupt();
        System.out.println(th1.getState());

    }
}

class MyThread extends Thread {
    private boolean running = true;

    @Override
    public void run() {
        Thread.currentThread().setName("MyThread");
        while (running) {
            System.out.println(ColorScheme.GREEN + "Hello from " + Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted");
            }
        }
    }

    /**
     * Метод для присваивания переменной running
     * значения false, чтобы можно было остановить
     * выполнение метода run().
     */
    public void shutDown() {
        this.running = false;
    }
}
