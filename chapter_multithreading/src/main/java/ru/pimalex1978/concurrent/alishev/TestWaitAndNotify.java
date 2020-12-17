package ru.pimalex1978.concurrent.alishev;

import java.util.Scanner;

/**
 * Методы wait() и notify() достались каждому бъекту от Object и
 * нужны чтобы синхронизировать потоки в Java.
 * Курс "Продвинутая Java. №23. Методы wait() и notify()"
 * https://www.udemy.com/course/javarussia/learn/lecture/8982264#overview
 */
public class TestWaitAndNotify {
    public static void main(String[] args) throws InterruptedException {
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
        thread1.join();
        thread2.join();
    }
}

class WaitAndNotify {
    /*Главное чтобы метод produce() и consume() синхронизировались на одном объекте.
     * Т.обр. методы wait и notify будут вызваны на одном объекте. За счет этого
     * объекта и происходит связь двух потоков. По другому это работать не будет.
     * Но синхронизуются они на объекте this, т.е. согласно контексту методов, в
     * которых они вызваны: this.wait(), this.notify(). Т.е. если мы будем синхронизироваться на каком то
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
            this.wait(); // 1 - отдаем intrinsic lock, 2 - ждем пока будет вызван notify()
            System.out.println("Producer thread resumed..."); //поток продолжил работу
        }
    }

    public void consume() throws InterruptedException {
        //Поспим 2 сек, чтобы поток с producer() точно был первый
        Thread.sleep(2000);
        //Scanner чтобы по нажатию клавиши из консоли программа продолжилась
        Scanner scanner = new Scanner(System.in);

        synchronized (this) {
            System.out.println("Waiting for return key pressed");
            scanner.nextLine(); //Чтоб программа ждала нашего нажатия
            //Пробуждает поток синхронизированный на объекте this, который ждет.
            //После этого тот поток который дошел до wait() и отдал монитор и остановил
            //своё выполнение, продолжит свою работу.
            this.notify();
            //Но должны помнить что notify не освобождает монитор
            //Монитор объекта освободится, как только выйдем из синхронизованного блока.
            Thread.sleep(5000);
        }
    }
}
