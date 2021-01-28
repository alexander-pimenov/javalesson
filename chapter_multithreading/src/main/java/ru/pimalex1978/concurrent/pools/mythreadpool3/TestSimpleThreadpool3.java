package ru.pimalex1978.concurrent.pools.mythreadpool3;

import java.util.concurrent.TimeoutException;

public class TestSimpleThreadpool3 {
    public static void main(String[] args) {
        SimpleThreadpool threadpool = SimpleThreadpool.getInstance();
        int runnableCount = 10;
        Runnable r;

        for (int i = 0; i < runnableCount; i++) {
            r = new SimpleTask3(i);
            threadpool.execute(r);
        }

        threadpool.stop();

        try {
            threadpool.awaitTermination();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

// Задача класса для выполнения (Шаг 1)
class SimpleTask3 implements Runnable {
    //у каждого потока будет id
    private int id;

    SimpleTask3(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Executing " + this.toString()
                + " inside : " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.toString() + " end..");
    }

    @Override
    public String toString() {
        return "Task{id=" + id + '}';
    }
}

