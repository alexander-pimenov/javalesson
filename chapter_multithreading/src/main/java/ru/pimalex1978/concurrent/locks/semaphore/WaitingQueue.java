package ru.pimalex1978.concurrent.locks.semaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static ru.pimalex1978.concurrent.ColorScheme.GREEN;
import static ru.pimalex1978.concurrent.ColorScheme.RED;

/**
 * Semaphore Class:
 * https://www.udemy.com/course/java-simple2advanced/learn/lecture/11252438#overview
 * <p>
 * Синхронизаторы – вспомогательные утилиты для синхронизации потоков, которые дают
 * возможность разработчику регулировать и/или ограничивать работу потоков и
 * предоставляют более высокий уровень абстракции, чем основные примитивы языка
 * (мониторы).
 * <p>
 * Еще одна техника синхронизации потоков - Семафоры.
 * Семафор, это класс, который позволяет одновременный доступ к
 * какому то ресурсу (к куску кода) нескольких потоков.
 * При использовании lock к ресурсу мог получить доступ только один
 * поток. В случае с семафором у нас может быть четко заданное
 * количество потоков, которые одновременно могут получить доступ.
 * А все остальные потоки станут в очередь.
 * Это как звонить на линию колцентра и ждать на линии своей
 * очереди. Потоки это пользователи, а колцентр - это семафор.
 * <p>
 * Потоки в семафоре работают в режиме FIFO. Кто первый пришел,
 * тот первый получит лок.
 * <p>
 * Для операции инкремента используем AtomicInteger.
 */
public class WaitingQueue {

    public static void main(String[] args) throws InterruptedException {
        //количество операторов колцентра
        int operators = 5;
        //количество пользователей ждущих в очереди
        int customers = 21;

        //создаем инстанс класса SemaphoreServiceDesk
        SemaphoreServiceDesk serviceDesk = new SemaphoreServiceDesk(operators, customers);

        //для каждого пользователя создаем отдельный поток
        //используем ExecutorService
        ExecutorService executorService = Executors.newCachedThreadPool();

        //создаем поток пользователей
        IntStream.range(0, customers)
                .forEach(client -> executorService.submit(
                        () -> {
                            serviceDesk.connect();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(GREEN + "Number of connected customers " + serviceDesk.getCustomersConnected());
                            System.out.println(RED + "Number of customers in the queue " + serviceDesk.getCustomersWaiting());
                        }));

        //завершим работу пула потоков
        executorService.shutdown();

        //
        executorService.awaitTermination(30, TimeUnit.SECONDS);

    }

    //Класс реализующий работу соединения с колцентром.
    private static class SemaphoreServiceDesk {

        //Переменная для учета пользователей ждущих в очереди
        private final AtomicInteger customersQueued; //её инициализируем в конструкторе

        //Переменная указывающая на того, кто соединился с колцентром
        //и разговаривает с оператором.
        private final AtomicInteger connectedCustomers = new AtomicInteger(); //=0

        //Поле Семафора
        private final Semaphore semaphore;

        //permits - цисло потоков допушенных к ресурсу в один момент времени.
        //operatorsNumbers - количество операторов в колцентре
        //customersNumbers - количество пользователей в очереди
        public SemaphoreServiceDesk(int operatorsNumbers, int customersNumbers) {
            semaphore = new Semaphore(operatorsNumbers);
            customersQueued = new AtomicInteger(customersNumbers);
        }


        //Метод осуществляет соединение к нашему колцентру.
        public void connect() {
            //Для иммитации разного времени работы потоков.
            Random random = new Random();
            try {
                //поток получает свой лок,
                //получает разрешение от семафора
                //acquire() запрашивает доступ к следующему за вызовом этого метода блоку кода,
                //если доступ не разрешен, поток вызвавший этот метод блокируется до тех пор,
                //пока семафор не разрешит доступ
                semaphore.acquire();
                //увеличим соединенных пользователей.
                connectedCustomers.incrementAndGet();
                //уменьшим кол-во пользователей в очереди
                customersQueued.decrementAndGet();
                //иммитируем работу потока в пределах 3 секунд
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }

        //метод разъединения с колцентром
        private void disconnect() {
            //поток отработал и должен освободить лок.
            //хорошим тоном делать это в блоке finally
            semaphore.release();
            //укажем, что освободилось место для нового пользователя
            connectedCustomers.decrementAndGet();
        }

        //Метод для получения количества пользователей ждущих в очереди
        private int getCustomersWaiting() {
            return customersQueued.get();
        }

        //Метод для получения количества пользователей присоединенных к колцентру
        private int getCustomersConnected() {
            return connectedCustomers.get();
        }
    }
}
