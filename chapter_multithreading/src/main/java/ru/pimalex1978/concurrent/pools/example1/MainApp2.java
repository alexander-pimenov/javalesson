package ru.pimalex1978.concurrent.pools.example1;

import java.util.concurrent.TimeUnit;

public class MainApp2 {
    public static void main(String[] args) {
        //Создаем задачи (Runnable Object) для выполнения. (шаг 1)
        Runnable task1 = () -> {
            System.out.println("Executing Task1 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
            }
            System.out.println("My task1 end..");
        };

        Runnable task2 = () -> {
            System.out.println("Executing Task2 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
            }
            System.out.println("My task2 end..");
        };

        Runnable task3 = () -> {
            System.out.println("Executing Task3 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
            }
            System.out.println("My task3 end..");
        };

        Runnable task4 = () -> {
            System.out.println("Executing Task4 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
            }
            System.out.println("My task4 end..");
        };

        Runnable task5 = () -> {
            System.out.println("Executing Task5 inside : " + Thread.currentThread().getName()); //Выполнение Task5 внутри:
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
            }
            System.out.println("My task5 end..");
        };

        Runnable task6 = () -> {
            System.out.println("Executing Task6 inside : " + Thread.currentThread().getName()); //Выполнение Task6 внутри:
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                //      e.printStackTrace();

            }
            System.out.println("My task6 end..");
        };

        //создаем пул исполнителей (ШАГ 2)
        ThreadPool threadPool = new ThreadPool();

        //передаем объекты в пул (шаг 3)
        try {
            threadPool.work(task1);
            threadPool.work(task2);
            threadPool.work(task3);
            threadPool.work(task4);
            threadPool.work(task5);
            threadPool.work(task6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //завершаем работу потоков в пуле (шаг 4)
        threadPool.shutdown();
    }
}
