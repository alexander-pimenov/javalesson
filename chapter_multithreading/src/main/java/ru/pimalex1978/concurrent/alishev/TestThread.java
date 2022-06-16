package ru.pimalex1978.concurrent.alishev;

import static ru.pimalex1978.concurrent.alishev.ColorScheme.*;

/**
 * Пример, как можо создавать различные потоки.
 * Видно, что потоки не связаны между собой, они борятся за процессорное
 * время, ведут себя как бы хаотично. Какой то поток будет работать,
 * какой то будет ждать.
 * Как можно прерывать поток из другого потока.
 * Или ловить в try-catch или ставить условие if().
 * Если код в потоке выполняется очень долго, то правильно, чтобы сам
 * поток время от времени сам у себя проверял не прерывают ли его.
 * Нужно поставить условие проверки в блоке
 * if(Thread.currentThread().isInterrupted())
 * <p>
 * Сделали статический импорт класса цветов ColorScheme, чтобы
 * упростить их вызов в строках.
 */
public class TestThread {
    public static void main(String[] args) {
        System.out.println("Starting main Thread. Hello from main");

        Thread th1 = new SimpleThread();
        th1.start();

        SimpleThread th2 = new SimpleThread();
        th2.start();

        Thread th3 = new Thread(new SimpleRunner());
        th3.start();

        new Thread(() -> System.out.println(CYAN + "Hello from Lambda Runnable!"))
                .start();

        //поспим 1,5 секунды
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        th2.interrupt(); //прервем 2-й поток
        System.out.println(RESET + "Main Thread has finished. Bye!");

    }

}

class SimpleThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                /*Если мы прерываем поток, т.е. из основного метода main приходит
                 * interrupted(), то поток выведет сообщение и завершит свою работу
                 * с помощью return.
                 * Если так не сделать, то он просто выведет сообщение о прерывании,
                 * но продолжит свою работу!!!*/
                System.out.println(RED + "WARN - " + Thread.currentThread().getName() + " was interrupted!");
                return;
            }
            //------------------------------------------------------------------------------
            // Проверку на прерывание потока из другого потока можно делать и таким способом.
//            if (Thread.currentThread().isInterrupted()) {
//                System.out.println(BLUE + "WARN (from if statement)- " + Thread.currentThread().getName() + " was interrupted!");
//                return; //завершаем поток
//            }
            //------------------------------------------------------------------------------
            System.out.println(YELLOW + "INFO - " + Thread.currentThread().getName() + " - " + i);
        }
    }
}

class SimpleRunner implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println(RED + "WARN - Runnable - " + Thread.currentThread().getName() + " was interrupted!");
            }
            System.out.println(PURPLE + "INFO - Runnable - " + Thread.currentThread().getName() + " - " + i);
        }
    }
}