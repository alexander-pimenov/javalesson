package ru.pimalex1978.concurrent.waitnotify;


import static ru.pimalex1978.concurrent.ColorScheme.*;

/**
 * В примере будет рассмотрена реализация паттерна Producer-Consumer
 * с помощью методов wait() и notify(), доставшиеся нам от класса
 * Object.
 * https://www.udemy.com/course/java-simple2advanced/learn/lecture/11252410#overview
 * Метод wait() останавливает поток, он заставляет ждать поток не ограниченное
 * количество времени. До тех пор пока другой поток, который синхронизируется
 * по тому же локу не вызовет метод notify().
 * notifyAll() - отправлет сообщение всем потокам, которые ждут получения
 * того же лока.
 * Есть метод wait(long timeout) с указанием в параметре скольок нужно ждать.
 * В нагем примере wait() вызывается у объекта message.
 * В примере два потока будут синхронизироваться по одному и тому же объекту.
 * Метод notify() говорит другому потоку, что он может уже начать выполнять работу,
 * но он не освобождает поток,
 * но если поток не может получить свой лок, т.е. на самом деле лок не отдан, то
 * и работать он не сможет, он будет все еще заблокирован, до тех пор пока не
 * освободим лок. Лок освободится, как только поток выейдет из метода synchronized(message)
 * <p>
 * В результате мы увидим поочередный вывод сообщений, что говорит о синхронизации
 * в работе двух потоков.
 * <p>
 * Напомню, что в блоке synchronized() код может выполнять в один момент времени
 * только один поток, в этом и есть суть синхронизации.
 *
 * В этой программе видим, что Продюсер записывает сообщение и ждет пока его
 * прочитает Консюмер. Полсе того, как Консюмер читает сообщение, Продюсер
 * снова записывает уже новое сообщение и опять отаналивается, чтоб его
 * прочитал Консюмер.
 */
public class WaitNotifyExample {

    public static void main(String[] args) {
        Message message = new Message();

        new Thread(new Producer(message)).start();
        new Thread(new Consumer(message)).start();
    }

    private static class Producer implements Runnable {
        //на нем будем синхронизироваться, поэтому он должен быть private final
        private final Message message;

        //Создадим конструктор Продюсера, чтобы передавать лок на
        // котором будем синхронизироваться.
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

        /**
         * Метод, в котором мы будем выполнять всю работу.
         * Перебираем весь массив сообщений и выводим их на экран.
         * Методы wait() и notify() вызываем у того объекта, на
         * котором синхронизируемся, в нашем примере это message.
         * Продюсер и Консюмер будут синхронизироваться по одному и тому
         * объекту (локу) - message.
         *
         * @throws InterruptedException исключение.
         */
        private void produce() throws InterruptedException {
            for (String t : text) {
                synchronized (message) {
                    System.out.println(BLUE + "Producing message: " + t);
                    //Делаем запись прочитанного сообщения.
                    message.setMessage(t);
                    //Скажем другим потокам, что лок свободен.
                    //Так сообщаем Консюмеру, что ссобщение готово к чтению.
                    //Монитор освободится, как дойдем до конца метода produce().
                    message.notify();
                    if (!"DONE".equals(t)) {
                        //Остановим работу Продюсера, чтоб отработал Консюмер.
                        //Чтобы бесконечно не записывать сообщения.
                        //При методе wait() монитор освождается сразу и другой
                        //поток может им завладеть.
                        message.wait();
                    }
                }
                //
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


        /**
         * Метод consume() работает в бесконечном цикле. Выходим из
         * него только когда получим слово "DONE".
         * Синхронизируемся по тому же лок объекту message.
         *
         * @throws InterruptedException exception
         */
        private void consume() throws InterruptedException {

            while (true) {
                //Спим 400 мс, чтобы Продюсер успел создать сообщение,
                //и только потом его забирать.
                Thread.sleep(400);
                synchronized (message) {
                    System.out.println(RED + "Consuming message: " + message.getMessage());
                    //проверка на продолжение работы
                    //Если не = DONE, то работу продолжаем, если равно,
                    // то выходим из метода.
                    if (!"DONE".equals(message.getMessage())) {
                        //скажем что лок свободен, но при этом монитор сразу
                        //не освободится.
                        message.notify();
                        //остановим работу Консюмера, чтоб отработал Продюсер.
                        //При этом лок освобождается сразу и Продюсер завладеет
                        // локом и сможет записать новое сообщение.
                        message.wait();
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
