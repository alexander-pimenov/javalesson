package ru.pimalex1978.concurrent.wait_notify_daemon;

/**
 * Поток-демон, daemon
 * Java приложение завершает работу тогда, когда завершает работу последний его
 * поток. Даже если метод main() уже завершился, но еще выполняются порожденные
 * им потоки, система будет ждать их завершения.
 * <p>
 * Однако это правило не относится к потоков-демонам (daemon). Если завершился
 * последний обычный поток процесса, и остались только daemon потоки, то они
 * будут принудительно завершены и выполнение приложения закончится. Чаще
 * всего daemon потоки используются для выполнения фоновых задач, обслуживающих
 * процесс в течение его жизни.
 * <p>
 * Объявить поток демоном достаточно просто. Для этого нужно перед запуском потока
 * вызвать его метод setDaemon(true). Проверить, является ли поток daemon'ом
 * можно вызовом метода isDaemon(). В качестве примера использования daemon-потока
 * можно рассмотреть класс Trade, который принял бы следующий вид.
 * <p>
 * Здесь можно самостоятельно поэкспериментировать с определением daemon-потока
 * для одного из классов (producer, consumer) или обоих классов, и посмотреть,
 * как система (JVM) будет вести себя.
 * <p>
 * Также можно менять врямя sleep для Producer и Consumer (Thread.sleep(30)).
 */

public class TradeDaemon {
    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

//		new Thread(producer).start();
//		new Thread(consumer).start();

        Thread tp = new Thread(producer);
        Thread tc = new Thread(consumer);

        tp.setDaemon(true);
        tc.setDaemon(true);

        tp.start();
        tc.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        System.out.println("\nГлавный поток завершен\n");
        System.exit(0);
    }
}
