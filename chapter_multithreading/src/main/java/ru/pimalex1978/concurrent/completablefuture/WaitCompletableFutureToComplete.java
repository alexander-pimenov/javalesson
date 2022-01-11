package ru.pimalex1978.concurrent.completablefuture;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*Пример создания и применения CompletableFuture */
public class WaitCompletableFutureToComplete {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<String> completableFuture = new CompletableFuture<String>();

        waitCompletableFutureToComplete(completableFuture);

        waitCompletableFutureToComplete(CompletableFuture.runAsync(() -> System.out.println("Hi_3")));

        //==создание  CF==
        //Разница в том, что supplyAsync() принимает Supplier, а runAsync -> Runnable.
        // Проще говоря, с помощью supplyAsync() можно вернуть результат, с runAsync() - нельзя.
        //метод supplyAsync()
        //где future исполнится в ForkJoinPool.commonPool(), т.к. мы не указывали ему Executor.
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hi_1");

        // Если мы хотим указать где будет исполняться future то передаем Executor вторым параметром.
        CompletableFuture<String> future2 = CompletableFuture
                .supplyAsync(() -> "Hi_2", Executors.newCachedThreadPool());

        //runAsync()
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> System.out.println("Hi_3"));

        CompletableFuture<Void> future4 = CompletableFuture
                .runAsync(() -> System.out.println("Hi_4"), Executors.newCachedThreadPool());


        //===Получение результата==
        //Но стоит помнить, что вызов этого метода блокирующий, поток будет ждать пока
        // CompletableFuture не вернет результат и о асинхронности можно забыть.
        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);         // имитируем долгое выполнение
            } catch (InterruptedException e) {
            } //пустой catch-block просто для примера
            return "Hi_5";
        });
        System.out.println(future5.get()); //output Hi

        //Добавление callback
        //Более приемлемым способом обработать результат работы CompletableFuture есть callback.
        //Если после выполенения задачи мы хотим вывести ее на екран, это будет выглядеть так
        CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> "Hi_6");
        future6.thenAccept(result -> System.out.println(result));
        future6.get();

        //Добавление нескольких callback
        //Для добавления несколько callback метод thenAccept() не подоходит, поскольку Consumer
        // ничего не возвращает. Для этого нужно использовать другой метод - thenApply(), который принимает Function.
        CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> "Hi_7");

        future7.thenApply(result -> {
            System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId());
            System.out.println(result + " all"); //output Hi all
            return result;
        });

        future7.thenApply(result -> {
            System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId());
            System.out.println(result + ", world!"); //output Hi, world!
            return result;
        });
        future7.get();

        //Нужно помнить, что функция в thenApply() исполняется в том же потоке, где вызывается !!!
        // Если же использовать  thenApplyAsync(),
        // тогда функция будет исполнена как отдельная задача в ForkJoinPool.commonPool !!!
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hi");

        CompletableFuture<String> stringCompletableFuture = future.thenApplyAsync(result -> {
            System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId());
            System.out.println(result + " all"); //output Hi all
            return result;
        });
        String s = stringCompletableFuture.get();
        System.out.println(s); //Hi
        //Тут уже пришлось подождать пока функция выполнится в отдельном потоке и использовать Thread.sleep()
        TimeUnit.MILLISECONDS.sleep(100);

    }

    /**
     * Метод ожидающий выполнения CompletableFuture или завершающий ожидание через 1 минуту
     * @param completableFuture входящий параметр
     * @param <T> дженерик
     * @throws InterruptedException возможное исключение
     */
    private static <T> void waitCompletableFutureToComplete(CompletableFuture<T> completableFuture) throws InterruptedException {
        int count = 0;
        if (!completableFuture.isDone()) {
            var until = LocalDateTime.now().plus(1, ChronoUnit.MINUTES);
            while (!completableFuture.isDone()) {
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.print(count++ + "\r");
                if (LocalDateTime.now().isAfter(until)) {
                    completableFuture.cancel(true);
                }
            }
        }
    }
}
