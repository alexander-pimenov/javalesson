package ru.pimalex1978.concurrent.waitnotify;


import static ru.pimalex1978.concurrent.ColorScheme.*;

/**
 * В примере будет рассмотрена реализация паттерна Producer-Consumer
 * с помощью методов wait() и notify(), доставшиеся нам от класса
 * Object.
 * Метод wait() останавливает поток, он заставляет ждать поток не ограниченное
 * количество времени. До тех пор пока другой поток, который синхронизируется
 * по тому же локу не вызовет метод notify().
 * notifyAll() - отправлет сообщение всем потокам, которые ждут получения
 * того же лока.
 * wait() вызывается у объекта message.
 * В примере два потока будут синхронизироваться по одному и тому же объекту.
 * Метод notify() говорит другому потоку, что он может уже начать выполнять работу,
 * но он не освобождает поток,
 * но если поток не может получить свой лок, т.е. на самом деле лок не отдан, то
 * и работать он не сможет, он будет все еще заблокирован, до тех пор пока не
 * освободим лок.
 * <p>
 * В результате мы увидим поочередный вывод сообщений, что говорит о синхронизации
 * в работе двух потоков.
 */
public class WaitNotifyExample {

    public static void main(String[] args) {
        Message message = new Message();

        new Thread(new Producer(message)).start();
        new Thread(new Consumer(message)).start();
    }

    private static class Producer implements Runnable {
        //на нем будем синхронизироваться
        private final Message message;

        //Создадим конструктор Продюсера, чтобы передавать лок
        Producer(Message message) {
            this.message = message;
        }

        /*В качестве источника сообщений создадим массив строк.*/
        String[] text = {
                "Whose woods these are I think I know.",
                "His house is in the village, thought;",
                "He will not see me stopping here",
                "To watch his woods fill up with snow.",
                "My little horse must think it queer",
                "To stop without a farmhouse near",
                "Between the woods and frozen lake",
                "The darkest evening of the year.",
                "He gives harness bells a shake",
                "To ask if there is some mistake.",
                "The only other sound's the sweep",
                "Of easy wind and downy flake.",
                "The woods are lovely, dark and deep,",
                "But I have promises to keep,",
                "And miles to go before I sleep,",
                "And miles to go before I sleep.",
                "DONE"
        };

        @Override
        public void run() {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void produce() throws InterruptedException {
            for (String t : text) {
                synchronized (message) {
                    System.out.println(BLUE + "Producing message: " + t);
                    message.setMessage(t);
                    //Скажем что лок свободен.
                    message.notify();
                    if (!"DONE".equals(t)) {
                        //остановим работу Продюсера, чтоб отработал Консюмер.
                        message.wait();
                    }
                }
                Thread.sleep(400);
            }
        }
    }

    private static class Consumer implements Runnable {
        private final Message message;

        Consumer(Message message) {
            this.message = message;
        }

        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void consume() throws InterruptedException {
            while (true) {
                Thread.sleep(400);
                synchronized (message) {
                    System.out.println(RED + "Consuming message: " + message.getMessage());
                    //проверка на продолжение работы
                    if (!"DONE".equals(message.getMessage())) {
                        message.notify();
                        message.wait();
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
