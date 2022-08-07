package ru.pimalex1978.streamepam;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    static Stream<Integer> stream;

    public static void main(String[] args) {
        List<String> list = Arrays.asList("usa ", " RUSSIA ", "gerMANY", "JaPaN!");

        //так писали до Java5, но здесь есть управление обходом
        //и такие вещи ничем не заменить, если это обоснованно
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        //используем цикл forEach
        for (String item : list) {
            System.out.println(item);
            //переводим всё к малым буквам, убираем лишние пробелы
            String newItem = item.toLowerCase().trim();
            //например, если название содержит букву "p", то это слово будем пропускать
            if (newItem.contains("p")) {
                continue;
            }
            System.out.println(newItem);
        }

        //используем метод forEach для коллекций
//        list.forEach(System.out::println);

        //используем стрим
        //весь стрим идет от интерфейса Stream
        list.stream()
                .map(e -> e.toLowerCase().trim()) //из одного элемента делаем один элемент
                .filter(elem -> !elem.contains("p")) //фильтруем
                .forEach(System.out::println);

        /*2 вариант*/
        stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7)
                .stream() //создали стрим
                .filter(i -> i > 4) //отфильтровали те что больше 4
                .map(i -> 1); //отобразили все оставшиеся числа в 1

        System.out.println(stream.count());

//        test1(); //будет выброшен иксепшн

        /*3 вариант. генераторы*/
        //бесконечный генератор целых чисел от 0 с шагом 2
        IntStream.iterate(0, i -> i + 2) //идем по элементам со сдвигом 2
                .limit(10) //нужно 10 элементов
                .forEach(e -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    System.out.println(e);
                });

        /*4 вариант*/
        //пример отсортированности. метод sorted чувствителен к разнотипности.
        //sorted сам приводить типы не умеет.
        //нужно чтобы из источника тек один тип данных, например Double
        Arrays.asList(new Double(2), new Double(6),
                new Double(4.5), new Double(8),
                new Double(6.3), new Double(1),
                new Double(4.7), new Double(-66),
                new Double(0.258))
                .stream()
                .sorted() //сделаем числа упорядоченными
                .forEach(System.out::println);

        /*5 вариант*/
        IntStream is = IntStream.of(1, 3, 5);
        OptionalDouble average = is.filter(i -> i % 2 == 0).average(); //1
//        int x = is.filter(i -> i % 2 == 0).average(); //1 - не верная запись, т.к. возвращается объект OptionalDouble
        System.out.println(average.isPresent() ? average.getAsDouble() : -1.0); //если нет таких чисел то вернем -1.0

        IntStream is2 = IntStream.of(1, 3, 5);
        int y = is2.filter(i -> i % 2 != 0).sum(); //2
        System.out.println(y);

        is = IntStream.of(1, 3, 5, 9);
        long count = is.filter(i -> i % 3 != 0).count(); //3
//        int z = is.filter(i -> i % 3 != 0).count(); //3 - не верная запись, т.к. возвращается long
        System.out.println(count);


    }

    public static void test1() {
        final long count = stream.count();
        System.out.println(count);
    }
}
