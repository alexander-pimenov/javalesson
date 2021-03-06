package ru.pimalex1978.concurrent.alishev;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Здесь в примере сделаем наооборот от того, что было в  примере с классом
 * TestCountDownLatch, теперь мы будем в потоке main отсчитывать countDownLatch назад,
 * а в трёх потоках будем ждать. Как только countDownLatch досчитает до 0
 * то в потоках откроется защелка, выполнение кода в потоках продолжиться
 * и выведутся их сообщения.
 * <p>
 * https://www.udemy.com/course/javarussia/learn/lecture/8982274#overview
 */
public class TestCountDownLatchConversely {
    private static final int NUMBER_COUNT = 5;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(NUMBER_COUNT);

        /*Создадим три потока испольуя ExecutorService,
         * т.е. создаем пул исполнителей, в количестве 3 штук.*/
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        /*В цикле передадим нашим трем потоках задание,
         * используя метод .submit(...)*/
        System.out.println("Threads receive jobs...");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(100);
            //передаем объекты в пул
            executorService.submit(new Processor2(i, countDownLatch));
        }
        /*.shutdown() - чтобы прекратить получение (сабмит) новых заданий
         * и чтоб потоки начали выполнять полученные задания, т.обр. потокам
         * даем понять, что им пора браться за выполнение переданных им заданий,
         * т.е. так мы закрываем пул потоков.*/
        executorService.shutdown();

        System.out.println("The Main thread counts down the counter...");
        /*Здесь будем отсчитывать countDownLatch к 0*/
        for (int i = 0; i < NUMBER_COUNT; i++) {
            Thread.sleep(1000); //спим 1 сек
            countDownLatch.countDown();
        }
        System.out.println("The Main thread has finished running!");
    }
}

/**
 * Все наши потоки будут делить один объект CountDownLatch,
 * а т.к. этот класс потоко безопастный, то о синхронизации нам
 * задумываться не надо.
 */
class Processor2 implements Runnable {
    //у каждого потока будет id
    private int id;
    //У всех наших потоков будет один CountDownLatch
    private CountDownLatch countDownLatch;

    public Processor2(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    /**
     * В методе run() логика выполняемая в потоке:
     * будем спать 3 сек и ждать пока countDownLatch не
     * станет равным 0.
     */
    @Override
    public void run() {
        System.out.println("Executing " + this.toString()
                + " inside : " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /* В этом методе .await() мы ждем пока наш countDownLatch
         * не станет равен 0. Изменять countDownLatch будем из
         * основного main потока.
         */
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Поток с id #id продолжен.
        System.out.println("Thread with id " + id + " proceeded.");
    }

    @Override
    public String toString() {
        return "Processor2{" +
                "id=" + id +
                ", countDownLatch=" + countDownLatch +
                '}';
    }
}