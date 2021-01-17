package ru.pimalex1978.concurrent.pools.scheduledexecutorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*scheduledExecutorService.scheduleAtFixedRate() принимает значение Runnable,
 * начальную задержку, период выполнения и единицу времени. Он начинает
 * выполнение данной задачи после указанной задержки, а затем выполняет
 * ее периодически в интервале, заданном значением периода.
 *
 * Обратите внимание, что если задача обнаруживает исключение, последующее
 * выполнение задачи подавляется. В противном случае задача завершится
 * только в том случае, если вы либо закроете программу-исполнитель,
 * либо убьете программу.*/
public class ScheduledExecutorsPeriodicExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Executing Task At " + System.nanoTime());
        };

        System.out.println("scheduling task to be executed every 2 seconds with an initial delay of 0 seconds");
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
    }
}
