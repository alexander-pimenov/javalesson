package ru.pimalex1978.concurrent.pools.mythreadpool3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestSimpleThreadpool2 {
    public static void main(String[] args) {
        SimpleThreadpool threadpool = SimpleThreadpool.getInstance();
        threadpool.execute(new Task2("Task1"));
        threadpool.execute(new Task2("Task2"));
        threadpool.execute(new Task2("Task3"));

        threadpool.stop();

        try {
            threadpool.awaitTermination();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

// Задача класса для выполнения (Шаг 1)
class Task2 implements Runnable {
    private String name;

    public Task2(String name) {
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

    @Override
    public String toString() {
        return "Task{"
                + "name='" + name + '\''
                + '}';
    }
}
