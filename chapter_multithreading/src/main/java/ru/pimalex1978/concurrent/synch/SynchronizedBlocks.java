package ru.pimalex1978.concurrent.synch;

import java.util.ArrayList;
import java.util.List;

/*
 * В примере показано использование созданных локов.
 * Показано, где лок нужно включить, а где лок отключить (
 * сделать лок релиз).
 * В данном пример показано, когда нам не выгодно использовать
 * synchronized методы, а когда нужно использовать synchronized
 * блоки.
 * Создаем ситуацию, когда есть два отдельных потока, которые
 * выполняют абсолютно не зависимую работу.
 * Первый поток работает с массивом a[], второй с массивом b[].
 * Вывод информации о списках может быть в различном порядке.
 * Время выполнения программы, примерно, 1272 ms.
 * А если мы ставим методу synchronized, то сперва выполнится
 * копирование одного массива (тот поток который первее завладеет
 * процессорным временем, запустит метод copyArray, т.е. поставит
 * лок), а затем только второй поток будет делать копирование второго
 * массива. Потоки будут работать последовательно. И время выполнения
 * будет примерно 2490 ms. Т.е. дольше в 2 раза.
 * Это один из недостатков synchronized метода.
 * Решается это просто - создается два отдельных лока для наших
 * отдельных потоков.
 * А вместо объявления метода synchronized будем использовать в методах
 * блоки synchronized с отдельными локами: synchronized(lock){}
 * Время выполнения при этом будет примерно 1238 ms, т.е. как и без synchronized.
 * Т.обр. два метода будут абсолютно независимыми друг от друга и не
 * будут ждать друг друга.
 */
public class SynchronizedBlocks {
    private int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private int[] b = {0, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    private List<Integer> intList1 = new ArrayList<>();
    private List<Integer> intList2 = new ArrayList<>();

    // Создаем два лока, которые сможем использовать отдельно
    // друг от друга. Обязательно должны быть final
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();


    public static void main(String[] args) {
        SynchronizedBlocks sb = new SynchronizedBlocks();
        sb.copy();
    }

    /**
     * Основной метод, где все будем делать.
     * В нем будут создаваться потоки исполнения.
     * А так же измерим время выполнения метода.
     */
    private void copy() {
        long start = System.currentTimeMillis();
        Thread thread1 = new Thread(new RunnerA());
        Thread thread2 = new Thread(new RunnerB());

        //Стартуем потоки.
        thread1.start();
        thread2.start();

        //Ждем пока потоки не выполнят свою работу.
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken : " + (end - start) + " ms");
    }

    /**
     * Метод для копирования элементов массива a[] в лист intList1.
     * Для лучшей работы и синхронизации используем блок synchronized
     * с определенным локом lock1.
     */
    private void copyArrayA() {
        synchronized (lock1) {
            for (int i = 0; i < a.length; i++) {
                intList1.add(a[i]);
                System.out.println(intList1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    // Метод объявленный synchronized имеет недостатки.
    //т.к. он синхронизируется на мониторе this, т.е. пока
    //один поток работает, ворой поток ждет освобождения монитора.
//    private synchronized void copyArrayA() {
//        for (int i = 0; i < a.length; i++) {
//            intList1.add(a[i]);
//            System.out.println(intList1);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * Метод для копирования элементов массива b[] в лист intList2.
     * Для лучшей работы и синхронизации используем блок synchronized
     * внутри метода с определенным локом lock2.
     */
    private void copyArrayB() {
        synchronized (lock2) {
            for (int i = 0; i < b.length; i++) {
                intList2.add(b[i]);
                System.out.println(intList2);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // Метод объявленный synchronized имеет недостатки.
    //т.к. он синхронизируется на мониторе this, т.е. пока
    //один поток работает, ворой поток ждет освобождения монитора.
//    private synchronized void copyArrayB() {
//        for (int i = 0; i < b.length; i++) {
//            intList2.add(b[i]);
//            System.out.println(intList2);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * Класс для работы с массивом a[]
     */
    private class RunnerA implements Runnable {
        @Override
        public void run() {
            copyArrayA();
        }
    }

    /**
     * Класс для работы с массивом b[]
     */
    private class RunnerB implements Runnable {
        @Override
        public void run() {
            copyArrayB();
        }
    }
}
