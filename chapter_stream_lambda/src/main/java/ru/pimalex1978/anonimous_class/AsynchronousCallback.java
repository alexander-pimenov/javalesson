package ru.pimalex1978.anonimous_class;

/**
 * Пример работы выполнения в разных потоках
 * <p>
 * Примером из реальной жизни может быть ситуация, когда мы покупаем продукт в Интернете,
 * и нам не нужно ждать подтверждения платежа, проверки запасов и всех этих тяжелых процессов
 * загрузки. В этом случае мы можем делать другие вещи, пока вызов обратного вызова
 * выполняется в фоновом режиме.
 */
public class AsynchronousCallback {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("***Start of main method***");
        //Реализуем интерфейс (описываем его функциональность), потом передадим его в метод
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName() +
                " (Реализуем функциональность интерфейса) Callback executed...");

        AsynchronousCallback asynchronousCallback = new AsynchronousCallback();
        asynchronousCallback.performAsynchronousAction1(runnable);


        asynchronousCallback.performAsynchronousAction2(runnable);

        System.out.println("***End of main method***");
    }

    /**
     * Здесь в отдельном потоке запускается выполнение функции
     *
     * @param runnable объект интерфейса Runnable
     * @throws InterruptedException возможно исключение
     */
    public void performAsynchronousAction1(Runnable runnable) throws InterruptedException {
        System.out.println("_Method starts_1. Action is being performed ....");
        Thread.sleep(1000L);
        new Thread(() -> {
            try {
                Thread.sleep(500L);
                System.out.println("In Thread_1. Processing Asynchronous Task...");
                Thread.sleep(1500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            runnable.run();
        }).start(); // здесь вызывается выполнение переданной функции как runnable
        System.out.println("_Method ended_1");
    }

    /**
     * Здесь в отдельном потоке запускается выполнение функции.
     * Код будет похож на предыдущий пример, но обратите внимание, что вместо
     * непосредственного вызова функции обратного вызова мы запустим новый поток
     * и вызовем функцию обратного вызова в этом новом потоке.
     * Асинхронный параллельный обратный вызов полезен, когда нам не нужно,
     * чтобы функция обратного вызова выполнялась сразу после действия из метода
     * PerformAsynchronousAction().
     *
     * @param runnable объект интерфейса Runnable
     * @throws InterruptedException возможно исключение
     */
    public void performAsynchronousAction2(Runnable runnable) throws InterruptedException {
        System.out.println("_Method starts_2. Action is being performed ....");
        Thread.sleep(1000L);
        new Thread(() -> {
            try {
                Thread.sleep(1500L);
                System.out.println("In Thread_2. Processing Asynchronous Task...");
                Thread.sleep(2500L);
                new Thread(runnable).start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            runnable.run();
        }).start(); // здесь вызывается выполнение переданной функции как runnable

        System.out.println("_Method ended_2");
    }
}


