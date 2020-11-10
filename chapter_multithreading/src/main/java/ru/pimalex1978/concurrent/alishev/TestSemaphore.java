package ru.pimalex1978.concurrent.alishev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Класс Semaphore испльзуется в том случае, когда у нас есть
 * какой то ресурс и много потоков используют его. Ресурс ценный.
 * И класс Семафор будет ограничивать доступ к этому ресурсу.
 * Например, есть сервер и 10 потоков отправляют данные в него,
 * т.е. пишут в него. Сервер это ресурс. Соединение с Сервером
 * это тоже ресурс. Пропускная способность кабеля ограничена.
 * И мы хотим эту пропускную способность делить между потоками.
 * С помощью Семафор мы можем делить этот ограниченный ресурс
 * между потоками.
 * <p>
 * В аргументе класса Семафор передаем число, которое означает
 * количество разрешений для потоков. Т.е. в нашем примере мы укажем
 * сколько потоков может писать одновременно в Сервер. Укажем число 3,
 * т.е. максимум 3 потока смогут одновременно отправлять данные на
 * Сервер.
 * .acquire() - метод вызывается, когда в потоке начинаем
 * взаимодействовать с ресурсом.
 * .release() - метод отдающий разрешение. Когда в потоке заканчиваем
 * использовать ресурс, мы вызываем этот метод.
 * .availablePermits() - метод возвращающий количество свободных
 * разрешений.
 * <p>
 * Если все разрешения израсходованны, т.е. 3 потока уже используют
 * один и тот же ресурс (в нашем примере), то 4-й поток который
 * тоже захочет использовать это ресурс и вызовет метод .acquire(),
 * будет ждать, остановится на этом методе .acquire(), пока один
 * из потоков, использующих ресурс, не вызовет метод .release()
 * (т.е. не освободит свой слот).
 */
public class TestSemaphore {
    public static void main(String[] args) {
        //Создадим 200 потоков используя Thread Pool
        ExecutorService executorService = Executors.newFixedThreadPool(200);

        //Получим наш Connection
        Connection connection = Connection.getConnection();

        //Передадим 200 потокам одно и то же задание: достать Connection и сделать работу
        for (int i = 0; i < 200; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        connection.work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //закончили передавать задания в потоки
        executorService.shutdown();

        //зададим срок ожидания на выполнение работы 1 день
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Класс реализует соединение с Сервером.
 * Это Соединение является ценным ресурсом и мы хотим ограничить
 * доступ к нему.
 * В этом классе реализован паттерн Singleton, т.обр. мы гарантируем,
 * что будет создан только один объект Connection. И только он
 * будет использоваться.
 */
class Connection {
    //переменная класса, в котором мы находимся, с созданием
    //объекта нашего класса Connection
    private static Connection connection = new Connection();

    private int connectionsCount;

    //Будем давать разрешать работать только 10 потокам из 200
    private Semaphore semaphore = new Semaphore(10);

    /**
     * Конструктор будет приватным, т.обр. мы запрещаем создавать
     * объекты класс. Т.е. даем понять пользователям, что мы не можем
     * создавать сколько угодно объектов этого класса. Т.к. Connection
     * один.
     * Это реализация паттерна Singleton.
     */
    private Connection() {
    }

    /**
     * Метод возвращающий объет класса Connection.
     *
     * @return Connection.
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Обернем метод doWork() в метод work(), где будем
     * использовать Семафор. Работать с doWork() в один момент
     * времени сможет только указанное количество потоков (10).
     * Метод .release() должен гарантированно вызываться, а если
     * doWork() выбросит исключение, то метод закончит работу, и
     * для этого .release() поместили в блок finally.
     */
    public void work() throws InterruptedException {
        semaphore.acquire();
        try {
            doWork();
        } finally {
            semaphore.release();
        }
    }

    /**
     * В этом методе будем делать какую то полезную работу
     * с нашим соединением.
     */
    private void doWork() throws InterruptedException {
        //Когда мы подключаемся к Connection мы будем увеличивать connectionsCount
        synchronized (this) {
            connectionsCount++;
            System.out.println(connectionsCount);
        }

        //Симулируем полезную работу, например записали данные куда-то
        Thread.sleep(5000);

        //когда работу сделали, то уменьшаем счетчик соединения
        synchronized (this) {
            connectionsCount--;
        }
    }
}

//        Semaphore semaphore = new Semaphore(3);
//        try {
//            semaphore.acquire();
//            semaphore.acquire();
//            semaphore.acquire();
//            System.out.println("All permits have been acquired.");
//            semaphore.acquire();
//            System.out.println("This point can't be  reach ...");
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(semaphore.availablePermits());
//        semaphore.release();
//        System.out.println(semaphore.availablePermits());