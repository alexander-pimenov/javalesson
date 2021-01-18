package ru.pimalex1978.concurrent.callableandfuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*https://www.callicoder.com/java-callable-and-future-tutorial/
 *
 * Вы можете использовать isCancelled() метод, чтобы проверить, отменена ли
 * задача или нет. Также после отмены задания isDone() всегда будет true.
 *
 * future.get() метод выбрасывает, CancellationException если задача отменена.
 * Мы можем справиться с этим фактом, проверив, отменено ли Future (будущее),
 * прежде чем получить результат.
 * */
public class FutureCancelExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        long startTime = System.nanoTime();
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return "Hello from Callable";
        });

        while (!future.isDone()) {
            System.out.println("Task is still not done...");
            Thread.sleep(200);
            double elapsedTimeInSec = (System.nanoTime() - startTime) / 1000000000.0;

            //////////////////////////////////////////
            //Если эту часть кода закомментируем, то получим результат
            //"Task completed! Retrieving the result"
            if (elapsedTimeInSec > 1) {
                future.cancel(true);
            }
            //////////////////////////////////////////
        }

        //Проверка, была ли отмена Future
        if (!future.isCancelled()) {
            System.out.println("Task completed! Retrieving the result");
            String result = future.get();
            System.out.println(result);
        } else {
            System.out.println("Task was cancelled");
        }

        executorService.shutdown();
    }
}
