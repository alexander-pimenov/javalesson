package ru.pimalex1978.anonimous_class;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Другой способ использования асинхронной функции обратного вызова —
 * использование CompletableFuture API. Этот мощный API, представленный
 * в Java 8, упрощает выполнение и объединение вызовов асинхронных методов.
 * Он делает все, что мы делали в предыдущем примере. Например, создает
 * новый поток, затем запускает его и управляет им.
 */
public class CompletableFutureCallback {
    public static void main(String[] args) throws Exception {
        System.out.println("***Start of main method***");

        //Здесь реализация интерфейса Supplier и далее передаем его в метод
        //Supplier<String> stringSupplier = () -> "Supply Async...";
        Supplier<String> stringSupplier = () -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Supply Async...";
        };

        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(stringSupplier);

        CompletableFuture<String> execution = completableFuture
                .thenApply(s -> s + " Callback executed...");

        System.out.println(execution.get());
        System.out.println("***End of main method***");

    }

}
