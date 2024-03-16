package ru.pimalex1978.stream.streamapi;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Примеры работы методов Stream Api
 * <p>
 * Created by vedenin on 17.10.15.
 */

public class CollectAndToArrayTests {
    // Метод collect преобразует stream в коллекцию или другую структуру данных
    // Полезные статические методы из Collectors:
    // toList, toCollection, toSet - представляют стрим в виде списка, коллекции или множества
    // toConcurrentMap, toMap - позволяют преобразовать стрим в map, используя указанные функции
    // averagingInt, averagingDouble, averagingLong - возвращают среднее значение
    // summingInt, summingDouble, summingLong - возвращает сумму
    // summarizingInt, summarizingDouble, summarizingLong - возвращают SummaryStatistics с разными агрегатными значениями
    // partitioningBy - разделяет коллекцию на две части по соотвествию условию и возвращает их как Map<Boolean, List>
    // groupingBy - разделить коллекцию по условию и вернуть Map<N, List<T>>, где T - тип последнего стрима, N - значение разделителя
    // mapping - дополнительные преобразования значений для сложных Collector'ов
    private static void testCollect() {
        System.out.println();
        System.out.println("Test distinct start");

        // ******** Работа с числами
        Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        // Получить сумму нечетных чисел
        long sumOdd = numbers.stream().collect(Collectors.summingInt(((p) -> p % 2 == 1 ? p : 0))); //или так:
        //long sumOdd = numbers.stream().mapToInt(((p) -> p % 2 == 1 ? p : 0)).sum();
        System.out.println("sumOdd = " + sumOdd); // напечатает sumEven = 4

        // Вычисть к каждого элемента 1 и получить среднее
        double average = numbers.stream().collect(Collectors.averagingInt((p) -> p - 1));
        System.out.println("average = " + average); // напечатает average = 1.5

        // Прибавить к числам 3 и получить статистику
        IntSummaryStatistics statistics = numbers.stream().collect(Collectors.summarizingInt((p) -> p + 3));
        System.out.println("statistics = " + statistics); // напечатает statistics = IntSummaryStatistics{count=4, sum=22, min=4, average=5.500000, max=7}

        // Получить сумму четных чисел через IntSummaryStatistics
        long sumEven = numbers.stream().collect(Collectors.summarizingInt((p) -> p % 2 == 0 ? p : 0)).getSum();
        System.out.println("sumEven = " + sumEven); // напечатает sumEven = 6

        // Разделить числа на четные и нечетные
        Map<Boolean, List<Integer>> parts = numbers.stream().collect(Collectors.partitioningBy((p) -> p % 2 == 0));
        System.out.println("parts = " + parts); // напечатает parts = {false=[1, 3], true=[2, 4]}

        // ******** Работа со строками
        Collection<String> strings = Arrays.asList("a1", "b2", "c3", "a1", "b2", "a1", "c3");

        // Получение списка из коллекции строк без дубликатов
        List<String> distinct = strings.stream().distinct().collect(Collectors.toList());
        System.out.println("distinct = " + distinct); // напечатает distinct = [a1, b2, c3]

        // Получение массива уникальных значений из коллекции строк
        String[] array = strings.stream().distinct().map(String::toUpperCase).toArray(String[]::new);
        System.out.println("array = " + Arrays.asList(array)); // напечатает array = [A1, B2, C3]

        // Объединить все элементы в одну строку через разделитель : и обернуть тегами <b> ... </b>
        String join = strings.stream().collect(Collectors.joining(" : ", "<b> ", " </b>"));
        System.out.println("join = " + join); // напечатает <b> a1 : b2 : c3 : a1 : b2 : a1 : c3 </b>

        // Преобразовать в map, где первый символ ключ, второй символ значение
        Map<String, String> map = strings.stream().distinct().collect(Collectors.toMap((p) -> p.substring(0, 1), (p) -> p.substring(1, 2)));
        System.out.println("map = " + map); // напечатает map = {a=1, b=2, c=3}

        // Преобразовать в map, сгруппировав по первому символу строки
        Map<String, List<String>> groups = strings.stream().collect(Collectors.groupingBy((p) -> p.substring(0, 1)));
        System.out.println("groups = " + groups); // напечатает groups = {a=[a1, a1, a1], b=[b2, b2], c=[c3, c3]}

        // Преобразовать в map, сгруппировав по первому символу строки и в качестве значения взять второй символ объединив через ":"
        Map<String, String> groupJoin = strings.stream()
                .collect(Collectors.groupingBy((p) -> p.substring(0, 1), Collectors.mapping((p) -> p.substring(1, 2), Collectors.joining(":"))));
        System.out.println("groupJoin = " + groupJoin); // напечатает groupJoin = {a=1:1:1, b=2:2, c=3:3}

        // Напишем собственный Collector, который будет выполнять объединение строк с помощью StringBuilder
        Collector<String, StringBuilder, String> stringBuilderCollector = Collector.of(
                StringBuilder::new, // метод инициализации аккумулятора
                (b, s) -> b.append(s).append(" , "), // метод обработки каждого элемента
                (b1, b2) -> b1.append(b2).append(" , "), // метод соединения двух аккумуляторов при параллельном выполнении
                StringBuilder::toString // метод выполняющийся в самом конце
        );
        String joinBuilder = strings.stream().collect(stringBuilderCollector);
        System.out.println("joinBuilder = " + joinBuilder); // напечатает joinBuilder = a1 , b2 , c3 , a1 , b2 , a1 , c3 ,

        // Аналог Collector'а выше написанный стилем JDK7 представлен ниже
        StringBuilder b = new StringBuilder(); // метод инициализации аккумулятора
        for (String s : strings) {
            b.append(s).append(" , "); // метод обработки каждого элемента
        }
        String joinBuilderOld = b.toString(); // метод выполняющийся в самом конце
        System.out.println("joinBuilderOld = " + joinBuilderOld); // напечатает joinBuilderOld = a1 , b2 , c3 , a1 , b2 , a1 , c3 ,
    }

