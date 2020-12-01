package ru.pimalex1978.concurrent.executors;

import java.util.Random;

import static ru.pimalex1978.concurrent.ColorScheme.*;

/**
 * Суть этого класса в том, чтобы искать Greatest Common Divisor
 * (наибольший общий делитель)
 * Наследуем Random, чтобы проще было использовать методы класса Random,
 * такие как nextInt().
 * Реализуем Runnable, чтобы из этого класса сделать поток, у которого в
 * методе run() тот код, который нужно выполнить. В нашем случае в нем
 * вычисляется GCD.
 * <p>
 * Также в этом классе есть проверка являтся ли этот поток Daemon или
 * User.
 */
public class GCDRunnable extends Random implements Runnable {

    //поля типа потока (Daemon | User)
    private boolean isDaemon;

    public GCDRunnable(boolean isDaemon) {
        this.isDaemon = isDaemon;
    }

    public GCDRunnable() {
    }

    @Override
    public void run() {
        //можем изменять описание нашего потока в зависимости daemon или user:
        String threadType = isDaemon ? "daemon-" : "user-";

        String threadDescription = threadType + Thread.currentThread().getName();

        System.out.println(BLUE + "Starting " + threadDescription); //базовое логирование
        //Что бы создать видимость длительной работы, будем крутиться в цикле 10_000_000
        for (int i = 0; i < 10_000_000; i++) {
            int a = nextInt(); //рандомное целое число
            int b = nextInt(); //рандомное целое число

            //Выводим вычисления на экран 1 раз в 10_000 и если gcd>5
            //Таже проверяем был ли прерван поток, это если нужно завершить работу потока
            //правильно и досрочно.
            //Пока не прерван, работает.
            if (i % 10000 == 0) {
                if (!Thread.interrupted()) {
                    int gcd = computeGCD(a, b);
                    if (gcd > 5) {
                        System.out.println(GREEN + "Running in " + threadDescription + ". The GCD of " + a + " and " + b + " is " + gcd);
                    }
                } else {
                    //Если был вызван interrupted(), то завершаем работу метода.
                    System.out.println(BLUE + "Thread " + threadDescription + " was interrupted");
                    return;
                }
            }
        }
        System.out.println(BLUE + "Leaving the thread " + threadDescription);
    }

    /**
     * Метод вычисляющий наибольший общий делитель.
     * Рекурсивно будет вызывать самого себя пока второе
     * число не станет равным 0.
     *
     * @param number1 первое число
     * @param number2 второе число
     * @return результат
     */
    private int computeGCD(int number1, int number2) {
        if (number2 == 0) {
            return number1;
        } else {
            return computeGCD(number2, number1 % number2);
        }
        /*-----------------------------------------------
        number1=50, number2=30:
        (50,30) => 50%30=20 -> (30,20) => 30%20=10 ->
        (20,10) => 20%10=0 (разделилось без остатка)
        -> (10,0)    return number1=10
         -------------------------------------------------*/
    }
}
