package ru.pimalex1978.concurrent.pools.mythreadpool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ThreadPoolApp {
    public static void main(String[] args) {

    }
}

class TaskCyclicBarrier implements Runnable {
    //у каждого потока будет id
    private int id;
    //У всех наших потоков будет один CountDownLatch
    private CyclicBarrier cyclicBarrier;

    public TaskCyclicBarrier(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    public TaskCyclicBarrier(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.println("Executing " + this.toString()
                    + " inside : " + Thread.currentThread().getName());

            Thread.sleep(1000);

            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(this.toString() + " end..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "TaskCyclicBarrier{" +
                "cyclicBarrier=" + cyclicBarrier +
                ", id=" + id +
                '}';
    }
}

class Processor implements Runnable {

    private int id;
    private CountDownLatch countDownLatch;

    Processor(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("Executing " + this.toString()
                    + " inside : " + Thread.currentThread().getName());

            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread with id " + id + " proceeded.");
    }

    @Override
    public String toString() {
        return "Task{"
                + "id=" + id
                + ", countDownLatch=" + countDownLatch
                + '}';
    }
}
