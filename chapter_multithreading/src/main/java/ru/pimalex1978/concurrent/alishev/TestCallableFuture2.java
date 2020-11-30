package ru.pimalex1978.concurrent.alishev;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Продолжение.
 * Начало смотри в TestCallableFuture1.
 * Пример работы с классами Callable и Future, они позволяют возвращать
 * значения из потоков. И также позволяют выбрасывать исключения из потоков и ловить
 * их в главном потоке.
 * Обратим внимание, что сигнатура метода run(), в котором выполняется логика потока,
 * имеет возвращаемый тип void, т.е. он ничего не может вернуть!!!
 * Можно вместо интерфейса Runnable(), метод run() которого ничего не возвращает,
 * использовать параметризованый интерфейс Callable<Object>() и его метод call().
 * И теперь можем возвращать значение того типа, который указали при создании
 * объекта Callable<Integer>()
 * Доступ к возвращаемому значению из потока мы можем получить с помощью
 * интерфейса Future (перев. - Будущее). Он параметризуется тем же типом что и интефейс
 * Callable<V>.
 * Т.е. когда то в будущем, когда поток завершит выполнение мы получим значение.
 * В качестве значения так же может быть исключение, которое нужно обрабатывать
 * в блоке catch. (смотри в коде)
 */
public class TestCallableFuture2 {
    public static void main(String[] args) {
        //Создадим пул из одного потока
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //Создадим новый поток с помощью submit, реализовывая интерфейс new Callable
        // в анонимном классе и получим из потока значение,
        //сохранив его в Future<Integer>
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            /*Описываем логику в потоке в методе call()*/
            @Override
            public Integer call() throws Exception {
                System.out.println("Starting");
                //спим 3 сек
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Finished");

                //Для возврата int значения используем метод calculate()
                int randomValue = calculate();
                //Если число < 50 бросим эксепшн и оно поймается в блоке catch (ExecutionException e)
                //для метода get()
                if (randomValue < 50) {
                    throw new Exception("Something wrong!");
                }
                return randomValue;
            }
        });

        //запустим наш executorService, т.е. потоки в пуле.
        executorService.shutdown();

        //Ждать выполнение потоков, как в первом примере с помощью
        //метода .awaitTermination не нужно, т.к. далее будет метод get()
        //из Future.
//        try {
//            executorService.awaitTermination(1, TimeUnit.DAYS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //Получим значение из Future с помощью метода get()
        //get() - дожидается окончания выполнения потока!!!
        //т.е. останавливаемся в главном потоке и ждем выполнения потока thread.
        //get() - выбрасывает два исключения InterruptedException и ExecutionException
        try {
            int result = future.get();
            System.out.println("Result is " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //Получим сообщение об ошибке и напечатаем его вместо printStackTrace
            Throwable ex = e.getCause(); //получаем само исключение, которое выбросили
            System.out.println(ex.getMessage()); //getMessage() дает нам текст исключения
//            e.printStackTrace();
        }
        System.out.println("I am here");
    }

    /**
     * Метод для примера того, что нам нужно в потоке что то
     * сделать и вернуть результат.
     *
     * @return возвращаемый результат целое число.
     */
    private static int calculate() {
        Random random = new Random();
        return random.nextInt(100);
    }
}
