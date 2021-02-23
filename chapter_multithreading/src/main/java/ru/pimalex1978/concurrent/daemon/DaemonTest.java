package ru.pimalex1978.concurrent.daemon;

/*Вот простой небольшой фрагмент кода, чтобы проиллюстрировать разницу
между обычным рабочим потоком и демон-потоком. Попробуйте сделать
это с каждым из значений true и false в setDaemon.
Особенность Демон-потока в том, что он завершит свою работу по
завершению работы всех обычных потоков.
Если мы не будем запускать WorkerThread то DaemonThread завершиться вместе
с основным потоком.*/
public class DaemonTest {

    public static void main(String[] args) {
//        new WorkerThread().start(); //раскомментируй, и посмотри разницу работы
        new DaemonThread().start();

        try {
            Thread.sleep(8500);
        } catch (InterruptedException e) {
            // handle exception here
        }

        System.out.println("Main Thread ending.");
    }
}

/**/
class WorkerThread extends Thread {
    public WorkerThread() {
        /*Если false (т.е. когда это пользовательский поток),
         рабочий поток продолжает выполняться.
         Когда true (т.е. когда это поток демона),
         рабочий поток завершается,
         когда завершается основной поток.*/
        setDaemon(false);
    }

    @Override
    public void run() {
        int count = 0;

        while (true) {
            System.out.println("Hello from Worker " + count++);

            try {
                sleep(3000);
            } catch (InterruptedException e) {
                // handle exception here
            }
        }
    }
}

/**/
class DaemonThread extends Thread {
    public DaemonThread() {
        /*Если false (т.е. когда это пользовательский поток),
         рабочий поток продолжает выполняться.
         Когда true (т.е. когда это поток демона),
         рабочий поток завершается,
         когда завершается основной поток.*/
        setDaemon(true);
    }

    @Override
    public void run() {
        int count = 100;

        while (true) {
            System.out.println("Hello from Demon " + count);

            try {
                sleep(1500);
            } catch (InterruptedException e) {
                // handle exception here
            }
            count += 100;
        }
    }
}
