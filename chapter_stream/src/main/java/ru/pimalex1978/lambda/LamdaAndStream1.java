package ru.pimalex1978.lambda;

import java.util.Arrays;
import java.util.List;

/*Поколдовав над Lambda-выражениями можно вывести квадрат каждого элемента списка.
 Заметьте, что мы используем метод stream(), чтобы преобразовать обычный список
 в поток. Java 8 предоставляет шикарный класс Stream (java.util.stream.Stream).
 Он содержит тонны полезных методов, с которыми можно использовать lambda-выражения.
 Мы передаем lambda-выражение x -> x*x в метод map(), который применяет его ко
 всем элементам в потоке. После чего мы используем forEach для печати всех
 элементов списка.*/
public class LamdaAndStream1 {
    public static void main(String[] args) {
        //создадим список целых чисел
        List<Integer> list = Arrays.asList(1, 3, 5, 6, 7, 9, 11, 23, 45, 68);

        System.out.println("Выводит квадраты всех чисел: ");

        //старый способ
        for (Integer n : list) {
            int x = n * n;
            System.out.print(x + " ");
        }
        System.out.println();

        //новый способ
        list.stream()
                .map(x -> x * x)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        System.out.println("Выводит сумму квадратов всех чисел: ");
        //старый способ
        int sum = 0;
        for (Integer n : list) {
            int x = n * n;
            sum = sum + x;
        }
        System.out.println(sum);

        //новый способ
        int summ = list.stream()
                .map(x -> x * x)
                .reduce((x, y) -> x + y)
                .get();
        System.out.println(summ);

    }
}
