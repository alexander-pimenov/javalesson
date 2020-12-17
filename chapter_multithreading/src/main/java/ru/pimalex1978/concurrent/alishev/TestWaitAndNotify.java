package ru.pimalex1978.concurrent.alishev;

import java.util.Scanner;

/**
 * Методы wait() и notify() достались каждому бъекту от Object и
 * нужны чтобы синхронизировать потоки в Java.
 * Курс "Продвинутая Java. №23. Методы wait() и notify()"
 * https://www.udemy.com/course/javarussia/learn/lecture/8982264#overview
 * С помощью методов wait() и notify() мы можем так же реализовать
 * паттерн Продюсер-Консюмер.
 * Методы produce() и consume() работают в разных потоках. Но для синхронизации
 * используют один объект. В нашем случае объект WaitAndNotify.
 * Метод wait(), может быть с параметрами timeoutMillis: wait(100);
 * wait() - может ждать сколь угодно долго.
 * wait(100) - ждет 100 мс и если монитор будет у этого же потока, то
 * он продолжит свою работу.
 */
public class TestWaitAndNotify {
    public static void main(String[] args) {
        //Создаем объект WaitAndNotify, чтобы можно было вызвать его методы.
        WaitAndNotify wn = new WaitAndNotify();

        //создадим два потока
        //Поток в котором выполняется метод produce()
        Thread thread1 = new Thread(
                () -> {
                    try {
                        wn.produce();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        //Поток в котором выполняется метод consume()
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //Запускаем потоки
        thread1.start();
        thread2.start();

        //Ждем в main выполнения потоков thread1 и thread2
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WaitAndNotify {
    /*Главное чтобы метод produce() и consume() синхронизировались на одном объекте.
     * Т.обр. методы wait и notify будут вызваны на одном объекте. На одном локе.
     * За счет этого объекта и происходит связь двух потоков.
     * По другому это работать не будет.
     * Но синхронизуются они на объекте this, т.е. согласно контексту методов, в
     * которых они вызваны: this.wait(), this.notify().
     * Т.е. если мы будем синхронизироваться на каком то
     * другом объекте lock, то этот объет нужно указывать и на методат wait и notify:
     * lock.wait() и lock.notify()
     * Один поток ждет, заснув на методе wait, а другой поток оповещает, что первый
     * может проснуться и продолжить выполнение.*/
    public void produce() throws InterruptedException {
        //Объявляем синхронизованный блок и синхронизируемся (для простоты)
        //на объекте this, хотя можно делать это на любом объекте.
        synchronized (this) {
            System.out.println("Producer thread started...");
            //Вызовем метод wait()
            //Он вызывается только на синхронизированном блоке
            //вне этого блока он не имеет смысла.
            //Метод wait() вызывается на том объекте на котором определен
            //синхронизованный блок.
            //Можно нескольким потокам использовать этот метод wait() и все
            //они остановятся и будут ждать, когда в каком нибудь другом
            //потоке вызовется notify() или notifyAll().
            this.wait();
//            this.wait(100);
            // this.wait(); -> Здесь происходят две вещи:
            // 1 - поток останавливается здесь, отдает intrinsic lock, т.е.
            // другие потоки, которые синхронизируются на этом объекте, могут
            // забрать лок этого объекта и выполнить какую то работу.
            // 2 - ждем, пока будет вызван notify() на синхронизированном объекте.
            System.out.println("Producer thread resumed..."); //поток продолжил работу
        }
    }

    public void consume() throws InterruptedException {
        //Поспим 2 сек, чтобы поток с методом producer() точно был первым
        Thread.sleep(2000);
        //Scanner чтобы по нажатию клавиши из консоли программа продолжилась
        Scanner scanner = new Scanner(System.in);

        synchronized (this) {
            System.out.println("Waiting for return key pressed");
            scanner.nextLine(); //Чтоб программа ждала нашего нажатия
            //Метод notifyAll() пробуждает все потоки синхронизированный на
            //объекте this, которые ждут. Например, 10 потоков были в ожидании
            //и этот метод их разбудит.
            //Метод notify() пробуждает только один поток. Например,
            //10 потоков были в ожидании и этот метод разбудит 1 поток.
            //После этого тот поток который дошел до wait() и отдал монитор и
            //остановил своё выполнение, продолжит свою работу.
//            this.notifyAll();
            this.notify();
            //Но должны помнить что метод notify() не освобождает монитор.
            //Монитор объекта освободится, как только выйдем из синхронизованного блока.
            Thread.sleep(5000);
        }
        // Как только мы выходим из этого блока synchronized(this), то тот
        // поток, который вызвал wait() и остановился (это было в
        // методе produce()), опять овладевает монитором,
        // который был только что занят методом consumer(), он пробудится
        // и продолжит свою работу и выведет на экран
        // "Producer thread resumed..."
    }
}
