package ru.pimalex1978.concurrent.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * https://www.baeldung.com/java-asynchronous-programming
 * https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-concurrency-advanced-3/src/main/java/com/baeldung/async/JavaAsync.java
 */
public class JavaAsync {

    private static final ExecutorService threadpool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int number = 20;

        //Thread Example
        factorialUsingThread(number).start();

        //FutureTask Example
        Future<Long> futureTask = factorialUsingFutureTask(number);
        System.out.println("Factorial of " + number + " by 'futureTask' is: " + futureTask.get());

        // CompletableFuture Example
        Future<Long> completableFuture = factorialUsingCompletableFuture(number);
        System.out.println("Factorial of " + number + " by 'completableFuture' is: " + completableFuture.get());

        //Один главный поток
        long factorial = factorial(number);
        System.out.println("Factorial of " + number + " by 'main method' is: " + factorial);
    }

    /**
     * Finds factorial of a number
     *
     * @param number
     * @return
     */
    public static long factorial(int number) {
        long start = System.currentTimeMillis();
        long result = 1;
        for (int i = number; i > 0; i--) {
            result *= i;
        }
        System.out.println(" Метод factorial: Затраченое на это время: " + (System.currentTimeMillis() - start));
        return result;
    }

    /**
     * Finds factorial of a number using Thread
     *
     * @param number
     * @return
     */
    public static Thread factorialUsingThread(int number) {
        long start = System.currentTimeMillis();
        Thread newThread = new Thread(() -> {
            System.out.println("Factorial of " + number + " by 'usingThread' is: " + factorial(number));
        });

        System.out.println(" Метод factorialUsingThread: Затраченое на это время: " + (System.currentTimeMillis() - start));
        return newThread;
    }

    /**
     * Finds factorial of a number using FutureTask
     *
     * @param number
     * @return
     */
    public static Future<Long> factorialUsingFutureTask(int number) {
        long start = System.currentTimeMillis();
        Future<Long> futureTask = threadpool.submit(() -> factorial(number));

        while (!futureTask.isDone()) {
            System.out.println("FutureTask is not finished yet...");
        }

        System.out.println(" Метод factorialUsingFutureTask: Затраченое на это время: " + (System.currentTimeMillis() - start));
        return futureTask;
    }

    /**
     * Finds factorial of a number using CompletableFuture
     *
     * @param number
     * @return
     */
    public static Future<Long> factorialUsingCompletableFuture(int number) {
        long start = System.currentTimeMillis();
        CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> factorial(number));
        System.out.println(" Метод factorialUsingCompletableFuture: Затраченое на это время: " + (System.currentTimeMillis() - start));
        return completableFuture;
    }
}
