package ru.pimalex1978.concurrent.callableandfuture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Пример из
 * https://javadevblog.com/java-callable-kratkoe-opisanie-i-primer-ispol-zovaniya.html
 * <p>
 * Java предоставляет Callable интерфейс для определения задач, возвращающих
 * результат. A Callable похож на, Runnable за исключением того, что он может
 * возвращать результат и вызывать проверенное исключение.
 * <p>
 * Callable интерфейс имеет единственный метод call(), который должен содержать
 * код, выполняемый потоком.
 * <p>
 * Точно так же как и Runnable, мы можем отправить Callable в
 * службу-исполнитель ExecutorService для выполнения.
 * submit() - это метод исполнителя службы представляет задачу для выполнения
 * в Thread. Однако он не знает, когда будет доступен результат отправленного
 * задания. Следовательно, он возвращает специальный тип значения, называемый
 * Future (Будущее), который можно использовать для получения результата задачи,
 * когда он доступен.
 * Future - представляет собой результат вычислений, которые будут
 * выполнены позже в будущем.
 * Если мы не вызовем метод get(), то и объект Callable не даст свой результат,
 * находящийся в роператоре return.
 * <p>
 * Обратите внимание, что get() метод блокируется, и ждет задачи для завершения.
 * Future API также предоставляет isDone() метод, чтобы проверить, завершена ли
 * задача или нет.
 * Если вы вызываете API из удаленной службы в вызываемой задаче, а удаленная служба
 * не работает, она future.get() будет заблокирована навсегда, что приведет к
 * зависанию приложения.
 * Чтобы защититься от этого факта, вы можете добавить тайм-аут в get() методе:
 * future.get(1, TimeUnit.SECONDS);
 */
public class MyCallable implements Callable<String> {

    //Выполняем некоторые действия
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        // возвращает имя потока, который выполняет callable таск
        return "Thread name: " + Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        //Получаем ExecutorService утилитного класса Executors с размером пула потоков равному 10
        ExecutorService executor = Executors.newFixedThreadPool(8);
        //создаем список с Future, которые ассоциированы с Callable
        List<Future<String>> list = new ArrayList<Future<String>>();
        // создаем экземпляр MyCallable
        Callable<String> callable = new MyCallable();
        for (int i = 0; i < 30; i++) {
            //сабмитим (отправляем) Callable таски, которые будут
            //выполнены пулом потоков
            Future<String> future = executor.submit(callable);
            //добавляя Future в список,
            //мы сможем получить результат выполнения
            list.add(future);
        }

        /*Future - представляет собой результат вычислений, которые будут
         * выполнены позже в будущем.
         * Если мы не вызовем метод .get(), то и объект Callable не будет выполнен,
         * в от личие от Runnable.*/
        for (Future<String> fut : list) {
            try {
                // печатаем в консоль возвращенное значение Future
                // будет задержка в 1 секунду, потому что Future.get()
                // ждет пока таск закончит выполнение
                System.out.println(new Date() + "::" + fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        //закрытие пула (шаг 4) Если его не закрыть, то программа будет работать вечно.
        executor.shutdown();
    }

}