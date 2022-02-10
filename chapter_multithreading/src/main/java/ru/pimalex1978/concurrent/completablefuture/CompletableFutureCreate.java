package ru.pimalex1978.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/*https://annimon.com/article/3462*/
public class CompletableFutureCreate {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.
        //Можно создать CompletableFuture, используя конструктор по умолчанию:
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        //Это самый простой CompletableFuture, который можно создать.
        // Чтобы получить результат этого CompletableFuture, можно вызвать get().
        //Метод get() блокирует поток до тех пор, пока Future не завершится.
        // Таким образом, этот вызов заблокирует поток навсегда, потому что Future никогда не завершается.
//        String result = completableFuture.get();
        //Чтобы завершить CompletableFuture вручную, можно использовать метод complete().
        //Все клиенты, ожидающие этот Future, получат указанный результат, а последующие
        // вызовы completableFuture.complete() будут игнорироваться.
//        completableFuture.complete("Результат Future");


        //2.
        // Выполнение асинхронных задач с использованием runAsync()

        // Если вы хотите асинхронно выполнить некоторую фоновую задачу, которая не возвращает,
        // результат, можно использовать метод CompletableFuture.runAsync().
        // Он принимает объект Runnable и возвращает CompletableFuture<Void>.

        // Асинхронно запускаем задачу, заданную объектом Runnable
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("Я " + Thread.currentThread().getName() + "--" + Thread.currentThread().getId()
                        + " буду работать в отдельном потоке, а не в главном. "
                        + "Текущее время: " + System.currentTimeMillis());
                // Имитация длительной работы
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                System.out.println("Я " + Thread.currentThread().getName() + "--" + Thread.currentThread().getId()
                        + " закончил работу.");
            }
        });

        System.out.println("Основной поток пришел сюда. " + Thread.currentThread().getName() + " ожидает ответа от future. "
                + "Текущее время: " + System.currentTimeMillis());
        // Блокировка и ожидание завершения Future
        // Метод CompletableFuture.get() блокирующий. Он ждет, пока Future завершится и вернёт результат.
        future.get();

        //3.
        // Выполнение асинхронной задачи и возврат результата с использованием supplyAsync()

        // CompletableFuture.runAsync() полезен для задач, которые ничего не возвращают.
        // Но что, если всё же нужно вернуть какой-нибудь результат из фоновой задачи?
        // В таком случае вам придёт на помощь метод CompletableFuture.supplyAsync().
        // Он принимает Supplier<T> и возвращает CompletableFuture<T>,
        // где T это тип возвращаемого функцией-поставщиком значения:
        // Запуск асинхронной задачи, заданной объектом Supplier
        // Supplier<T> это функциональный интерфейс, представляющий поставщика результатов.
        // У него есть всего один метод get(), в котором можно указать фоновое задание и вернуть результат.
        CompletableFuture<String> futureSupply = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("Я " + Thread.currentThread().getName() + "--" + Thread.currentThread().getId()
                        + " буду работать в отдельном потоке, а не в главном. "
                        + "Текущее время: " + System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Результат асинхронной задачи";
            }
        });

        // Блокировка и получение результата Future
        // Метод CompletableFuture.get() блокирующий. Он ждет, пока Future завершится и вернёт результат.
        System.out.println("Основной поток пришел сюда. " + Thread.currentThread().getName() + " ожидает ответа от futureSupply. "
                + "Текущее время: " + System.currentTimeMillis());
        // Блокировка и получение результата Future
        String result = futureSupply.get();
        System.out.println(result);


        //4.
        // Все методы CompletableFuture API представлены в двух вариантах: один принимает Executor в качестве аргумента, а второй нет.
        //Вот как можно создать пул потоков и передать его в один из этих методов:
        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture<String> futureSupplyExe = CompletableFuture.supplyAsync(() -> {
            System.out.println("Я " + Thread.currentThread().getName() + "--" + Thread.currentThread().getId()
                    + " буду работать в отдельном потоке, а не в главном. "
                    + "Текущее время: " + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Результат асинхронной задачи. выполненной в " + Thread.currentThread().getName();
        }, executor);

        // Блокировка и получение результата Future
        // Метод CompletableFuture.get() блокирующий. Он ждет, пока Future завершится и вернёт результат.
        String resultSupplyExe = futureSupplyExe.get();
        System.out.println(resultSupplyExe);

        System.out.println("Основной поток закончил работу. " + Thread.currentThread().getName()
                + " Текущее время: " + System.currentTimeMillis());
    }
}
