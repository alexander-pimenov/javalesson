package ru.pimalex1978.concurrent.evenandodd;

/**
 * Здесь продемонстрированна синхронизация двух потоков чере wait() и notify().
 * В примере показаны работа двух тредов, один из ряда числе выбирает только
 * четные, другой только нечетные. Числа печатаются на экране.
 * Предел числовой указываем в параметре max объекта класса TaskEvenOdd.
 */
public class PrintEvenOddWaitNotify {
    public static void main(String[] args) {

        Printer printer = new Printer();
        Thread th1 = new Thread(new TaskEvenOdd(printer, 10, false), "Odd");
        Thread th2 = new Thread(new TaskEvenOdd(printer, 10, true), "Even");
        th1.start();
        th2.start();
    }
}

class TaskEvenOdd implements Runnable {
    private final int max;
    private final Printer printer;
    private final boolean isEvenNumber;

    TaskEvenOdd(Printer printer, int max, boolean isEvenNumber) {
        this.printer = printer;
        this.max = max;
        this.isEvenNumber = isEvenNumber;
    }

    @Override
    public void run() {
        int number = isEvenNumber ? 2 : 1;
        while (number <= max) {
            if (isEvenNumber) {
                printer.printEven(number);
            } else {
                printer.printOdd(number);
            }
            number += 2;
        }
    }
}

class Printer {
    private volatile boolean isOdd;

    //печатает четное число
    synchronized void printEven(int number) {
        while (!isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); //первыставим флаг для правильной обработки
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + number);
        isOdd = false;
        notify();
    }

    //печатает нечетное число
    synchronized void printOdd(int number) {
        while (isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); //первыставим флаг для правильной обработки
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + number);
        isOdd = true;
        notify();
    }
}