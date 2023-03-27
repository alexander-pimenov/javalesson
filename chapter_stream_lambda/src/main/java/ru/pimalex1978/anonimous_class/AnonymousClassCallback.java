package ru.pimalex1978.anonimous_class;

import java.util.function.Consumer;

/**
 * Пример работы с анонимным классом (который заменяется на лямбду)
 */
public class AnonymousClassCallback {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start of main method");
     performAction(new Consumer<String>() {
         @Override
         public void accept(String s) {
             System.out.println(s + "_1");
             System.out.println(s + "_2");
             System.out.println(s + "_3");
             System.out.println(s + "_4");
         }
     });
     performAction2(new Consumer<String>() {
         @Override
         public void accept(String s) {
             System.out.println(s);
         }
     }, "это моё сообщение");
        System.out.println("End of main method");
    }

    public static void performAction(Consumer<String> consumer) throws InterruptedException {
        System.out.println("Action is being performed ....");
        Thread.sleep(5000L);
        consumer.accept("Callback is executed");
    }

    public static void performAction2(Consumer<String> consumer, String message) throws InterruptedException {
        System.out.println("(2) Action is being performed ....");
        Thread.sleep(2000L);
        consumer.accept("(2) Callback is executed" + " " + message);
    }
}
