package ru.pimalex1978.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static ru.pimalex1978.concurrent.ColorScheme.*;

/**
 * Если в наших заданиях нужно использовать много потоков, а не один-
 * два или нужно выполнить большое количество задач в несколько
 * потоков, то создавать потоки через new Thread() не правильно и не
 * эффективно, т.к. Thread() - это тяжелый объект и при выполнении
 * new Thread() выполняется большое количество работы и вовлекается
 * не только JVM, но и ядро операционной системы и выделяется часть памяти.
 * Т.е. под каждый new Thread() выделяется кусок памяти, т.обр.
 * происходит фрагментация памяти.
 * Эту проблему помагают решить экзекьюторы (executors).
 * ExecutorService.
 * Он помогает создать потоки заранее, отделяет код, который создает
 * потоки от кода, который выполняет бизнес-лигику.
 * Потоки можно использовать несколько раз. Поток может выполнить
 * задание и вернуться в пул потоков. Потом ожет быть использован для
 * выполнения следующего задания.
 * <p>
 * Если имеем большое количество итераций и нам нужно будет завершать
 * работу потока, то делать это нужно правильно. Поможет метод interrupt().
 * Он нужен для остановки работы потока. Но его мало вызвать, поток нужно
 * проверять был ли поток прерван. Т.е. эта проверка должна быть в классе
 * реализующем Runnable - GCDRunnable.
 * runInOneThread().
 * <p>
 * Так же можно останавливать поток с помощью опроса логической переменной.
 * runOneThreadWithBooleanVariable().
 * <p>
 * Создаваемые нами потоки Thread в которых мы выполняем какую то работу,
 * называются USER потоки. Они работают пока не выполнят все свои задачи.
 * <p>
 * Есть также Daemon потоки, которые живут пока живет main поток!!!
 * <p>
 * Чтобы USER (пользовательский) поток превратить в Daemon нужно установить
 * setDaemon(true).
 */
public class Launcher {
    private static final int POOL_SIZE = 2;

    public static void main(String[] args) throws InterruptedException {
        //переменная для выбора типа потока
        boolean isDaemon = true;

        System.out.println(RED + "Starting main thread");
//        GCDRunnable r = new GCDRunnable(); //инстанс нашего Runnable
        GCDRunnable r = new GCDRunnable(isDaemon); //инстанс нашего Runnable
//        runInOneThread(r, isDaemon);
//        runOneThreadWithBooleanVariable(isDaemon);
        runWithExecutors(r, isDaemon);
//        runWithExecutorsAndSingleThread(r,true);
        System.out.println(RED + "Leaving the main thread");
    }

    /**
     * Метод для работы с одним потоком, это чтоб не смешивать
     * работу с executors.service
     * Создаем поток. Стартуем его. Спим секунду. Останавливаем поток.
     * Обработка остановки потока в самом классе GCDRunnable.
     *
     * @throws InterruptedException исключение возможно.
     */
    private static void runInOneThread(GCDRunnable r, boolean isDaemon) throws InterruptedException {
//        Thread th = new Thread(r);
        Thread th = new Thread(r, "MyThreadWithInterruption");

        if (isDaemon) {
            th.setDaemon(true);
        }
        //стартуем поток
        th.start();
        //Работаем 0,5-1 секунду
        Thread.sleep(500);
        //Прерываем поток
        th.interrupt();
    }

    /**
     * Метод для работы с одним потоком, котрый останавливаем с помощью логической
     * переменной, которую сбрасываем методом disable() в положение false.
     *
     * @throws InterruptedException исключение возможно.
     */
    private static void runOneThreadWithBooleanVariable(boolean isDaemon) throws InterruptedException {
        MyThread myThread = new MyThread(isDaemon);
//        MyThread myThread = new MyThread();
        Thread th = new Thread(myThread, "MyThreadWithBooleanVariable");
        if (isDaemon) {
            th.setDaemon(true);
        }
        th.start();
        //Работаем 8 секунд
        Thread.sleep(8000);
        //вызываем метод из класса MyThread
        myThread.disable();
        //Еще ждем 1 секунду
        Thread.sleep(1000);
    }

    /**
     * Метод по созданию пула потоков для выполнения работы.
     * Здесь с помощью ThreadFactory мы можем задавать тип потока, даже
     * когда используем ExecutorService.
     *
     * @param r        объект Runnable
     * @param isDaemon указатель демон поток или нет
     * @throws InterruptedException исключение
     */
    private static void runWithExecutors(GCDRunnable r, boolean isDaemon) throws InterruptedException {

        //Для того чтобы в пуле установить демон потоки нужно использовать
        //ThreadFactory и переопределить метод newThread
        ThreadFactory factory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                if (isDaemon) {
                    thread.setDaemon(true);
                }
                return thread;
            }
        };

        //Создаем пул потоков содержащий 2 потока
        ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE, factory);
        // в цикле добавляем 5 заданий
        for (int i = 0; i < 5; i++) {
            //метод execute() для запуска задачи, заданной запускаемым объектом Runnable
            executorService.execute(r);
        }
        //говорим, чтоб executorService завершил свою работу, как выполнил все задания
        //После этого исполнитель перестанет принимать какие-либо новые потоки и завершит все существующие в очереди.
        executorService.shutdown();

        //Для завершения потоков в принудительном порядке за 60 секунд.
        //если будут зависшие потоки, то они завершат свою работу.
        executorService.awaitTermination(60, TimeUnit.SECONDS);
    }

    /**
     * В данном примере мы отправляем на исполнение пять задач. Но так как
     * применяется метод newSingleThreadExecutor(), будет создан только один
     * новый поток и одновременно будет выполняться только одна задача.
     * Остальные четыре задачи находятся в очереди ожидания. Как только задача
     * выполнится потоком, этот поток тут же выберет и выполнит следующую.
     * Т.е. задачи выполняться одна за другой, последовательно.
     *
     * @param r        объект Runnable
     * @param isDaemon указатель демон поток или нет
     * @throws InterruptedException исключение
     */
    private static void runWithExecutorsAndSingleThread(GCDRunnable r, boolean isDaemon) throws InterruptedException {
        //
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // в цикле добавляем 5 заданий
        for (int i = 0; i < 5; i++) {
            //метод execute() для запуска задачи, заданной запускаемым объектом Runnable
            executorService.execute(r);
        }
        //говорим, чтоб executorService завершил свою работу, как выполнил все задания
        //После этого исполнитель перестанет принимать какие-либо новые потоки и завершит все существующие в очереди.
        executorService.shutdown();
    }
}
