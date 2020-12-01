package ru.pimalex1978.concurrent.executors;

import static ru.pimalex1978.concurrent.ColorScheme.*;

/**
 * Распространенный способ завершения потока представляет опрос
 * из основного (или другого) потока логической переменной.
 * Например isActive. Сброс в положение false делаем в методе
 * disable().
 */
public class MyThread implements Runnable {

    private boolean isActive;

    //поля типа потока (Daemon | User)
    private boolean isDaemon;

    /**
     * Метод для сбрасывания переменной isActive
     * в состояние false.
     */
    void disable() {
        isActive = false;
    }

    MyThread() {
        isActive = true;
    }

    MyThread(boolean isDaemon) {
        this.isDaemon = isDaemon;
        isActive = true;
    }

    @Override
    public void run() {
        //определение типа потока
        String threadType = isDaemon ? "daemon-" : "user-";

        //описание потока
        String threadDescription = threadType + Thread.currentThread().getName();

        System.out.printf(CYAN + "%s started ... \n", threadDescription);
        int counter = 1; //счетчик циклов
        while (isActive) {
            System.out.println(PURPLE + "Loop " + counter++);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }
        }
        System.out.printf(CYAN + "%s finished ... \n", threadDescription);
    }
}
