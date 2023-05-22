package ru.pimalex1978.stream;

import java.util.stream.Stream;

public class StreamApp {
    public static void main(String[] args) {


        //вариант 1
        //(!!!) Внимательно посмотри, как обрабатываются элементы в стриме (!!!)
        // с этом стриме каждый элемент оборачивается в блоке map(),
        // потом все заходят в sorted(), но он бесполезный и ничего не сортирует,
        //а потом вызывается anyMatch() и на втором элементе завершается стрим.
        //d2 -> b1 -> a2 -> D2 -> A2

        Stream.of("d2", "a2", "b1", "c3", "g4", "h5", "f6")
                .map(s -> {
                    System.out.print(s + " ");
                    return s.toUpperCase();
                })
                .sorted((first, second) -> {
                    return 0;
                })
                .anyMatch(s -> {
                    System.out.print(s + " ");
                    return s.startsWith("A");
                });
        //d2 a2 b1 c3 g4 h5 f6 D2 A2
        System.out.println("\n============");

        //вариант 2
        //с этом стриме для каждого элемента поочереди вызывается блок map(),
        //а потом вызывается anyMatch() и на втором элементе завершается стрим.
        //d2 -> D2 -> a2 -> A2
        Stream.of("d2", "a2", "b1", "c3", "g4", "h5", "f6")
                .map(s -> {
                    System.out.print(s + " ");
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.print(s + " ");
                    return s.startsWith("A");
                });
        //d2 D2 a2 A2
        System.out.println("\n============");


        //Если я использую myFunc(), то обработка будет как в варианте 2 (!!!)
        String[] strs = {"d2", "a2", "b1", "c3", "g4", "h5", "f6"};
        boolean b = myFunc(strs);
        //d2 D2 a2 A2
    }

    public static boolean myFunc(String[] list) {
        for (String my : list) {
            System.out.print(my + " ");
            System.out.print(my.toUpperCase() + " ");
            if (my.toUpperCase().startsWith("A")) {
                return true;
            }
        }
        return false;
    }
}
