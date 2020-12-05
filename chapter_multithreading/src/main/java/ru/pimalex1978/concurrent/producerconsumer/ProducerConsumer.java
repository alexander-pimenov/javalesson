package ru.pimalex1978.concurrent.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static ru.pimalex1978.concurrent.ColorScheme.*;

/**
 * Расмотрим шаблон Продюсер-Консюмер (Производитель-Потребитель).
 * https://www.udemy.com/course/java-simple2advanced/learn/lecture/11252390#overview
 * Суть этого шаблона заключается в следующем. Есть две группы потоков (или два потока)
 * Один из них Продюсер, он получает информацию из файла или по сети и второй
 * является Консюмером (Потребителем) этой информации и как то её обрабатывает.
 * Промежуточным звеном этой цепи будет какая то коллекция, к-рая хранит все
 * объекты обработанные Продюсером и которые потом должен забрать Консюмер.
 * В нашем случае у нас будет ArrayBlockingQueue, потокобезопасная
 * структура очередь на массиве (FIFO). Особенность этой очереди такова, что
 * если она заполнена, то в нее ничего добавить нельзя, и поток будет ждать
 * пока освободиться место в очереди чтоб туда добавить новое сообщение. Так же
 * в обратном направлении, если очередь пуста, то поток Консюмер будет ждать
 * пока там что то появится.
 * Т.е. для синхронизации потоков обошлись без synchronized и lock.
 * <p>
 * В качестве массива строк использован стих
 * STOPPING BY WOODS ON A SNOWY EVENING
 * Роберт Фрост
 */
public class ProducerConsumer {
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {

        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }

    private static class Producer implements Runnable {
        /*В качестве источника сообщений создадим массив строк.*/
        String[] messages = {
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

        /*
         * Помещаем данные из массива строк в очередь.
         */
        private void produce() throws InterruptedException {
            //Для иммитации ассинхронной работы потоков
            Random r = new Random();
            //В цикле обрабатываем строки массива
            for (int i = 0; i < messages.length; i++) {
                //Помещаем сообщение в очередь
                queue.put(messages[i]);
                System.out.println(GREEN + "Producing " + messages[i] + ". Queue size is " + queue.size());
                Thread.sleep(r.nextInt(1000));
            }
        }
    }

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*
         * Получаем данные из очереди и обрабатываем
         */
        private void consume() throws InterruptedException {
            Random r = new Random();
            while (true) {
                String message = queue.take();
                System.out.println(RED + "Consuming " + message + ". Queue size is " + queue.size());
                if (!"DONE".equals(message)) {
                    Thread.sleep(r.nextInt(2000));
                } else {
                    return;
                }
            }
        }
    }
}
