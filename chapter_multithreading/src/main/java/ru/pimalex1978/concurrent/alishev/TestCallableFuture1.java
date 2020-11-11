package ru.pimalex1978.concurrent.alishev;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Пример работы с классами Callable и Future, они позволяют возвращать
 * значения из потоков. И также позволяют выбрасывать исключения из потоков.
 * Обратим внимание, что сигнатура метода run(), в котором выполняется логика потока,
 * имеет возвращаемый тип void, т.е. он ничего не может вернуть!!!
 * Мы можем конечно иметь поле result в классе TestCallableFuture и с этим полем
 * что то делать в потоке (result++), а потом значение этого поля вызвать из основного
 * потока sout(result). Но можно сделать проще.
 * Можно вместо интерфейса Runnable(), метод run() которого ничего не возвращает,
 * использовать интерфейс Callable()
 * Смотри далее TestCallableFuture2.
 */
public class TestCallableFuture1 {
    private static int result;

    public static void main(String[] args) {
        //Создадим пул из одного потока
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //Создадим новый поток с помощью submit, реализовывая в нем интерфейс new Runnable
        executorService.submit(new Runnable() {
            /*Описываем логику в потоке*/
            @Override
            public void run() {
                System.out.println("Starting");
                //спим 3 сек
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished");
                result++;
            }
        });
        //запустим наш executorService
        executorService.shutdown();

        //ждем выполнение потока
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(result);
        System.out.println("I am here");
    }
}
