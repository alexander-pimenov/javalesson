package ru.pimalex1978.concurrent.pools.example5;

import org.junit.Test;

import java.util.concurrent.TimeUnit;


public class ThreadPoolTest {

    @Test
    public void threadPoolTest2() {

        //Создаем задачи (Runnable Object) для выполнения. (шаг 1)
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing Task1 inside : " + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("My task1 end..");
            }
        };

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing Task2 inside : " + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("My task2 end..");
            }
        };

        Runnable task3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing Task3 inside : " + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("My task3 end..");
            }
        };

        Runnable task4 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing Task4 inside : " + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("My task4 end..");
            }
        };

        Runnable task5 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing Task5 inside : " + Thread.currentThread().getName()); //Выполнение Task5 внутри:
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("My task5 end..");
            }
        };

        Runnable task6 = () -> {
            System.out.println("Executing Task6 inside : " + Thread.currentThread().getName()); //Выполнение Task6 внутри:
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
            System.out.println("My task6 end..");
        };

        ThreadPool pool = new ThreadPool();
        pool.work(task1);
        pool.work(task2);
        pool.work(task3);
        pool.work(task4);
        pool.work(task5);
        pool.work(task6);

        pool.shutdown();
    }

}