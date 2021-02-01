package ru.pimalex1978.concurrent.switcher;

/**
 * В этом задании нужно многопоточные нити сделать последовательными.
 * Есть две нити. Нужно, чтобы сначала нить А печатала на консоль текст,
 * а потом нить В.
 * Нить А всегда печатает первой.
 * Для реализации работы нужно использовались методы wait, notify, notifyAll.
 */
public class Switcher {
    public static void main(String[] args) {
        MasterSlaveBarrier barrier = new MasterSlaveBarrier();

        Thread first = new Thread(
                () -> {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        barrier.tryMaster();
                        barrier.doneMaster();
                    }
                }, "Thread A"
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        barrier.trySlave();
                        barrier.doneSlave();
                    }
                }, "Thread B"
        );
        first.start();
        second.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}