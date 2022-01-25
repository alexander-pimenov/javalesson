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
import java.util.stream.Stream;

public class StreamExamples {
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

        //stream
        Integer sumOddStream = collection.stream()
                .sorted()
                .distinct()
                .filter(o -> o % 2 != 0)
                .reduce((s1, s2) -> s1 + s2)
                .orElse(0);
        System.out.println(sumOddStream);

        System.out.println("======Рандомная генерация чисел для стрима методом ints====");
        //Random(1) - при разных запусках дает оди и теже числа, если будет Random() - то числа будут разные
        //ints(20, 0, 10) - всего вырабатывается 20 числе, в диапазоне от 0 до 10 (исключительно)
        int[] ints;
        ints = new Random(1)
                .ints(20, 0, 10)
                .toArray();
        System.out.println(Arrays.toString(ints));
        Integer[] integers;
        integers = new Random(1)
                .ints(20, 0, 10)
                .boxed()
                .toArray(Integer[]::new);
        System.out.println(Arrays.toString(integers));

        //Нам необходима сумма пяти псевдослучайных чисел.
        //генерируются числа от 1 до 20, много, т.к. нет ограничения streamSize потом их limit(5) и остаток 5 суммируется.
        int sum = new Random().ints(1, 20 + 1)
                .distinct()
                .limit(5)
                .peek(System.out::println) //промежуточнй вывод наших чисел
                .sum();
        System.out.println("sum = " + sum);

        //
        System.out.println("======Преобразование List в Map======");
        System.out.println(List.of(1, 1, 2, 2, 3, 4).stream()
                .distinct() //это чтобы ключи были уникальны
                .collect(Collectors.toMap(
                        e -> e, //ключ
                        e -> e * e //значение
                )));

        //каждый элемент коллекции умножаем на 2
        System.out.println("======Каждый элемент коллекции умножаем на 2======");
        List<Integer> collect = collection.stream()
                .map(e -> e * 2)
                .collect(Collectors.toList());
        System.out.println(collect);

        //Создание и заполнение рандомными числами от 0 до 50 списка из 10 элементов
        System.out.println("======Создание и заполнение рандомными числами от 0 до 50 списка из 10 элементов\n\rи умножение каждого на 2======");
        List<Integer> filteredList = Stream.generate(() -> new Random().nextInt(50))
                .limit(10) //выбираем только 10 шт
                .filter(e -> e > 5) //которые > 5
                .map(e -> e * 2) //умножаем все эл-ты на 2
                .collect(Collectors.toList()); //собираем в список
        System.out.println(filteredList);

        //замена всех эл-тов в списке на число 5
        System.out.println("======Замена всех эл-тов в списке на число 5======");
        List<Integer> listOf5s = filteredList.stream()
                .map(e -> 5)
                .collect(Collectors.toList());
        System.out.println(listOf5s);

        //Генерация списка рандомных целых чисел с помощью метода ints класса Random
        System.out.println("======Генерация списка рандомных целых чисел с помощью метода ints класса Random======");
        List<Integer> integerList = new Random(1).ints(20, 10, 50) //ints(20, 10, 50) - всего вырабатывается 20 числе, в диапазоне от 10 до 50 (исключительно)
                .distinct()
                .boxed() //упакуем в stream Integer
                .collect(Collectors.toList());
        System.out.println(integerList);

        //reduce method (уменьшение до одного элемента)
        //считаем сумму эл-тов списка
        //acc - аккумулятор, аккумулирует данные, каждый раз обновляется, это как переменная счетчик.
        //По умолчанию на первой итерации он равняется первому эл-ту в списке,
        //или так же можно устанавливать своё значени, например 4.
        //currEl - текущий элемент, прибавляем к аккумулятору (установленному или по-умолчанию)
        System.out.println("======Суммирование всех чисел в списке======");
        Integer summ = integerList.stream()
                .reduce((acc, currEl) -> acc + currEl) //отсчет с нуля (аналогично (reduce(0, (acc, currEl) -> acc + currEl)))
                .get(); //возвращается Optional<Integer>поэтому нужно сделать get()
        Integer summ2 = integerList.stream()
                .reduce(4, (acc, currEl) -> acc + currEl); //отсчет с 4
        System.out.println(summ);
        System.out.println(summ2);


    }
}