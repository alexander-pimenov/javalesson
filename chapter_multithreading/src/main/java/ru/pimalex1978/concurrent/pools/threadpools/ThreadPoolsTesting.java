package ru.pimalex1978.concurrent.pools.threadpools;

/*https://github.com/kishanjavatrainer/ExecutorsWithMutipleWorkersThreadPoolExample/blob/master/ExecutorsWithMutipleWorkersThreadPoolExample/src/com/infotech/client/ClientTest.java
 *
 * Настоящая мощь ExecutorService проявляется в том, что мы создаем пул потоков
 * и выполняем несколько задач одновременно в пуле потоков. В этом примере мы увидим,
 * как можно создать службу-исполнитель ExecutorService, которая использует пул потоков
 * (ThreadPool) и выполняет несколько задач одновременно.
 *
 * Шаги, которым нужно следовать:
 * 1. Создайте задачу (Runnable Object) для выполнения.
 * 2. Создать пул исполнителей (Executor Pool) с помощью исполнителей (Executors).
 * 3. Передача задач (tasks) в пул исполнителей (Executor Pool).
 * 4. Завершение работы пула исполнителей (Executor Pool). Т.е. его закрытие.
 *
 * В примере видно, что main поток начался и закончился, запустился пул потоков,
 * который выполняет поступающие задания (tasks).
 *
 * */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolsTesting {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started.");

        //Создаем задачи (Runnable Object) для выполнения. (шаг 1)
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing Task1 inside : " + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException();
//                    e.printStackTrace();
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
                    throw new IllegalStateException();
//                    e.printStackTrace();
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
                    throw new IllegalStateException();
//                    e.printStackTrace();
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
                    throw new IllegalStateException();
//                    e.printStackTrace();
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
                    throw new IllegalStateException();
//                    e.printStackTrace();
                }
                System.out.println("My task5 end..");
            }
        };

        Runnable task6 = () -> {
            System.out.println("Executing Task6 inside : " + Thread.currentThread().getName()); //Выполнение Task6 внутри:
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException();
//                e.printStackTrace();
            }
            System.out.println("My task6 end..");
        };

        //Создаем пул исполнителей (Executor Pool) с помощью исполнителей (Executors). (шаг 2)
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // создаем пул потоков с количеством ядер процессора:
//        ExecutorService executorService = Executors.newFixedThreadPool(
//                Runtime.getRuntime().availableProcessors());

        //передаем объекты Runnable в пул для выполнения (шаг 3)
        executorService.submit(task1);
        executorService.submit(task2);
        executorService.submit(task3);
        executorService.submit(task4);
        executorService.submit(task5);
        executorService.submit(task6);

        //завершаем пул потоков (шаг 4)
        //.shutdown() говорит что мы перестаем принимать новые задания и
        //беремся за выполнения заданий, которые были переданы с помощью
        //метода submit().
        //shutdown() чем то похож на метод start() для потока. Из него
        //выходим мгновенно и идем дальше по коду.
        executorService.shutdown();
//        executorService.shutdownNow();

        //Метод .awaitTermination() - "ожидание окончания" - с указанием сколько
        //будем ждать выполнения работы потоками. Укажем 1 день.
        //На этом методе поток main остановится и не пойдет дальше и будет
        //ждать либо выполнения потоками работы либо истечения срока. И только
        //потом пойдет дальше.
//        executorService.awaitTermination(5, TimeUnit.MINUTES);

        /*Этот блок нужен, чтобы посмотреть, когда потоки станут Terminated.
         * boolean isTerminated() (Прекращено) - Возвращает {true}, если все задачи были выполнены
         * после завершения работы.
         * Обратите внимание, что {isTerminated} никогда не будет {true}, если сначала
         * не было вызвано {shutdown} или {shutdownNow}.*/
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(100);
                System.out.println("I am hear!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + " finished.");
    }
}

//Output:
//main started.
//main finished.
//Executing Task1 inside : pool-1-thread-1
//Executing Task2 inside : pool-1-thread-2
//Executing Task3 inside : pool-1-thread-3
//My task1 end..
//Executing Task4 inside : pool-1-thread-1
//My task2 end..
//Executing Task5 inside : pool-1-thread-2
//My task3 end..
//Executing Task6 inside : pool-1-thread-3
//My task4 end..
//My task6 end..
//My task5 end..