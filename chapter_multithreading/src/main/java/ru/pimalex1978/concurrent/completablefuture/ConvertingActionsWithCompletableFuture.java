package ru.pimalex1978.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*https://annimon.com/article/3462*/
public class ConvertingActionsWithCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //Преобразование действий с CompletableFuture
        //
        //Метод CompletableFuture.get() блокирующий. Он ждет, пока Future завершится и вернёт результат.
        //
        //Но это же не то, что нам нужно, верно? Для построения асинхронных систем мы должны иметь
        // возможность повесить на CompletableFuture колбэк, который автоматически вызовется при завершении Future.
        //
        //Так что нам не потребуется ждать результат и внутри функции-колбэка мы сможем написать логику,
        // которая отработает после завершения Future.
        //
        //Вы можете повесить колбэк на CompletableFuture, используя методы
        // -=thenApply()=-, -=thenAccept()=-
        // и -=thenRun()=-.

        //1. thenApply()
        // Вы можете использовать метод thenApply() для обработки и преобразования результата CompletableFuture
        // при его поступлении. В качестве аргумента он принимает Function<T, R>.
        // Function<T, R> это тоже функциональный интерфейс, представляющий функцию, которая принимает аргумент
        // типа T и возвращает результат типа R:
        // Создаём CompletableFuture
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });

        // Добавляем колбэк к Future, используя thenApply()
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> "Привет," + name);

        // Блокировка и получение результата Future
        System.out.println(greetingFuture.get()); // Привет, Rajeev

        //Вы также можете сделать несколько последовательных преобразований, используя серию вызовов thenApply().
        // Результат одного thenApply() передаётся следующему:
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        }).thenApply(name -> {
            return "Привет," + name;
        }).thenApply(greeting -> {
            return greeting + ". Добро пожаловать в блог CalliCoder";
        });

        System.out.println(welcomeText.get());
        // Выводит: Привет, Rajeev. Добро пожаловать в блог CalliCoder

        //Заметка об асинхронных колбэках
        //Все методы-колбэки в CompletableFuture имеют два асинхронных вида:
        // Виды thenApply()
        //<U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
        //<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
        //<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
        //Эти асинхронные виды колбэков помогут распараллелить задачи, выполнив их в отдельном потоке.

        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Некоторый результат";
        }).thenApply(result -> {
        /*
        Выполняется в том же потоке, где и задача supplyAsync()
        или в главном потоке, если задача supplyAsync() завершается сразу(чтобы проверить это удалите sleep())
        */
            return "Обработанный результат " + result;
        });

        //Чтобы иметь больше контроля над потоком, выполняющим задачу, вы можете использовать асинхронные колбэки.
        // Если вы используете thenApplyAsync(),
        // он будет выполнен в другом потоке, полученном из ForkJoinPool.commonPool():
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Поток, который работатет в supplyAsync "
                    + Thread.currentThread().getName() + "--" + Thread.currentThread().getId());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Некоторый результат";
        }).thenApplyAsync(result -> {
            // Выполняется в другом потоке, взятом из ForkJoinPool.commonPool()
            //Чтобы иметь больше контроля над потоком, выполняющим задачу,
            //вы можете использовать асинхронные колбэки. Если вы используете
            // thenApplyAsync(), он будет выполнен в другом потоке, полученном из
            // ForkJoinPool.commonPool()
            System.out.println("Поток, который работатет в thenApplyAsync " + Thread.currentThread().getName()
                    + "--" + Thread.currentThread().getId());
            return "Обработанный результат " + result;
        });

        System.out.println(completableFuture.get());


        //Более того, если вы передадите Executor в thenApplyAsync(), задача будет выполнена в потоке,
        // полученном из пула потоков Executor.
        Executor executor = Executors.newFixedThreadPool(2);
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "Некоторый результат";
        }).thenApplyAsync(result -> {
            // Выполняется в потоке, полученном от Executor
            return "Обработанный результат " + result;
        }, executor);

        System.out.println(stringCompletableFuture.get());

    }
}