    public static void main(String[] args) throws Exception {
        testCollect();

        //
        List<Integer> numb = Arrays.asList(50, 10, 40, 30, 20, 100, 90, 80, 70);
        System.out.println("max numb = " + getMaxOrMinNumber(MinMax.MAX, numb));
        System.out.println("min numb = " + getMaxOrMinNumber(MinMax.MIN, numb));
        System.out.println("2-nd max numb = " + getSecondLargestOrSmallestNumber(MinMax.MAX, numb));
        System.out.println("2-nd min numb = " + getSecondLargestOrSmallestNumber(MinMax.MIN, numb));

        //частота повторения символов в строке
        var str = "javaisthebest";
        System.out.println("map of chars = " + frequencyOfEachCharacterInString(str));

        //разворот каждого отдельного слова в предложении
        var strStr = "Hello World This Is Java";
        System.out.println("reverse each word of string = " + reverseEachWordOfString(strStr));

        //удалим дубликаты из списка чисел (можно обойтись без stream и положить список в множество (set)
        List<Integer> listWithDuplicate = Arrays.asList(5, 1, 4, 3, 2, 10, 9, 8, 7, 1, 3, 5, 1, 3);
        System.out.println("remove duplicate elements from list = " + removeDuplicateElementsFromList(listWithDuplicate));


        //извлекаем последний элемент из строки
        List<String> listOfString = Arrays.asList("One", "Two",
                "Three", "Four", "Five", "Six");
        System.out.println("retrieve last element of list of string = " + retrieveLastElementOfListOfString(listOfString));

        //найти возраст человека, если задан его ДР
        LocalDate birthDate = LocalDate.parse("1991-03-24");

        LocalDate currentDate = LocalDate.now();

        int years = Period.between(birthDate, currentDate).getYears();
        System.out.println("years = " + years);

        System.out.println("years = " + findAgeOfPersonInYear(new Person("Alex", LocalDate.parse("1978-01-15"))));

        //только четные числа
        System.out.println("even numbers = " + getListOfEvenNumbersFromNumbersList(listWithDuplicate));

        //получим сумму всех чисел в числе
        int number = 12345;
        System.out.println("sum of all digits of number = " + getSumOfAllDigitsOfNumber(number));

        //отсортируем в алфавитном порядке слова
        System.out.println("sorted alphabetically = " + sortOfStringAlphabetically(Order.ASC,listOfString));
        System.out.println("sorted alphabetically = " + sortOfStringAlphabetically(Order.DESC,listOfString));


    }

    public static int getMaxOrMinNumber(MinMax opt, List<Integer> numbers) {
        if (opt == MinMax.MAX) {
            return numbers.stream()
                    .max(Comparator.naturalOrder())
                    .get();
        } else {
            return numbers.stream()
                    .min(Comparator.naturalOrder())
                    .get();
        }
    }

    public static int getSecondLargestOrSmallestNumber(MinMax opt, List<Integer> numbers) {
        if (opt == MinMax.MAX) {
            return numbers.stream()
                    .sorted(Comparator.reverseOrder())
                    .skip(1)
                    .findFirst()
                    .get();
        } else {
            return numbers.stream()
                    .sorted(Comparator.naturalOrder())
                    .skip(1)
                    .findFirst()
                    .get();
        }
    }

    public static Map<Character, Long> frequencyOfEachCharacterInString(String str) {
        IntStream intStream = str.chars(); //InputStream представляет ASCII значения для каждого символа
        Stream<Character> characterStream = intStream.mapToObj(c -> (char) c); //mapToObj - конвертирует каждое ASCII значение обратно в символ

        Map<Character, Long> collect = characterStream
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                TreeMap::new, //чтобы выстроить ключи по алфавиту
                                Collectors.counting()
                        ));
        return collect;
    }

    public static String reverseEachWordOfString(String str) {
        String[] strings = str.split(" ");
        return Arrays.stream(strings)
                .map((word) -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));
    }

    public static List<Integer> removeDuplicateElementsFromList(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

    }

    public static List<Integer> getListOfEvenNumbersFromNumbersList(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 == 0)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    public static int getSumOfAllDigitsOfNumber(int number) {
        IntStream intStream = String.valueOf(number).chars(); //InputStream представляет ASCII значения для каждого символа
        return intStream.map(Character::getNumericValue).sum();
    }

    public static String retrieveLastElementOfListOfString(List<String> listStr) {
        return listStr.stream()
                .skip(listStr.size() - 1)
                .findFirst()
                .get();
    }

    public static int findAgeOfPersonInYear(Person person) {
        LocalDate birthDate = person.getBirthDate();
        LocalDate currentDate = LocalDate.now();
        Period between = Period.between(birthDate, currentDate);
        return between.getYears();
    }

    public static List<String> sortOfStringAlphabetically(Order order, List<String> listStr) {
        if (order == Order.ASC) {
            return listStr.stream()
                    .sorted()
                    .collect(Collectors.toList());
        } else {
            return listStr.stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        }
    }

    private enum MinMax {
        MIN, MAX
    }

    private enum Order {
        ASC, DESC
    }
}
