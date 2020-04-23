package ru.pimalex1978.stream;

/* Этот класс был создан для тренировки. Отработка разных методом в стримах.
 * Возможно нет сопроводительного текста.
 *
 * Stream Api позволяет писать обработку структур данных в стиле SQL,
 * то если раньше задача получить сумму всех нечетных чисел из
 * коллекции решалась кодом с циклами, то сейчас в функциональном стиле.*/

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SunOdd {
    public static void main(String[] args) {


        System.out.println("======Поиск суммы нечетных чисел с помощью for и stream====");

        List<Integer> collection = (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9));

        //с циклом for
        Integer sumOdd = 0;
        for (Integer i : collection) {
            if (i % 2 != 0) {
                sumOdd += i;
            }
        }
        System.out.println(sumOdd);
        //sream
        Integer sumOddStream = collection.stream().sorted().distinct().filter(o -> o % 2 != 0).reduce((s1, s2) -> s1 + s2).orElse(0);
        System.out.println(sumOddStream);

        System.out.println("======Рандомная генерация чисел для стрима методом ints====");
        //Random(1) - при разных запусках дает оди и теже числа, если будет Random() - то числа будут разные
        //ints(20, 0,10) - всего вырабатывается 20 числе, в диапазоне от 0 до 10 (исключительно)
        int[] ints;
        ints = new Random(1).ints(20, 0, 10).toArray();
        System.out.println(Arrays.toString(ints));
        Integer[] integers;
        integers = new Random(1).ints(20, 0, 10)
                .boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(integers));

        //Нам необходима сумма пяти псевдослучайных чисел.
        //генерируются числа от 1 до 20, много, т.к. нет ограничения streamSize потом их limit(5) и остаток 5 суммируется.
        int sum = new Random().ints(1, 20 + 1)
                .distinct()
                .limit(5)
                .peek(System.out::println) //промежуточнй вывод наших чисел
                .sum();
        System.out.println("sum = " + sum);

        System.out.println("======Преобразование List в Map======");
        System.out.println(List.of(1, 1, 2, 2, 3, 4).stream()
                .distinct() //это чтобы ключи были уникальны
                .collect(Collectors.toMap(
                        e -> e,
                        e -> e * e
                )));
    }
}