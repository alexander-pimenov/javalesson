package ru.pimalex1978.concurrent.pools.example3;

import java.util.concurrent.TimeUnit;

public class MainApp {
    public static void main(String[] args) {
        //Создаем задачи (Runnable Object) для выполнения. (шаг 1)
        Runnable task1 = () -> {
            System.out.println("Executing Task1 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
//                Thread.currentThread().interrupt();

            }
            System.out.println("My task1 end..");
        };

        Runnable task2 = () -> {
            System.out.println("Executing Task2 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
//                Thread.currentThread().interrupt();

            }
            System.out.println("My task2 end..");
        };

        Runnable task3 = () -> {
            System.out.println("Executing Task3 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
//                Thread.currentThread().interrupt();

            }
            System.out.println("My task3 end..");
        };

        Runnable task4 = () -> {
            System.out.println("Executing Task4 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
//                Thread.currentThread().interrupt();

            }
            System.out.println("My task4 end..");
        };

        Runnable task5 = () -> {
            System.out.println("Executing Task5 inside : " + Thread.currentThread().getName()); //Выполнение Task5 внутри:
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
//                Thread.currentThread().interrupt();

            }
            System.out.println("My task5 end..");
        };

        Runnable task6 = () -> {
            System.out.println("Executing Task6 inside : " + Thread.currentThread().getName()); //Выполнение Task6 внутри:
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                //      e.printStackTrace();
//                Thread.currentThread().interrupt();
            }
            System.out.println("My task6 end..");
        };

        //создаем пул исполнителей (ШАГ 2)
        ThreadPool threadPool = new ThreadPool(2);

        //
        threadPool.work(task1);
        threadPool.work(task2);
        threadPool.work(task3);
        threadPool.work(task4);
        threadPool.work(task5);
        threadPool.work(task6);

        //
        threadPool.shutdown();

    }
}
