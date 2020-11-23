package ru.pimalex1978.concurrent.alishev;

/**
 * Это слово помагает бороться с хаотичностью потоков.
 * Ключевое слово volatile говорит, что эта переменная будет
 * изменяться в процессе работы из разных потоков.
 * И JVM будет учитывать это. И не будет кешировать эту переменную.
 * Т.е. эта переменная будет храниться в основной памяти,
 * и каждый поток будет брать обновленную переменную.
 */
public class TestVolatile {

    //Эта переменная общая для двух потоков, поэтому далее для
    //нормальной работы программы нужна синхронизация.
    //Один из вариантов синхронизации, это контроль переменной.
    //Чтобы переменная не кешировалась, а была доступна из общей памяти
    //для всех потоков, нужно ставить ей volatile.
    private static volatile int counter;

    public static void main(String[] args) {
        new SimpleWriter().start();
        new SimpleReader().start();
    }

    private static class SimpleWriter extends Thread {
        /*В этом потоке мы увеличиваем переменную localCounter и
         записываем её в counter*/
        @Override
        public void run() {
            int localCounter = counter;
            for (int i = 0; i < 10; i++) {
                System.out.println(ColorScheme.GREEN + "Writer increment counter " + (localCounter + 1));
                counter = ++localCounter;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class SimpleReader extends Thread {
        /*В этом потоке мы вычитываем переменную counter и
         записываем её в localCounter*/
        @Override
        public void run() {
            int localCounter = counter;
            while (localCounter < 10) {
                if (localCounter != counter) {
                    System.out.println(ColorScheme.RED + "Reader reds counter " + counter);
                    localCounter = counter;
                }
            }
        }
    }
}

