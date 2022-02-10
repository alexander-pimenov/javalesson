package ru.pimalex1978.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*https://annimon.com/article/3462*/
public class CompletableFutureExceptionHandling {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Обработка исключений CompletableFuture
        //
        // Мы рассмотрели, как создать, преобразовать и объединить CompletableFuture.
        // Теперь давайте разберёмся, что делать, если что-то пошло не так.
        //
        // Сперва рассмотрим, как ошибки распространяются в цепочке задач.
        // Если в исходной задаче supplyAsync() возникнет ошибка, тогда ни одна из
        // последующих задач thenApply() не будет вызвана и Future завершится с исключением.
        // Если ошибка возникнет в первом thenApply(), то все последующие задачи в цепочке
        // не будут запущены и Future всё так же завершится с исключением.
        CompletableFuture.supplyAsync(() -> {
            // Код, который может выбросить исключение
            return "Некоторый результат";
        }).thenApply(result -> {
            return "Обработанный результат " + result;
        }).thenApplyAsync(result -> {
            //Чтобы иметь больше контроля над потоком, выполняющим задачу,
            //вы можете использовать асинхронные колбэки. Если вы используете
            // thenApplyAsync(), он будет выполнен в другом потоке, полученном из
            // ForkJoinPool.commonPool()
            return "Результат дальнейшей обработки " + result;
        }).thenAccept(result -> {
            // Какие-то действия с окончательным результатом
            //просто выведем на печать:
            System.out.println(result);
        });

        //1. Обработка исключений с использованием метода -= exceptionally() =-
        // Метод exceptionally() даёт возможность обойти возможные ошибки, если они есть.
        // Можно залогировать исключение и вернуть значение по умолчанию.
        Integer age = -10;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным");
            }
            if (age > 18) {
                return "Взрослый";
            } else {
                return "Ребёнок";
            }
        }).exceptionally(ex -> {
            //Можно залогировать исключение и вернуть значение по умолчанию.
            System.out.println("Ой! У нас тут исключение - " + ex.getMessage());
            return "Неизвестно!";
        });

        System.out.println("Зрелость: " + maturityFuture.get());

        //2. Обработка исключений с использованием метода -= handle() =-
        //
        // Для восстановления после исключений API также предоставляет более общий метод handle().
        // Он вызывается независимо от того, возникло исключение или нет.
        Integer age2 = -10;

        CompletableFuture<String> maturityFuture2 = CompletableFuture.supplyAsync(() -> {
            if (age2 < 0) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным");
            }
            if (age2 > 18) {
                return "Взрослый";
            } else {
                return "Ребёнок";
            }
        }).handle((res, ex) -> {
            //Если возникает исключение, аргумент res будет null, если не возникает, то ex будет null.
            System.out.println("Аргумент res: " + res);
            System.out.println("Аргумент ex: " + ex);
            if (ex != null) {
                System.out.println("Ой! У нас тут исключение - " + ex.getMessage());
                return "Неизвестно!";
            }
            return res;
        });

        System.out.println("Зрелость: " + maturityFuture2.get());

    }
}
