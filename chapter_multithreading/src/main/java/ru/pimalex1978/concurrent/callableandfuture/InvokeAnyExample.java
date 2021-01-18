package ru.pimalex1978.concurrent.callableandfuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*https://www.callicoder.com/java-callable-and-future-tutorial/
 *
 * invokeAny() метод принимает коллекцию Callables и возвращает результат самого быстрого Callable.
 * Обратите внимание, что он НЕ возвращает Future !!! А возвращаемый тип объекта Callable.
 *
 * */
public class InvokeAnyExample {
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

        // Возвращает результат самого быстрого вызова. (в данном случае task2)
        String result = executorService.invokeAny(taskList);

        System.out.println(result);

        executorService.shutdown();
    }
}
