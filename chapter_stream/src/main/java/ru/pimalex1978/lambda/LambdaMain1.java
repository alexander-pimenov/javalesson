package ru.pimalex1978.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaMain1 {
    public static void main(String[] args) {
        //даны массивы строк
        String[] array1 = {"мама", "мыла", "раму"};
        String[] array2 = {"я", "очень", "люблю", "java"};
        String[] array3 = {"мир", "май", "труд"};

        List<String[]> arrays = new ArrayList<>();
        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);

        //1 вариант: в логике метода compare запишем сравнение по длинне массива
        Comparator<String[]> sortByLength = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1.length - o2.length;
            }
        };

        //2 вариант: в логике метода compare запишем логику определения длин всех слов в массиве
        //и потом сравнение массивов по общей длинне слов в нем
        Comparator<String[]> sortByWordsLength = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int length1 = 0;
                int length2 = 0;

                for (String s : o1) {
                    length1 += s.length();
                }

                for (String s : o2) {
                    length2 += s.length();
                }

                return length1 - length2;
            }
        };

        System.out.println(Arrays.asList(array1));
        System.out.println(Arrays.asList(array2));
        System.out.println(Arrays.asList(array3));

        //по 1 варианту
        arrays.sort(sortByLength);

        for (String[] arr : arrays) {
            System.out.println(Arrays.asList(arr));
        }
        //[мама, мыла, раму]
        //[мир, май, труд]
        //[я, очень, люблю, java]

        //по 2 варианту
        arrays.sort(sortByWordsLength);

        for (String[] arr : arrays) {
            System.out.println(Arrays.asList(arr));
        }
        //[мир, май, труд]
        //[мама, мыла, раму]
        //[я, очень, люблю, java]

        //3 вариант: можно сходу подставить нужную нам реализацию компаратора
        //например сотировка по убыванию
        //И вот она красивая лямбда:
        arrays.sort(((o1, o2) -> o2.length - o1.length));
        for (String[] arr : arrays) {
            System.out.println(Arrays.asList(arr));
        }
        //[я, очень, люблю, java]
        //[мир, май, труд]
        //[мама, мыла, раму]

        //Простой пример лямбда-выражения для интерфейса Runnable (Ничего не принимает, ничего не возвращает)
        //просто выводит на печать
        new Thread(() -> System.out.println("Hello world (1) !!!")).start();

        //или еще так это записать, когда можем сохранить лямбду в переменную:
        Runnable runnable = () -> System.out.println("Hello world (2) !!!");
        Thread t = new Thread(runnable);
        t.start();

        //или такая лямбда реализующая Runnable в несколько строк:
        Runnable r2 = () -> {
            System.out.println("Hello world (3) !!!");
            System.out.println(array1[0]);
            System.out.println(array1[1]);
            System.out.println(array1[2]);
        };

        Thread t2 = new Thread(r2);
        t2.start();

    }
}
