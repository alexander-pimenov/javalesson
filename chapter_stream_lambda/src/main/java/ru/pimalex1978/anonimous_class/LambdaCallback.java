package ru.pimalex1978.anonimous_class;

import java.util.function.Consumer;

/**
 * Пример работы с лямбдой вместо анонимного класса
 * Пример выполнения в одном потоке
 */
public class LambdaCallback {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("***Start of main method***");
        performActionR(() -> System.out.println("Callback function executed..."));

        performAction1(s -> {
            System.out.println(s + "_1");
            System.out.println(s + "_2");
            System.out.println(s + "_3");
            System.out.println(s + "_4");
        });

        performAction2(s -> System.out.println(s), "это моё сообщение");

        System.out.println("***End of main method***");
    }

    public static void performActionR(Runnable runnable) throws InterruptedException {
        System.out.println("(R) Action is being performed ....");
        Thread.sleep(2000L);
        runnable.run();
    }

    public static void performAction1(Consumer<String> consumer) throws InterruptedException {
        System.out.println("(1) Action is being performed ....");
        Thread.sleep(3000L);
        consumer.accept("(1) Callback is executed");
    }

    public static void performAction2(Consumer<String> consumer, String message) throws InterruptedException {
        System.out.println("(2) Action is being performed ....");
        Thread.sleep(4000L);
        consumer.accept("(2) Callback is executed" + " " + message);
    }
}
