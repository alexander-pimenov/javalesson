package ru.pimalex1978.concurrent.callableandfuture;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Пример из урока:
 * https://www.udemy.com/course/java-simple2advanced/learn/lecture/11252456#overview
 * У java.lang.Runnable есть брат и зовут его java.util.concurrent.Callable и
 * появился он на свет в Java 1.5. Мы видим, что в отличие от Runnable,
 * новый интерфейс объявляет метод call, который возвращает результат
 * в виде объекта Object.
 * Кроме того, по умолчанию он throws Exception. То есть избавляет нас
 * от необходимости на проверяемые исключения писать try-catch блоки.
 * Очевидно, что в дальнейшем мы рассчитываем получить результат действий,
 * которыев в будущем будут выполнены. Будущее по-английский — Future.
 * И интерфейс есть с точно таким же именем: java.util.concurrent.Future
 * Интерфейс Фьючер это результат работы какой то задачи.
 * Имеет определенные методы, которые помгут получить результат работы:
 * get() или проверить не было ли наше задание Task отменено: isCanceled(),
 * или еще что-нибудь: isDone(), cancel(boolean mayInterruptIfRunning)
 *
 */
public class CallableFutureBase {
    public static void main(String[] args) {
        //соберем все наши Future в коллекцию
        List<Future<Double>> futures = new ArrayList<>();

        ExecutorService executorService = Executors.newCachedThreadPool();

        /*определим задачу, которая будет выполняться в executorService
         * несколько раз в цикле*/
        for (int i = 0; i < 30; i++) {
            Future<Double> submit = executorService.submit(() -> {
                //Здесь имитируем работу потока.
                Timer timer = new Timer();
                //Будем использовать рандомные значения.
                Random random = new Random();

                //Получим текущее время и запишем в переменную start
                timer.start = Instant.now();

                //вроде поток что то делает в течение какого то времени
                //определим его рандомно
                int rand = random.nextInt(5000);
                //Можно так же выбрасывать исключения по условию.
                //Это исключение будет отловлено в случае обработки возвращаемого результата.
                if (rand > 4000) {
                    throw new RuntimeException("Thread is running to long. Terminating...");
                }
                Thread.sleep(rand);

                //Получим текущее время и запишем в переменную end
                //для вычисления прошедшего времени
                timer.end = Instant.now();

                //Благодаря Callable мы может возвращать что-то из метода call(),
                //т.е. из отработавшего потока. В данном случае это затраченное время.
                return timer.timeInSeconds();
            });

            //Полученные фьючеры добавляем в коллекцию
            futures.add(submit);
        }

        executorService.shutdown();

        //Пройдемся по листу фьючеров и у каждого фьючера вызовем метод get()
        //будем обрабатывать их параллельно в нескольких потоках
        futures.parallelStream()
                .map(f -> {
                    try {
                        //если всё успешно, то получим результат
                        return f.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        //Выведем эксепшн в sout, что вызвало наш exception
                        System.out.println(e.getCause());
//                        e.printStackTrace();
                    }
                    //если попали в блок exception, то потом выведем значение 0
                    return 0;
                })
                .forEach(t -> System.out.println("Thread execution time " + t)); //Время выполнения потока
    }
}

/**
 * Класс для замера времени.
 */
class Timer {
    public Instant start;
    public Instant end;

    /**
     * Метод возвращающий прошедшее время в секундах.
     *
     * @return время в секундах.
     */
    public double timeInSeconds() {
        return Duration.between(start, end).toMillis() / 1000.0;
    }
}
