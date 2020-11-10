package ru.pimalex1978.concurrent.alishev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Пример из урока "№21.Пул потоков" из курса "Продвинутая Java".
 * https://www.udemy.com/course/javarussia/learn/lecture/8982254#overview
 * <p>
 * Thread Pool это еще один способ создания какого то количества потоков.
 * Смысл в том, что создается N-е число потоков, которые выполняют задание
 * параллельно. Создавать явно отдельно потоки не нужно. И потоки не будут
 * "хвататься" за выполнение одной работы, а будут работать параллельно.
 * <p>
 * Класс Executors содержит в себе статические методы. Эти методы принимают
 * на вход какие то аргументы и возвращают ExecutorService.
 * ExecutorService - это наш пул потоков.
 * ExecutorService - это сервис по выполнению.
 * В нашем примере ниже, этот сервис ExecutorService содержит двух "работников"
 * (два потока), которые будут выполнять работу work.
 * Если работник освобождается то он начинает делать следующую работу.
 */
public class TestThreadPool {
    public static void main(String[] args) throws InterruptedException {

        /*Создаем Thread Pool (сервис по выполнению). В нашем пуле будет 2 потока.
        И мы им дадим задание.*/
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        /*Будем передавать нашим работникам (нашему сервису по выполнению)
        5 заданий.*/
        for (int i = 0; i < 5; i++) {
            //передаем нашему сервису по выполнению работу new Work(i),
            //класс который реализует интерфейс Runnable и содержит метод run()
            //с помощью метода submit()
            executorService.submit(new Work(i));
        }

        //Теперь хотим чтоб работники приступили к выполнению работы.
        //.shutdown() говорит что мы перестаем принимать новые задания и
        //беремся за выполнения заданий, которые были переданы с помощью
        //метода submit().
        //shutdown() чем то похож на метод start() для потока. Из него
        //выходим мгновенно.
        executorService.shutdown();
        System.out.println("All tasks submitted."); //все задачи отправлены

        //Метод .awaitTermination() - "ожидание окончания" - с указанием сколько
        //будем ждать выполнения работы потоками. Укажем 1 день.
        //На этом методе поток main остановится и не пойдет дальше и будет
        //ждать либо выполнения потоками работы либо истечения срока. И только
        //потом пойдет дальше.
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
//Вывод результата.
//All tasks submitted.
//Work 1 was completed.
//Work 0 was completed.
//Work 2 was completed.
//Work 3 was completed.
//Work 4 was completed.

/**
 * Класс представляющий работу.
 * У каждой работы (таски) есть свой id.
 */
class Work implements Runnable {

    //у каждой единицы работы будет свой id
    private int id;

    /**
     * id будет поставляться в конструкторе.
     *
     * @param id id работы
     */
    public Work(int id) {
        this.id = id;
    }

    /**
     * В методе пишем ту работу, которую надо выполнить.
     * Т.е. ту логику, которую надо выполнить.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work " + id + " was completed.");
    }
}

