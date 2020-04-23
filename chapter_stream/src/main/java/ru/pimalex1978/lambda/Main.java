package ru.pimalex1978.lambda;

import java.util.function.Consumer;

/**
 * Пример реализации функционального интерфейса через лямбда.
 */

public class Main {
    public static void main(String[] args) {

        //Запись кода с лямбдами, это с java 8 и выше
        execute(() -> {
            //Нужный нам кусок кода.
            System.out.println("Hello Topjava!!!");
        });

        //Запись кода без лямбд, как в java 7
//        execute(new Runnable() {
//            @Override
//            public void run() {
//                //Нужный нам кусок кода.
//                System.out.println("Hello Topjava!!!");
//            }
//        });

        consume(str -> System.out.println(str), "Hello Topjava!!!");

//        consume(new Consumer<String>() {
//            @Override
//            public void accept(String str) {
//                System.out.println(str);
//            }
//        }, "Hello Topjava!!!");

    }

    /*Возьмем стандартный паттерн стратегия.
     * Это когда мы передаем в метод или в класс кусок кода,
     * который там внутри исполняется. Его может окружать другой код. */
    private static void execute(Runnable runnable) {
        System.out.println("Start runner");
        runnable.run();
        System.out.println("End runner");
    }

    /*Используем интерфейс Consumer*/
    private static <String> void consume(Consumer<String> consumer, String out) {
        System.out.println("Start consumer");
        consumer.accept(out);
        System.out.println("End consumer");
    }
}

//Вывод программы:
//
//Start runner
//Hello Topjava!!!
//End runner
//Start consumer
//Hello Topjava!!!
//End consumer