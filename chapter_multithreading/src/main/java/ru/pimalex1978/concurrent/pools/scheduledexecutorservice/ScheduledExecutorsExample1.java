package ru.pimalex1978.concurrent.pools.scheduledexecutorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*scheduledExecutorService.schedule() функция принимает Runnable значение
 * задержки и единицу задержки. Вышеупомянутая программа выполняет задачу через 5 секунд
 * с момента отправки.*/
public class ScheduledExecutorsExample1 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
//            System.out.println("Executing Task At " + System.currentTimeMillis());
            System.out.println("Executing Task At " + System.nanoTime());
        };

//        System.out.println("Submitting task at " + System.currentTimeMillis() + " to be executed after 5 seconds.");
        System.out.println("Submitting task at " + System.nanoTime() + " to be executed after 5 seconds.");
        scheduledExecutorService.schedule(task, 5, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(task, 6, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(task, 7, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
    }
}
