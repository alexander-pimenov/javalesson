package ru.pimalex1978.concurrent.pools.example6;

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
                Thread.currentThread().interrupt();

            }
            System.out.println("My task1 end..");
        };

        Runnable task2 = () -> {
            System.out.println("Executing Task2 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
                Thread.currentThread().interrupt();

            }
            System.out.println("My task2 end..");
        };

        Runnable task3 = () -> {
            System.out.println("Executing Task3 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
                Thread.currentThread().interrupt();

            }
            System.out.println("My task3 end..");
        };

        Runnable task4 = () -> {
            System.out.println("Executing Task4 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
                Thread.currentThread().interrupt();

            }
            System.out.println("My task4 end..");
        };

        Runnable task5 = () -> {
            System.out.println("Executing Task5 inside : " + Thread.currentThread().getName()); //Выполнение Task5 внутри:
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
//                    e.printStackTrace();
                Thread.currentThread().interrupt();

            }
            System.out.println("My task5 end..");
        };

        Runnable task6 = () -> {
            System.out.println("Executing Task6 inside : " + Thread.currentThread().getName()); //Выполнение Task6 внутри:
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                //      e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            System.out.println("My task6 end..");
        };

        //создаем пул исполнителей (ШАГ 2)
        ThreadPool threadPool = new ThreadPool(2);

        //передаем объекты в пул (шаг 3)
        threadPool.execute(task1);
        threadPool.execute(task2);
        threadPool.execute(task3);
        threadPool.execute(task4);
        threadPool.execute(task5);
        threadPool.execute(task6);

        //завершаем работу потоков в пуле (шаг 4)
        threadPool.shutdown();

        ///////////////RunState////////////////////////
//        int COUNT_BITS = Integer.SIZE - 3;
//        System.out.println("COUNT_BITS = " + COUNT_BITS);
//        int COUNT_MASK = (1 << COUNT_BITS) - 1;
//        System.out.println("COUNT_MASK = " + COUNT_MASK);
//
//        int RUNNING = -1 << COUNT_BITS;
//        int SHUTDOWN = 0 << COUNT_BITS; //=0
//        int STOP = 1 << COUNT_BITS;
//        int TIDYING = 2 << COUNT_BITS;
//        int TERMINATED = 3 << COUNT_BITS;
//        System.out.println("RUNNING =  " + RUNNING + ";\n"
//                + "SHUTDOWN =  " + SHUTDOWN + "\n"
//                + "STOP = " + STOP + "\n"
//                + "TIDYING = " + TIDYING + "\n"
//                + "TERMINATED = " + TERMINATED);
//
        //Output:
        //COUNT_BITS = 29
        //COUNT_MASK = 536870911
        //RUNNING =  -536870912;
        //SHUTDOWN =  0
        //STOP = 536870912
        //TIDYING = 1073741824
        //TERMINATED = 1610612736
    }
}
