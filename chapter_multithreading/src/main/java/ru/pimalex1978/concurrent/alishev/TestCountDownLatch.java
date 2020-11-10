package ru.pimalex1978.concurrent.alishev;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс показывающий работу "Защелки Обратного Отчета"
 * CountDownLatch(3) - 3 - это количество итераций, которые
 * нужно отсчитать назад прежде, чем защелка отопрется.
 * При создании объекта CountDownLatch в конструктор предаем такое
 * число, которое означает сколько раз нужно вызвать метод
 * .countDown() из разных или из одного потока, чтобы .await()
 * больше не ждал. Пока число не будет равно 0, .await() дальше
 * не идет.
 * В нашем пример мы в основном потоке main ждем CountDownLatch,
 * а в трёх потоках отсчитываем этот CountDownLatch назад.
 */
public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        /*Создадим три потока испольуя ExecutorService*/
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        /*В цикле передадим нашим трем потоках задание,
         * используя метод .submit(...)*/
        System.out.println("Threads receive jobs...");
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Processor(i, countDownLatch));
            Thread.sleep(100);
        }
        /*.shutdown() - чтобы прекратить сабмин новых заданий и чтоб потоки
         * начали выполнять полученные задания.*/
        executorService.shutdown();

        /*.await() - Заставляет текущий поток (в нашем случае это поток main) ждать,
         * пока отсчет защелки не завершится до нуля, если только поток не
         * будет прерван.*/
        countDownLatch.await();

        Thread.sleep(1000);
        /*После того как защелка откроется выведем надпись:*/
        System.out.println("Latch has been opened, main-thread is proceeding!");
    }
}

/**
 * Все наши потоки будут делить один объект CountDownLatch,
 * а т.к. этот класс потоко безопастный, то о синхронизации нам
 * задумываться не надо.
 */
class Processor implements Runnable {
    //у каждого потока будет id
    private int id;
    //У всех наших потоков будет один CountDownLatch
    private CountDownLatch countDownLatch;

    public Processor(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    /**
     * В методе run() логика выполняемая в потоке:
     * будем спать 3 сек и отсчитывать countDownLatch к 0.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /* В этом методе мы на единицу уменьшаем наш countDownLatch.
         * .countDown() - Уменьшает счетчик защелки, освобождая все
         * ожидающие потоки, если счетчик достигает нуля.
         * Если текущий счетчик больше нуля, он уменьшается.
         * Если новый счетчик равен нулю, то все ожидающие
         * потоки повторно включаются для целей планирования потоков.
         */
        countDownLatch.countDown();
        System.out.println("Thread with id: " + id + " performed a decrease by 1");
    }
}
