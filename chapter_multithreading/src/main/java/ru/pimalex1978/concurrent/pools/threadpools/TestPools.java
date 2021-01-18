package ru.pimalex1978.concurrent.pools.threadpools;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * http://espressocode.top/thread-pools-java/
 * В следующем уроке мы рассмотрим базовый пример исполнителя пула
 * потоков — FixedThreadPool.
 * Java-программа для иллюстрации ThreadPool.
 *
 * Шаги, которым нужно следовать:
 * 1. Создайте задачу (Runnable Object) для выполнения.
 * 2. Создать пул исполнителей (Executor Pool) с помощью исполнителей (Executors).
 * 3. Передача задач (tasks) в пул исполнителей (Executor Pool).
 * 4. Завершение работы пула исполнителей (Executor Pool). Т.е. его закрытие.
 *
 * При запуске программы будет видно, что задача 4 или задача 5 выполняются
 * только тогда, когда поток в пуле становится свободным. До этого
 * дополнительные задачи помещаются в очередь.
 * Пул потоков, выполняет сначала первые три задачи, а потом забирает на
 * выполнение 4-ю и 5-ю.
 *
 * */

// Задача класса для выполнения (Шаг 1)
class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    // Работает task таким образом:
    // - печатает имя задачи и спит в течение 1 с
    // - весь этот процесс повторяется 5 раз
    @Override
    public void run() {
        try {
            for (int i = 0; i <= 5; i++) {
                if (i == 0) {
                    Date d = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                    // печатает время инициализации для каждой задачи
                    System.out.println("Initialization Time for"
                            + " task name - " + name + " = " + ft.format(d));
                } else {
                    Date d = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                    // печатает время выполнения для каждой задачи (Executing Time for task)
                    System.out.println("Executing Time for task name - " +
                            name + " = " + ft.format(d));
                }
                TimeUnit.SECONDS.sleep(1);
//                Thread.sleep(1000);
            }
            System.out.println(name + " complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class TestPools {
    // Максимальное количество потоков в пуле потоков
    static final int MAX_T = 3;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started.");


        // создает пять задач (шаг 1)
        Runnable r1 = new Task("task 1");
        Runnable r2 = new Task("task 2");
        Runnable r3 = new Task("task 3");
        Runnable r4 = new Task("task 4");
        Runnable r5 = new Task("task 5");

        // создаем пул потоков с номером MAX_T
        // потоки как фиксированный размер пула (шаг 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // создаем пул потоков с количеством ядер процессора:
//        ExecutorService pool = Executors.newFixedThreadPool(
//                Runtime.getRuntime().availableProcessors());


        // передаем объекты Task в пул для выполнения (шаг 3)
        // используем метод или submit() или execute()
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);

        // закрытие пула (шаг 4)
        //.shutdown() говорит что мы перестаем принимать новые задания и
        //беремся за выполнения заданий, которые были переданы с помощью
        //метода submit() или execute().
        //shutdown() чем то похож на метод start() для потока. Из него
        //выходим мгновенно и идем дальше по коду.
        pool.shutdown();
//        pool.shutdownNow();

        //Метод .awaitTermination() - "ожидание окончания" - с указанием сколько
        //будем ждать выполнения работы потоками. Укажем 1 день.
        //На этом методе поток main остановится и не пойдет дальше и будет
        //ждать либо выполнения потоками работы либо истечения срока. И только
        //потом пойдет дальше.
//        pool.awaitTermination(15, TimeUnit.MINUTES);

        /*Этот блок нужен, чтобы посмотреть когда потоки станут Terminated.
         * boolean isTerminated() (Прекращено) - Возвращает {true}, если все задачи были выполнены
         * после завершения работы.
         * Обратите внимание, что {isTerminated} никогда не будет {true}, если сначала
         * не было вызвано {shutdown} или {shutdownNow}.*/
        while (!pool.isTerminated()) {
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
//Initialization Time for task name - task 2 = 01:17:53
//Initialization Time for task name - task 1 = 01:17:53
//Initialization Time for task name - task 3 = 01:17:53
//Executing Time for task name - task 2 = 01:17:55
//Executing Time for task name - task 1 = 01:17:55
//Executing Time for task name - task 3 = 01:17:55
//Executing Time for task name - task 1 = 01:17:56
//Executing Time for task name - task 2 = 01:17:56
//Executing Time for task name - task 3 = 01:17:56
//Executing Time for task name - task 1 = 01:17:57
//Executing Time for task name - task 2 = 01:17:57
//Executing Time for task name - task 3 = 01:17:57
//Executing Time for task name - task 1 = 01:17:58
//Executing Time for task name - task 2 = 01:17:58
//Executing Time for task name - task 3 = 01:17:58
//Executing Time for task name - task 1 = 01:17:59
//Executing Time for task name - task 2 = 01:17:59
//Executing Time for task name - task 3 = 01:17:59
//task 1 complete
//Initialization Time for task name - task 4 = 01:18:00
//task 2 complete
//Initialization Time for task name - task 5 = 01:18:00
//task 3 complete
//Executing Time for task name - task 4 = 01:18:01
//Executing Time for task name - task 5 = 01:18:01
//Executing Time for task name - task 4 = 01:18:02
//Executing Time for task name - task 5 = 01:18:02
//Executing Time for task name - task 4 = 01:18:03
//Executing Time for task name - task 5 = 01:18:03
//Executing Time for task name - task 4 = 01:18:04
//Executing Time for task name - task 5 = 01:18:04
//Executing Time for task name - task 4 = 01:18:05
//Executing Time for task name - task 5 = 01:18:05
//task 4 complete
//task 5 complete

