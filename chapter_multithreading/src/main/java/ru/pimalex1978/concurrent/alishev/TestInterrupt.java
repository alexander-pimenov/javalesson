package ru.pimalex1978.concurrent.alishev;

import java.util.Random;

/**
 * В этом примере разберем прерывание потоков.
 * Как из одного потока прервать другой поток.
 * Это нужно если процесс в одном потоке очень длительный
 * и его нужно остановить.
 * В Java был метод stop(), который убивал поток, мгновенно его
 * останавливая. И процесс выполняемый в потоке оказывается в неопределенном
 * состоянии. Не хороший метод. Он считается устаревшим.
 * Метод interrupt() не убивает поток, он дает потоку сообщение, что его хотят
 * остановить. Поток сообщение принимает и мы можем грамотно завершить поток.
 * Не вызывая неопределенное состояние.
 * Используем такую конструкцию: Thread.currentThread().isInterrupted().
 * В потоке main мы вызываем метод interrupt(), а в потоке thread метод
 * .isInterrupted() возвращает true.
 * <p>
 * InterruptedException выбрасывается, когда поток был прерван.
 * Например, метод sleep() выбрасываем это исключение, поймав его в блоке
 * catch мы можем также выйти из цикла в нашей программе с помощью ключевого
 * слова break, т.е. получим аналогичную ситуацию как и с использованием
 * .isInterrupted(). Но это работа ведется в потоке thread, а не прерывается из
 * другого потока.
 */
public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
        /*Создаем поток в котором будем вычислять sin
        случайного значения очень много раз 1_000_000_000.*/
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("GOOD_THREAD"); //Установим новое имя нашему потоку
                Random random = new Random();
                for (int i = 0; i < 1_000_000_000; i++) {

                    /*Специальная конструкция для того чтоб принять
                     * сообщение, что текущий поток currentThread
                     * желают прервать из другого потока.*/
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println(ColorScheme.RED + Thread.currentThread().getName() + " Thread was interrupted.");
                        break; //выйдем из цикла или
//                        return; //или выйдем из метода, что говорит о завершении работы потока.
                    }
                    /*Конструкция прерывающая поток с помощью метода sleep(),
                     * который выбрасывает исключение InterruptedException*/
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        System.out.println("Thread was interrupted!!");
//                        break;
//                    }
                    Math.sin(random.nextDouble());
                }
            }
        });

        System.out.println("Starting thread.");

        //запускаем поток
        thread.start();

        //Подождем 2 сек и потом вызовем interrupt()
        Thread.sleep(2000);
        //Будем прерывать поток thread из main потока используя interrupt()
        thread.interrupt();

        //останавливаемся в потоке main и ждем выполнение потока thread
        thread.join();

        System.out.println(ColorScheme.RESET + "Thread has finished.");
    }
}
