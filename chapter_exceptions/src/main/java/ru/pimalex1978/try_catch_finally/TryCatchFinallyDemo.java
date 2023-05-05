package ru.pimalex1978.try_catch_finally;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.function.Supplier;

/*Как видно из примера, что блок finally отрабатывает всегда*/
public class TryCatchFinallyDemo {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        System.out.println("==Пример работы сблоками try-catch-finally==");
        System.out.println("==Что выведется в консоль при разных способах?==");
        try {
            System.out.println("A");
            badMethod();
            System.out.println("B");
        } catch (Exception ex) {
            System.out.println("C");
        } finally {
            System.out.println("D");
        }


        Supplier<Integer> integerSupplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    return 1;
                } catch (Exception ignored) {
                    System.out.println("Мы в блоке catch");
                    return 2;
                } finally {
                    System.out.println("Мы в блоке finally");
                    return 3;
                }
            }
        };
        System.out.println(integerSupplier.get()); //3

        Supplier<Integer> integerSupplierWithException = new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    throw new RuntimeException();
                } catch (Exception ignored) {
                    System.out.println("Мы в блоке catch");
                    return 2;
                } finally {
                    System.out.println("Мы в блоке finally");
                    return 3;
                }
            }
        };
        System.out.println(integerSupplierWithException.get());


        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(TryCatchFinallyDemo.class.getClassLoader().getResource("test.txt").toURI()));
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
                throw new RuntimeException("Something happened on reading");
            }
        } finally {
            if (scanner != null) {
                System.out.println("Мы в блоке finally");
                scanner.close();
                //выкидывать отсюда исключение - это плохая практика, т.к. оно перезаписывает иксепшн, который кидается из блока try.
                //строку ниже можно закоментить и увидим, что выводится
                throw new RuntimeException("Something happened on closing");
            }
        }


        //В продолжении примера выше, лучше делать с использованием try-with-recourses, что был автоклозебл сканера:
        try (Scanner scanner2 = new Scanner(new File(TryCatchFinallyDemo.class.getClassLoader().getResource("test.txt").toURI()))) {
            while (scanner2.hasNext()) {
                System.out.println(scanner2.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //для примера нужно проверить разный закомментированный код 1 или 2
    public static void badMethod() {
        throw new RuntimeException(); //1-й
//        throw new Error(); //2-й
    }
}
