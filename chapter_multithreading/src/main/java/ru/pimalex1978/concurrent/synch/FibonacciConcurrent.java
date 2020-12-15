package ru.pimalex1978.concurrent.synch;

/*
 * Synchronized - синхронизация.
 * Потоки общаются друг с другом путем доступа к одним и тем же переменным.
 * Это может вызывать проблему memory consistency (согласованность памяти) и
 * visibility ( видимость), когда изменения сделанные одним потоком не
 * видны другим потокам.
 *
 * Если используется synchronized в статическом методе, то используется
 * лок класса, а если метод не статический, то лок объекта this.
 *
 */
public class FibonacciConcurrent {
    private static int previous = 0;
    private static int current = 1;

    public static void main(String[] args) {
        //Создаем два потока, которые будут вызывать наш FibonacciRunner
        //а он в свою очередь запускать метод run() c методом calcNext().
        Thread th1 = new Thread(new FibonacciRunner());
        Thread th2 = new Thread(new FibonacciRunner());

        th1.start();
        th2.start();

//        th1.join();
//        th1.join();
    }

    /*
     * Чтобы программа работала правильно, нужно ограничить доступ
     * к методу. Используем synchronized, чтобы только один поток
     * мог работать с методом, а другой ждал.
     * Т.е. сперва один поток будет фибоначить числа, а потом
     * другой продолжит это действие. Но не одновременно.
     * intrinsic lock - внутренняя блокировка этому помагает.
     */
    private static synchronized void calcNext() {
        int next = previous + current;
        previous = current;
        current = next;
        System.out.println(current);
    }
// Это идентичные записи synchronized в методе.
//    private static void calcNext() {
//        synchronized (FibonacciConcurrent.class) {
//            int next = previous + current;
//            previous = current;
//            current = next;
//            System.out.println(current);
//        }
//    }

    private static class FibonacciRunner implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                calcNext();
            }
        }
    }
}
