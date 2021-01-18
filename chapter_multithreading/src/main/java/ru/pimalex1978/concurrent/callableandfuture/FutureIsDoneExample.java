package ru.pimalex1978.concurrent.callableandfuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*https://www.callicoder.com/java-callable-and-future-tutorial/
 *
 * ExecutorService.submit() метод немедленно возвращается и дает вам Future (Будущее).
 * Как только вы получили Future (будущее), вы можете выполнять другие задачи параллельно,
 * пока ваша отправленная задача выполняется, а затем использовать future.get() метод
 * для получения результата будущего.
 *
 * Обратите внимание, что get() метод блокируется и ждет задачи для завершения.
 * Future API также предоставляет isDone() метод, чтобы проверить, завершена ли задача или нет.
 *
 * Если вы вызываете API из удаленной службы в вызываемой задаче, а удаленная служба не работает,
 * она future.get() будет заблокирована навсегда, что приведет к зависанию приложения.
 * Чтобы защититься от этого факта, вы можете добавить тайм-аут в get() методе:
 * future.get(1, TimeUnit.SECONDS);
 *
 * */
public class FutureIsDoneExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread().getName() + " started.");

        // создаем пул потоков с 1 потоком (шаг 2)
        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // создаем задачи и передаем объект Callable в пул для выполнения (соместили шаг 1 + 3)
        Future<String> future1 = executorService.submit(() -> {
            Thread.sleep(2000);
            System.out.println("Я внутри task1");
            return "Hello from Callable 1";
        });

        Future<String> future2 = executorService.submit(() -> {
            Thread.sleep(4000);
            System.out.println("Я внутри task2");
            return "Hello from Callable 2";
        });

        while (!future1.isDone()) {
            System.out.println("Task1 is still not done...");
            Thread.sleep(200);
        }

        while (!future2.isDone()) {
            System.out.println("Task2 is still not done...");
            Thread.sleep(200);
        }

        System.out.println("Task completed! Retrieving the result");
        String result1 = future1.get();
        String result2 = future2.get();
        System.out.println(result1);
        System.out.println(result2);

        // закрытие пула (шаг 4)
        executorService.shutdown();

        System.out.println(Thread.currentThread().getName() + " finished.");
    }
}
