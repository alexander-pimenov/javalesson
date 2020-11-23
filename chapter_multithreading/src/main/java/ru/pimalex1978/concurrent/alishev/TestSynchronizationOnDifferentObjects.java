package ru.pimalex1978.concurrent.alishev;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Пример, где мы синхронизируемся на отдельном объекте, а не на this,
 * т.обр. увеличиваем производительность программы.
 */
public class TestSynchronizationOnDifferentObjects {
    public static void main(String[] args) {
        new Worker().main();
    }
}

class Worker {
    private Random random = new Random();

    //Будем синхронизоваться на этих объектах lock1 lи ock2 и это хороший тон,
    //хотя можем синхроизоваться на листах.
    //Объекты lock1 и lock2 должны быть final.
    //Эти объекты будем использовать только для синхронизации и больше ни
    //для чего.
    final Object lock1 = new Object();
    final Object lock2 = new Object();

    //Посмотрим состояние гонки на примере листов, их заполнения
    // случайными числами.
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    //Два этапа работы addToList1 и addToList2
    //Чтобы не было состояния гонки, т.е. когда два потока одновременно
    //будут использовать метод одновременно, используем ключевое слово
    //synchronized
    //Но не забываем, что в этом случае синхронизация будет производится на
    //объекте this (т.е. на объекте new Worker)
    //Т.обр. если один поток вырвался вперед и завладел монитором объекат this
    //то второй будет ждать пока первый не выполнит методы addToList1 и addToList2
    //Чтобы разделить выполнение методов нужно создать два раздельных объекта
    //lock1 и lock2 и убрать ключевое слово synchronized и сделать внутри метода
    //блок synchronized
    //У этих объектов разные мониторы. И поэтому один поток может забрать метод
    //addToList1, а второй поток может забрать addToList2 а не ждать выполнения
    //этих двух методов первым потоком.

    /**
     * Добавление случайного элемента в список.
     */
    public void addToList1() {
        //синхронизируемся на lock1
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100)); //от 0 до 99
        }
    }

    /**
     * Добавление случайного элемента в список.
     */
    public void addToList2() {
        //синхронизируемся на lock2
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100)); //от 0 до 99
        }
    }

    /*В методе вызываем два процесса addToList1 и addToList2*/
    public void work() {
        for (int i = 0; i < 1000; i++) {
            addToList1();
            addToList2();
        }
    }

    /**
     * В методе создаим два потока, чтобы каждый поток мог наполнять элементами
     * списки, т.обр. за определенное время увеличим производительность.
     * Как изменилась производительность увидим используя временные метки.
     */
    public void main() {
        long before = System.currentTimeMillis();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });

//        work(); //Здесь работает только один поток main

        //Стартуем потоки
        thread1.start();
        thread2.start();

        try {
            //Ждем пока потоки thread1 и thread2 выполняться
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long after = System.currentTimeMillis();
        System.out.println("Program took " + (after - before) + " ms to run.");
        System.out.println("List1: " + list1.size());
        System.out.println("List2: " + list2.size());
    }
}

//Обрати внимание на разное время выполнения программы: 14337 ms и 4775 ms
//Синхронизация метода на объекте this, слово synchronized в сигнатуре метода
//Program took 14337 ms to run.
//List1: 2000
//List2: 2000

//Синхронизация в методе на объекте lock1 и во втором метода на объекте lock2,
//используем блок synchronized(lock1) внутри метода
//Program took 4775 ms to run.
//List1: 2000
//List2: 2000