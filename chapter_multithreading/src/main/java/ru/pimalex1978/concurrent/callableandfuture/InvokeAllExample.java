package ru.pimalex1978.concurrent.callableandfuture;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/*https://www.callicoder.com/java-callable-and-future-tutorial/
 *
 * Мы можем выполнить несколько задач, передав в invokeAll() метод коллекцию Callables.
 * invokeAll() возвращает список futures (фьючеров). Любой вызов future.get() будет заблокирован,
 * пока не будут завершены все futures (фьючеры).
 * В приведенной программе первый вызов future.get() оператора блокируется до тех пор, пока не
 * будут завершены все фьючеры, т.е. результаты будут распечатаны через 5 секунд.
 * */
public class InvokeAllExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<String> task1 = () -> {
            Thread.sleep(2000);
            return "Result of Task1";
        };

        Callable<String> task2 = () -> {
            Thread.sleep(1000);
            return "Result of Task2";
        };

        Callable<String> task3 = () -> {
            Thread.sleep(5000);
            return "Result of Task3";
        };

        //поместим наши Callable в список.
        List<Callable<String>> taskList = Arrays.asList(task1, task2, task3);

        //Возвращает результат всех вызовов.
        List<Future<String>> futures = executorService.invokeAll(taskList);

        for (Future<String> future : futures) {
            //Результат печатается только после завершения всех фьючеров. (т.е. через 5 секунд) !!!
            System.out.println(future.get());
        }

        executorService.shutdown();
    }
}
