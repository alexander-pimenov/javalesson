package ru.pimalex1978.stream;

/*
 * Рассмотрим различные варианты работы со стримами.
 * Примеры из статьи "Шпаргалка Java программиста 4. Java Stream API",
 * https://itnan.ru/post.php?c=1&p=270383
 * */

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainStream {
    public static void main(String[] args) throws IOException {

        /*1. Способы создания стримов*/
        //Создание стрима из коллекции: collection.stream()
        Collection<String> collection = Arrays.asList("a1", "a2", "a3");
        Stream<String> streamFromCollection = collection.stream();
        System.out.println("streamFromCollection = "
                + streamFromCollection
                .collect(toList())); //streamFromCollection = [a1, a2, a3]

        //Создание стрима из значений: Stream.of(значение1,… значениеN)
        Stream<String> streamFromValues = Stream.of("a1", "a2", "a3");
        System.out.println("streamFromValues = "
                + streamFromValues.collect(toList())); //streamFromValues = [a1, a2, a3]

        //Создание стрима из массива: Arrays.stream(массив)
        String[] array = {"a1", "a2", "a3"};
        Stream<String> streamFromArrays = Arrays.stream(array);
        System.out.println("streamFromArrays = " + streamFromArrays.collect(toList())); //streamFromArrays = [a1, a2, a3]

        //Создание стрима из файла (каждая строка в файле будет отдельным элементом в стриме)
        //Files.lines(путь_к_файлу)
        //.lines() - выбрасывает exception IOException
        Stream<String> streamFromFiles = Files.lines(Paths.get("text.info about lambda.txt"));
        /*Создадим файл и запишем туда что то*/
        File file = new File("1.tmp");
        file.deleteOnExit(); //Требует, чтобы файл или каталог, обозначенный этим абстрактным путем,
        // были удалены при завершении работы виртуальной машины.
        PrintWriter out = new PrintWriter(file); //Запишем данные в файл.
        out.println("a1");
        out.println("a2");
        out.println("a3");
        out.close();

        Stream<String> streamFromFile = Files.lines(Paths.get(file.getAbsolutePath()));
        System.out.println("streamFromFile = " + streamFromFile.collect(toList())); //streamFromFile = [a1, a2, a3]

        //Создание стрима из строки: «строка».chars()
        IntStream streamFromString = "123".chars();
        System.out.print("streamFromString = ");
        streamFromString.forEach(e -> System.out.print(e + " , ")); //streamFromString = 49 , 50 , 51 ,
        System.out.println();

        //С помощью Stream.builder: Stream.builder().add(...)....build()
        //или так:
        //Stream.builder().add("a1").add("a2").add("a3").build();
        //или так:
        Stream.Builder<String> builder = Stream.builder();
        Stream<String> streamFromBuilder = builder.add("a1")
                .add("a2").add("a3").build();

        System.out.println("streamFromBuilder = "
                + streamFromBuilder
                .collect(toList())); //streamFromBuilder = [a1, a2, a3]

        //Создание параллельного стрима: collection.parallelStream()
        Stream<String> parallelStream = collection.parallelStream();
        System.out.println("parallelStream = " + parallelStream.collect(toList())); //parallelStream = [a1, a2, a3]

        //Создание бесконечных стрима с помощью Stream.iterate:
        //Stream.iterate(начальное_условие, выражение_генерации)
        //Выражение n -> n + 1, это просто аналог выражения Integer func(Integer n) { return n+1;} обернутого в анонимный класс
        Stream<Integer> streamFromIterate = Stream.iterate(1, n -> n + 1);
        System.out.println("streamFromIterate = " + streamFromIterate
                .limit(3)
                .collect(toList())); //streamFromIterate = [1, 2, 3]

        //Создание бесконечных стрима с помощью Stream.generate
        //Stream.generate(выражение_генерации)
        //Выражение () -> «a1» аналог выражения String func() { return «a1»;} обернутого в анонимный класс
        Stream<String> streamFromGenerate = Stream.generate(() -> "a1");
        System.out.println("streamFromGenerate = " + streamFromGenerate
                .limit(3)
                .collect(toList())); //streamFromGenerate = [a1, a1, a1]

        //Создать пустой стрим
        Stream<String> streamEmpty = Stream.empty();
        System.out.println("streamEmpty = " + streamEmpty.collect(toList())); //streamEmpty = []


        // вывести на экран 10 случайных чисел, используя forEach()
        System.out.println("10 случайных чисел: ");
        (new Random())
                .ints()
                .limit(10)
                .forEach(System.out::println);

        System.out.println("==========");

        /*2. Примеры работы с некоторыми методами стримов.*/
        // filter - возвращает stream, в котором есть только элементы, соответствующие условию фильтра
        // count - возвращает количество элементов в стриме
        // collect - преобразует stream в коллекцию или другую структуру данных
        // mapToInt - преобразовать объект в числовой стрим (стрим, содержащий числа)
        // findFirst - возвращает первый Optional элемент из стрима
        // skip - пропускает N первых элементов (где N параметр метода)
        // collect преобразует stream в коллекцию или другую структуру данных
        // Метод limit позволяет ограничить выборку определенным количеством первых элементов
        //concat(Stream a, Stream b) - объединяет два стрима так, что вначале идут
        // элементы стрима A, а по его окончанию последуют элементы стрима B.

        //Sream.ofNullable - сделает из элементов поток, если элемент равен null,
        //то возвращает пустой поток.
        //Это метод позволяет фильтровать элементы.
        System.out.println("ofNullable");

        Stream.of(1, null, 2, null, 3, 4)
                .flatMap(Stream::ofNullable)
                .map(v -> String.format(" %s", v))
                .forEach(System.out::print); // 1 2 3 4
        System.out.println();

        System.out.println("==========");

        //Stream.takeWhile - это метод позволяет получать поток данных до тех пор, пока он проходит фильтр.
        //Новый метод takeWhile(Predicate p) говорит сам за себя, он будет брать элементы стрима,
        // до тех пор пока не встретится элемент, подходящий под предикат.
        System.out.println("takeWhile");
        List.of(2, 4, 3, 4, 5, 6).stream()
                .takeWhile(v -> v % 2 == 0)//"бери пока"
                .map(v -> String.format(" %s", v))//трансформируем для красивого вывода
                .forEach(System.out::print);
        System.out.println();
        //2 4
        System.out.println("==========");

        //Stream.dropWhile - это метод позволяет получать поток данных после того, как фильтр не проходит.
        //Этот метод в отличии от takeWhile() будет удалять элементы из стрима, до тех пор,
        // пока не найдется элемент, подходящий под предикат.
        System.out.println("dropWhile");

        List.of(2, 4, 3, 4, 5, 6).stream()
                .dropWhile(v -> v % 2 == 0)//"бросай пока"
                .map(v -> String.format(" %s", v))
                .forEach(System.out::print);
        // 3 4 5 6
        System.out.println();

        System.out.println("==========");

        //Stream.iterate(seed, predicate, next) - по сути это for цикл с индексом.
        System.out.println("iterate");

        Stream.iterate(0, i -> i < 10, i -> i + 1)
                .map(v -> String.format(" %s", v))
                .forEach(System.out::print);
        // 0 1 2 3 4 5 6 7 8 9
        System.out.println();
        System.out.println("==========");

        //concat(Stream a, Stream b)
        System.out.println("***метод concat()***");
        Stream.concat(
                        Stream.of(1, 2, 3),
                        Stream.of(4, 5, 6))
                .map(v -> String.format(" %s", v))
                .forEach(System.out::println);
        //1,2,3,4,5,6
        System.out.println("==========");

        //Исходная коллекция
        List<String> collectionSource = Arrays.asList("a1", "a2", "a3", "a1");

        //Вернуть количество вхождений объекта «a1»
        //Возвращает количество элементов стрима.
        System.out.println("***Пример с count() - Возвращает количество элементов стрима.***");
        long count = collectionSource.stream()
                .filter("a1"::equals)
                .count();
        System.out.println(count);
        //2
        System.out.println("==========");

        //Еще примеры с count()
        long numberOfPieces = IntStream.range(0, 10)
                .flatMap(x -> IntStream.range(0, x))
                .count();
        System.out.println(numberOfPieces);
        //45
        System.out.println("==========");

        System.out.println(IntStream.rangeClosed(-3, 6)
                .count());
        // 10
        System.out.println("==========");

        System.out.println(
                Stream.of(0, 2, 9, 13, 5, 11)
                        .map(v -> v * 2)//получаем все числа четные
                        .filter(v -> v % 2 == 1)//пропускаем только нечетные
                        .count()//в итоге ни одного элемента нет в стриме
        );
        //0
        System.out.println("==========");


        //Вернуть первый элемент коллекции или 0, если коллекция пуста.
        String s = collectionSource.stream()
                .findFirst()
                .orElse(String.valueOf(0));
        System.out.println(s); //a1

        //Вернуть последний элемент коллекции или «empty», если коллекция пуста
        String e = collectionSource.stream()
                .skip(collectionSource.size() - 1)
                .findAny()
                .orElse("empty");
        System.out.println(e); //a1

        //Найти элемент в коллекции равный «a3» или кинуть ошибку
        //Выражение «a3»::equals это аналог boolean func(s) { return «a3».equals(s);}
        String s1 = collectionSource.stream()
                .filter("a3"::equals)
                .findFirst()
                .get();
        System.out.println(s1); //a3

        //Вернуть третий элемент коллекции по порядку
        String s2 = collectionSource.stream()
                .skip(2)
                .findFirst()
                .get();
        System.out.println(s2); //a3

        //Вернуть два элемента начиная со второго
        Object[] objects = collectionSource.stream()
                .skip(1)
                .limit(2)
                .toArray();
        List<String> collect = collectionSource.stream()
                .skip(1)
                .limit(2)
                .collect(toList());
        System.out.println(Arrays.toString(objects)); //[a2, a3]
        System.out.println(collect); //[a2, a3]

        //Выбрать все элементы по шаблону
        //Выражение (s) -> s.contains(«1») это аналог boolean func(str) { return str.contains(«1»);}
        List<String> collect1 = collectionSource.stream()
                .filter(str ->
                        str.contains("1"))
                .collect(toList());
        System.out.println(collect1); //[a1, a1]

        /*3. Примеры использования distinct*/
        //Метод distinct возвращает stream без дубликатов, при этом для упорядоченного стрима
        // (например, коллекция на основе list) порядок стабилен, для неупорядоченного — порядок не гарантируется.
        Collection<String> ordered = Arrays.asList("a1", "a2", "a2", "a3", "a1", "a2", "a2");

        Collection<String> nonOrdered = new HashSet<>(ordered);

        //Получение коллекции без дубликатов
        List<String> distinct = nonOrdered.stream()
                .distinct()
                .collect(toList());
        //distinct = [a1, a2, a3]
        System.out.println("distinct = " + distinct); //порядок не гарантируется

        List<String> distinctOrdered = ordered.stream()
                .distinct()
                .collect(toList());
        //distinctOrdered = [a1, a2, a3]
        System.out.println("distinctOrdered = " + distinctOrdered); //порядок гарантируется

        /*4. Примеры использования Match функций (anyMatch, allMatch, noneMatch)
         * anyMatch - любое совпадение, allMatch - все совпадения, noneMatch - ни одного совпадения*/
        // Метод anyMatch - возвращает true, если условие выполняется хотя бы для одного элемента
        // Метод noneMatch - возвращает true, если условие не выполняется ни для одного элемента
        // Метод allMatch - возвращает true, если условие выполняется для всех элементов

        //найти существуют ли хоть одно совпадение с шаблоном в коллекции
        boolean isAnyOneTrue = collectionSource.stream()
                .anyMatch("a1"::equals);
        System.out.println("isAnyOneTrue = " + isAnyOneTrue); //isAnyOneTrue = true
        boolean isAnyOneFalse = collectionSource.stream()
                .anyMatch("a8"::equals);
        System.out.println("isAnyOneTrue = " + isAnyOneTrue); //isAnyOneTrue = true

        //найти существуют ли все совпадения с шаблоном в коллекции
        boolean isAll = collectionSource.stream()
                .allMatch(el -> el.contains("1"));
        System.out.println("isAll = " + isAll); //isAll = false

        //сравнение на неравенство
        boolean isNotEquals = collectionSource.stream()
                .noneMatch("a7"::equals);
        System.out.println("isNotEquals = " + isNotEquals); //isNotEquals = true
        System.out.println("==========");

        /*5. Примеры использования map функций (map, mapToInt, flatMap, flatMapToInt)*/
        // Метод Map изменяет выборку по определенному правилу, возвращает stream с новой выборкой.
        //map​(Function mapper)
        //Применяет функцию к каждому элементу и затем возвращает стрим,
        // в котором элементами будут результаты функции.
        // map можно применять для изменения типа элементов.
        System.out.println("Примеры с методом map:");
        Stream.of("3", "4", "5", "6")
                .map(Integer::parseInt)//преобразовали в числа 3,4,5,6
                .map(z -> z + 10)//добавили к каждому числу по 10
                .map(v -> String.format(" %s", v))
                .forEach(System.out::print); //13 14 15 16
        System.out.println();

        System.out.println("==========");

        Stream.of(120, 410, 85, 32, 314, 12)
                .map(v -> v + 11)
                .map(v -> String.format(" %s", v))
                .forEach(System.out::print); //131 421 96 43 325 23
        System.out.println();
        System.out.println("==========");

        System.out.println("Перобразование чисел из двоичной в 16-тиричную");
        Stream.of("10", "11", "12")
                .map(v -> Integer.parseInt(v, 16))//здесь имеем Stream<Integer> в 16-ти ричном отображении
                .map(v -> String.format(" %s", v))//преобразуем для красивого вывода
                .forEach(System.out::print); //16 17 18
        System.out.println();
        System.out.println("============");

        Map<Integer, List<String>> map3 = Stream.of("ab", "c", "def", "gh", "ijk", "mnop")
                .collect(Collectors.groupingBy(
                        String::length,
                        LinkedHashMap::new,
                        Collectors.mapping(
                                String::toUpperCase,
                                toList())
                ));
        map3.entrySet().forEach(System.out::println);
        //2=[AB, GH]
        //1=[C]
        //3=[DEF, IJK]
        //4=[MNOP]
        System.out.println("============");
        Map<Integer, String> map2 = Stream.of("ab", "c", "def", "gh", "ijk", "mnop")
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(
                                String::toUpperCase,
                                Collectors.joining("-")) //если не ставить delimiter то будет вывод слитный
                ));
        map2.entrySet().forEach(System.out::println);
        System.out.println("============");
        Map<Integer, List<String>> map1 = Stream.of("ab", "c", "def", "gh", "ijk", "mnop")
                .collect(Collectors.groupingBy(
                        String::length
                ));
        map1.entrySet().forEach(System.out::println);
        System.out.println("============");

        IntStream.range(1, 5)
                .flatMap(i -> IntStream.generate(() -> i)
                        .limit(i))
                .forEach(System.out::println);
        // 1, 2, 2, 3, 3, 3, 4, 4, 4, 4
        System.out.println("============");

        IntStream.range(-5, 0)
                .flatMap(i -> IntStream.of(i, -i))
                .sorted()
                .boxed() //необходим int=Integer, чтоб потом перевести в String
                .map(v -> String.format(" %s", v)) //красиво выведем потом с пробелами
                .forEach(System.out::println);
        // -5, -4, -3, -2, -1, 1, 2, 3, 4, 5
        System.out.println("============");

        IntStream.range(-5, 0)
                .flatMap(i -> IntStream.of(i, -i))
                .boxed()
                .sorted(Comparator.comparing(Math::abs))
                .map(v -> String.format(" %s", v)) //красиво выведем потом с пробелами
                .forEach(System.out::print);
        // -1, 1, -2, 2, -3, 3, -4, 4, -5, 5


        //Исходная коллекция
        Collection<String> collection1 = Arrays.asList("a1", "a2", "a3", "a1");

        //Добавить "_1" к каждому элементу первой коллекции
        List<String> transform = collection1.stream()
                .map(el1 -> el1 + "_1")
                .collect(toList());
        System.out.println("transform = " + transform); //transform = [a1_1, a2_1, a3_1, a1_1]

        //В первой коллекции убрать первый символ и вернуть массив чисел (int[])
        //или так с mapToInt:
        int[] numberInts = collection1.stream()
                .mapToInt(b -> Integer.parseInt(b.substring(1)))
                .toArray();
        //или так с map:
        List<Integer> number = collection1.stream()
                .map(b -> Integer.parseInt(b.substring(1)))
                .collect(toList());
        System.out.println("numberInts = " + Arrays.toString(numberInts)); //numberInts = [1, 2, 3, 1]
        System.out.println("number = " + number); //number = [1, 2, 3, 1]
        System.out.println("==========");
        //Из второй коллекции получить все числа, перечисленные через запятую из всех элементов
        Collection<String> collection2 = Arrays.asList("1,2,3,0", "4,5", "6_7_8");
        Collection<String> collection3 = Arrays.asList("1,2,3,0", "4,5", "6,7,8");

        // Метод FlatMap - похоже на Map - только вместо одного значения, он возвращает целый stream значений
        System.out.println("***метод flatMap***");
        //или так:
        String[] numberFlatMap = collection2.stream()
                .flatMap((p) -> Arrays.stream(p.split(",")))
                .toArray(String[]::new);
        System.out.println("numberFlatMap = " + Arrays.toString(numberFlatMap)); //numberFlatMap = [1, 2, 3, 0, 4, 5, 6_7_8]
        //или так:
        String[] numberFlatMap1 = collection2.stream()
                .flatMap((p) -> Arrays.asList(p.split(",")).stream())
                .toArray(String[]::new);
        System.out.println("numberFlatMap1 = " + Arrays.toString(numberFlatMap1)); //numberFlatMap1 = [1, 2, 3, 0, 4, 5, 6_7_8]
        System.out.println("==========");


        //Из третей коллекции получить сумму всех чисел, перечисленных через запятую
        //Метод flatMapToInt - похоже на mapToInt - только вместо одного значения,
        // он возвращает целый stream значений.
        //Работает как map, но с одним отличием — можно преобразовать один элемент в ноль,
        // один или множество других.
        //Как и в случае с map, служат для преобразования в примитивный стрим.
        int sum = collection3.stream()
                .flatMapToInt((p) -> Arrays.asList(p.split(",")).stream()
                        .mapToInt(Integer::parseInt))
                .sum();
        System.out.println("sum = " + sum); // sum = 36
        System.out.println("==========");

        Stream.of(2, 3, 0, 1, 3)
                .flatMapToInt(v -> IntStream.range(0, v))
                .mapToObj(v -> String.format(" %S", v))
                .forEach(System.out::print);
        // 0 1 0 1 2 0 0 1 2
        System.out.println();
        System.out.println("==========");

        System.out.println("Пример с flatMap и switch:");
        Stream.of(1, 2, 3, 4, 5, 6)
                .flatMap(v -> {
                    switch (v % 3) {
                        case 0:
                            return Stream.of(v, v * v, v * v * 2);
                        case 1:
                            return Stream.of(v);
                        default:
                            //return Stream.empty();
                            return Stream.of(0);
                    }
                })
                .map(v -> String.format(" %s", v))
                .forEach(System.out::print); //1 0 3 9 18 4 0 6 36 72
        // 1 0 3 9 18 4 0 6 36 72
        System.out.println();
        System.out.println("===========");

        //ofNullable(T t)
        //Появился в Java 9. Возвращает пустой стрим, если в качестве аргумента
        // передан null, в противном случае, возвращает стрим из одного элемента.
        System.out.println("***метод ofNullable***");
        String strRand = Math.random() > 0.5 ? "I'm feeling lucky" : null;
        Stream.ofNullable(strRand)
                .forEach(System.out::println); //будет или пусто или "I'm feeling lucky"
        System.out.println("===========");

        //Stream.iterate(seed, predicate, next) - по сути это for цикл с индексом.
        System.out.println("***метод iterate()***");
        Stream.iterate(0, i -> i < 10, i -> i + 1)
                .map(v -> String.format(" %s", v))
                .forEach(System.out::print); //0 1 2 3 4 5 6 7 8 9
        System.out.println();
        System.out.println("===========");

        System.out.println("***метод iterate(), как for()***");
        Stream.iterate(2, x -> x < 25, x -> x + 6)
                .forEach(System.out::println);
        //2, 8, 14, 20
        System.out.println("===========");



        /*6. Примеры использования Sorted функции*/
        //// Метод Sorted позволяет сортировать значения либо в натуральном порядке, либо задавая Comparator
        System.out.println("***метод sorted()***");
        //исходная коллекция
        Collection<String> collection4 = Arrays.asList("a1", "a4", "a5", "a3", "a2", "a1", "a2");

        //Отсортировать коллекцию строк по алфавиту
        List<String> sorted = collection4.stream()
                .sorted()
                .collect(toList());
        System.out.println("sorted = " + sorted); //sorted = [a1, a1, a2, a2, a3, a4, a5]
        //Отсортировать коллекцию строк по алфавиту в обратном порядке
        List<String> sortedReverse = collection4.stream()
                .sorted((o1, o2) -> o2.compareTo(o1))
                .collect(toList());
        System.out.println("sortedReverse = " + sortedReverse); //sortedReverse = [a5, a4, a3, a2, a2, a1, a1]

        //Отсортировать коллекцию строк по алфавиту и убрать дубликаты
        List<String> sortedDistinct = collection4.stream()
                .sorted()
                .distinct()
                .collect(toList());
        System.out.println("sortedDistinct = " + sortedDistinct); //sortedDistinct = [a1, a2, a3, a4, a5]
        System.out.println("===========");

        /*7. Примеры использования Max и Min функций*/
        // Метод max вернет максимальный элемент, в качестве условия использует компаратор
        // Метод min вернет минимальный элемент, в качестве условия использует компаратор
        //Найти максимальное значение среди коллекции строк
        //Comparator для строк String::compareTo
        String max = collection4.stream().max(String::compareTo).get();
        System.out.println("max = " + max); //max = a5

        //Найти минимальное значение среди коллекции строк
        String min = collection4.stream().min(String::compareTo).get();
        System.out.println("min = " + min); //min = a1
        //Пример с объектами:
        //Найдем человека с максимальным возрастом	peoples.stream().max((p1, p2) -> p1.getAge().compareTo(p2.getAge())).get()

        //Пример с объектами:
        //Найдем человека с минимальным возрастом	peoples.stream().min((p1, p2) -> p1.getAge().compareTo(p2.getAge())).get()

        /*8. Примеры использования ForEach и Peek функций*/
        //Обе ForEach и Peek по сути делают одно и тоже, меняют свойства объектов в стриме,
        //единственная разница между ними в том что ForEach терминальная и она заканчивает работу со стримом, в то время как
        //Peek конвейерная и работа со стримом продолжается.

        // Метод ForEach применяет указанный метод к каждому элементу стрима и заканчивает работу со стримом
        //Не оборачивается в переменную, т.к. в аргументе Consumer имеет void type.
        Collection<String> collection5 = Arrays.asList("aa1", "aa2", "aa3", "aa4", "aa1");
        // Напечатать отладочную информацию по каждому элементу стрима
        System.out.print("forEach = ");
        collection5.stream()
                .map(String::toUpperCase)
                .forEach(p -> System.out.print(p + ",")); //forEach = AA1,AA2,AA3,AA4,AA1,
        System.out.println();

        Collection<StringBuilder> stringBuilders = Arrays.asList(
                new StringBuilder("a1"),
                new StringBuilder("a2"),
                new StringBuilder("a3"));
        Collection<StringBuilder> stringBuilders2 = Arrays.asList(
                new StringBuilder("a1"),
                new StringBuilder("a2"),
                new StringBuilder("a3"));

        stringBuilders.stream().forEachOrdered(p -> p.append("_new"));
        System.out.println("forEachOrdered = " + stringBuilders); //forEachOrdered = [a1_new, a2_new, a3_new]

        // Метод Peek возвращает тот же стрим, но при этом применяет указанный метод к каждому элементу стрима
        System.out.print("peak1 = ");
        List<String> peek = collection5.stream()
                .map(String::toUpperCase)
                .peek(p -> System.out.print(p + ","))  //peak1 = AA1,AA2,AA3,AA4,AA1,
                .collect(toList());
        System.out.println();

        //Используем stringBuilders2, т.к. stringBuilders уже изменен, и еще раз изменится.
        List<StringBuilder> newListPeek = stringBuilders2.stream()
                .peek(p -> p.append("_new"))
                .collect(toList());
        System.out.println("newListPeek = " + newListPeek); //newListPeek = [a1_new, a2_new, a3_new]

        /*9. Примеры использования Reduce функции*/
        /*Метод reduce позволяет выполнять агрегатные функции над всей коллекцией
        (такие как сумма, нахождение минимального или максимального значение и т.п.),
        он возвращает одно значение для стрима, функция получает два аргумента —
        значение полученное на прошлых шагах и текущее значение.*/
        // Он возвращает одно Optional значение

        //Исходная коллекция чисел
        List<Integer> intCollection = Arrays.asList(1, 2, 3, 4, 2, 3);

        //Получить сумму чисел или вернуть 0
        // или так:
        // intCollection.stream().reduce(Integer::sum).orElse(0);
        //или так:
        Integer summ = intCollection.stream()
                .reduce((o1, o2) -> o1 + o2)
                .orElse(0);
        System.out.println("summ = " + summ); //summ = 15

        //Вернуть максимум или -1
        //или так:
        //Integer max1 = intCollection.stream().reduce(Integer::max).orElse(-1);
        //или так:
        Integer max2 = intCollection.stream()
                .reduce((p1, p2) -> p1 > p2 ? p1 : p2)
                .orElse(-1);
        System.out.println("max2 = " + max2); //max2 = 4

        //Вернуть минимум или 0
        Integer min2 = intCollection.stream()
                .reduce((p1, p2) -> p1 < p2 ? p1 : p2)
                .orElse(-1);
        System.out.println("min2 = " + min2); //min2 = 1

        //Вернуть сумму нечетных чисел или 0
        Integer sumOdd = intCollection.stream()
                .filter(o -> o % 2 != 0)
                .reduce((p1, p2) -> p1 + p2)
                .orElse(0);
        System.out.println("sumOdd = " + sumOdd); //sumOdd = 7

        //Вернуть последний элемент или 0
        Integer lastItem = intCollection.stream()
                .reduce((p1, p2) -> p2)
                .orElse(0);
        System.out.println("lastItem = " + lastItem); //lastItem = 3

        //Старым методом, без stream API:
        /*
        Integer lastOld = null;
        for(Integer i: collection) {
            lastOld = i;
        }
        lastOld = lastOld == null? 0 : lastOld;
        System.out.println("last = " + last + " : " + lastOld); // напечатает last = 2 : 2
        */

        /* Работа со сложными объектами:
        // Найдем самого старшего мужчину
        int oldMan = peoples.stream().filter((p) -> p.getSex() == Sex.MAN).map(People::getAge).reduce((s1, s2) -> s1 > s2 ? s1 : s2).get();
        System.out.println("oldMan = " + oldMan); // напечатает 69

        // Найдем самого минимальный возраст человека у которого есть бука е в имени
        int younger = peoples.stream()
                .filter((p) -> p.getName().contains("е"))
                .mapToInt(People::getAge)
                .reduce((s1, s2) -> s1 < s2 ? s1 : s2)
                .orElse(0);
        System.out.println("younger = " + younger); // напечатает 23
        */

        /*10. Примеры использования toArray и collect функции*/
        /*Если с toArray все просто, можно либо вызвать toArray() получить Object[],
        либо toArray(T[]::new) — получив массив типа T,
        то collect позволяет много возможностей преобразовать значение в
        коллекцию, map'у или любой другой тип.
        Для этого используются статические методы из Collectors, например
        преобразование в List будет stream.collect(Collectors.toList()).*/
        // Полезные статические методы из Collectors:
        // toList, toCollection, toSet - представляют стрим в виде списка, коллекции или множества
        // toConcurrentMap, toMap - позволяют преобразовать стрим в map, используя указанные функции
        // averagingInt, averagingDouble, averagingLong - возвращают среднее значение
        // summingInt, summingDouble, summingLong - возвращает сумму
        // summarizingInt, summarizingDouble, summarizingLong - возвращают SummaryStatistics с разными агрегатными значениями
        // partitioningBy - разделяет коллекцию на две части по соответствию условию и возвращает их как Map<Boolean, List>
        // groupingBy - разделить коллекцию по условию и вернуть Map<N, List<T>>, где T - тип последнего стрима, N - значение разделителя
        // mapping - дополнительные преобразования значений для сложных Collector'ов

        //Исходный лист с числами
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        //Получить сумму нечетных чисел
        //можно так:
        Integer collectSumOdd = numbers.stream()
                .collect(Collectors.summingInt(p -> p % 2 == 1 ? p : 0));
        //можно так:
        //Integer collectSumOdd = numbers.stream().mapToInt(p -> p % 2 == 1 ? p : 0).sum();
        System.out.println("collectSumOdd = " + collectSumOdd); //collectSumOdd = 9

        //Вычесть от каждого элемента 1 и получить среднее
        Double collectAverag = numbers.stream()
                .collect(Collectors.averagingInt(p -> p - 1));
        System.out.println("collectAverag = " + collectAverag); //collectAverag = 2.5

        //Прибавить к числам 3 и получить статистику summarizingInt
        IntSummaryStatistics collectStatistic = numbers.stream()
                .collect(Collectors.summarizingInt(p -> p + 3));
        System.out.println("collectStatistic = " + collectStatistic);
        //результат: collectStatistic = IntSummaryStatistics{count=6, sum=39, min=4, average=6,500000, max=9}

        // Получить сумму четных чисел через IntSummaryStatistics
        long sumEven = numbers.stream()
                .collect(Collectors.summarizingInt(p -> p % 2 == 0 ? p : 0))
                .getSum();
        System.out.println("sumEven = " + sumEven); //sumEven = 12

        //Разделить числа на четные и нечетные
        Map<Boolean, List<Integer>> collectPartition = numbers.stream()
                .collect(Collectors.partitioningBy(p -> p % 2 == 0));
        System.out.println("collectPartition = " + collectPartition); //collectPartition = {false=[1, 3, 5], true=[2, 4, 6]}

        //Другой исходный массив
        Collection<String> strings = Arrays.asList("a1", "b2", "c3", "a4", "a1", "b2");

        //Получить массив строк без дубликатов и в верхнем регистре из коллекции строк
        String[] toArray = strings.stream().distinct()
                .map(String::toUpperCase)
                .toArray(String[]::new);
        System.out.println("toArray = " + Arrays.toString(toArray)); //toArray = [A1, B2, C3, A4]

        // Объединить все элементы в одну строку через разделитель : и обернуть тегами <b> ... </b>
        String collectJoin = strings.stream()
                .collect(Collectors.joining(" : ", "<b>", "<b>"));
        System.out.println("collectJoin = " + collectJoin); //collectJoin = <b>a1 : b2 : c3 : a4 : a1 : b2<b>

//        // Преобразовать в map, где первый символ ключ, второй символ значение
//        Map<String, String> map = strings.stream().distinct()
//                .collect(Collectors
//                        .toMap(p -> p.substring(0, 1),
//                                p -> p.substring(1, 2)));
//        System.out.println("map = " + map);

        // Преобразовать в map, сгруппировав по первому символу строки
        Map<String, List<String>> groups = strings.stream()
                .collect(Collectors.groupingBy(p -> p.substring(0, 1)));
        System.out.println("groups = " + groups); //groups = {a=[a1, a4, a1], b=[b2, b2], c=[c3]}

        // Преобразовать в map, сгруппировав по первому символу строки и в качестве значения
        // взять второй символ объединим через :
        Map<String, String> groupJoin = strings.stream()
                .collect(Collectors.groupingBy(p -> p.substring(0, 1),
                        Collectors.mapping(p -> p.substring(1, 2),
                                Collectors.joining(":"))));
        System.out.println("groupJoin = " + groupJoin); //groupJoin = {a=1:4:1, b=2:2, c=3}

        //toMap​(Function keyMapper, Function valueMapper)
        //Собирает элементы в Map. Каждый элемент преобразовывается в ключ и в
        //значение, основываясь на результате функций keyMapper и valueMapper
        //соответственно. Если нужно вернуть тот же элемент, что и пришел, то
        // можно передать Function.identity().
        Map<Integer, Integer> map11 = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.toMap(Function.identity(), Function.identity()));
        System.out.println(map11); //{1=1, 2=2, 3=3, 4=4, 5=5}

        Map<Integer, String> map22 = Stream.of(1, 2, 3)
                .collect(Collectors.toMap(
                        Function.identity(),
                        i -> String.format("%d * 2 = %d", i, i * 2)
                ));
        System.out.println(map22); //{1=1 * 2 = 2, 2=2 * 2 = 4, 3=3 * 2 = 6}

        Map<Character, String> map33 = Stream.of(50, 54, 55)
                .collect(Collectors.toMap(
                        i -> (char) i.intValue(),
                        i -> String.format("<%d>", i)
                ));
        System.out.println(map33); //{2=<50>, 6=<54>, 7=<55>}

        //toMap​(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction)
        //Аналогичен первой версии метода, только в случае, когда встречается два
        //одинаковых ключа, позволяет объединить значения.
        //В данном случае, для чисел 50, 55 и 20, ключ одинаков и равен 0, поэтому значения накапливаются.
        //Для 64 и 19 аналогично.

        Map<Integer, String> map4 = Stream.of(50, 55, 69, 20, 19, 52)
                .collect(Collectors.toMap(
                        i -> i % 5,
                        i -> String.format("<%d>", i),
                        (a, b) -> String.join(", ", a, b)
                ));
        System.out.println(map4); //{0=<50>, <55>, <20>, 2=<52>, 4=<69>, <19>}

        /*11. Примеры*/
        //Дан массив аргументов. Нужно получить Map, где каждому ключу будет соответствовать своё значение.
        String[] arguments = {"-i", "in.info about lambda.txt", "--limit", "40", "-d", "1", "-o", "out.info about lambda.txt"};
        Map<String, String> argsMap = new LinkedHashMap<>(arguments.length / 2);
        for (int i = 0; i < arguments.length; i += 2) {
            argsMap.put(arguments[i], arguments[i + 1]);
        }
        argsMap.forEach((key, value) -> System.out.format("%s: %s%n", key, value));
        //-i: in.info about lambda.txt
        //--limit: 40
        //-d: 1
        //-o: out.info about lambda.txt

        //Обратная задача — сконвертировать Map с аргументами в массив строк, стримы помогут.
        String[] argums = argsMap.entrySet().stream()
                .flatMap(a -> Stream.of(a.getKey(), a.getValue()))
                .toArray(String[]::new);
        System.out.println(String.join(" ", argums));
        //-i in.info about lambda.txt --limit 40 -d 1 -o out.info about lambda.txt

        /*Задачи:*/
        System.out.println("*****Разные задачи:*****");
        IntStream.range(5, 30)
                .limit(12)
                .skip(3)
                .limit(6)
                .skip(2)
                .forEach(System.out::println);
        //10,11,12,13
        System.out.println("==========");

        IntStream.range(0, 10)
                .skip(2)
                .dropWhile(x -> x < 5)
                .limit(3)
                .forEach(System.out::println);
        //5,6,7
        System.out.println("==========");

        IntStream.range(0, 10)
                .skip(3)
                .takeWhile(x -> x < 5)
                .limit(3)
                .forEach(System.out::println);
        //3,4
        System.out.println("==========");

        IntStream.range(1, 5)
                .flatMap(i -> IntStream.generate(() -> i)
                        .limit(i))
                .forEach(System.out::println);
        //1,2,2,3,3,3,4,4,4,4
        System.out.println("==========");

        int reduceSum = IntStream.range(-2, 2)
                .map(i -> i * 5)
                .reduce(10, Integer::sum);
        System.out.println("reduceSum = " + reduceSum);
        // reduceSum = 0
        System.out.println("==========");

        IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0))
                .entrySet().forEach(System.out::println);
        //false=[1, 3, 5, 7, 9]
        //true=[0, 2, 4, 6, 8]
        System.out.println("==========");

        IntStream.range(-5, 0)
                .flatMap(i -> IntStream.of(i, i + 6))
                .sorted()
                .forEach(System.out::println);
        //-5 -4 -3 -2 -1 1 2 3 4 5
        System.out.println("==========");

        IntStream.range(-5, 0)
                .flatMap(i -> IntStream.of(i, i + 6))
                .boxed()
                .sorted(Comparator.comparing(Math::abs))
                .map(v -> String.format(" %s", v)) //для последующего красивого вывода
                .forEach(System.out::print);
        //1 -1 2 -2 3 -3 4 -4 5 -5
        System.out.println("==========");

        IntStream.range(1, 5)
                .flatMap(i -> IntStream.generate(() -> i).limit(i))
                .boxed()
                //.peek(System.out::println)//1223334444
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()))
                .entrySet().forEach(System.out::println);
        //1=1
        //2=2
        //3=3
        //4=4

        //извлеките уникальные слова, отсортированные ASC из списка фраз:

        System.out.println("===Извлечение уникальных слов, отсортированные ASC из списка фраз===");

        List<String> phrases = Arrays.asList(
                "sporadic perjury daddy",
                "confounded skimming mammy",
                "incumbent jailer daddy",
                "confounded jailer mammy");

        List<String> uniqueWords = phrases
                .stream()
                .flatMap(phrase -> Stream.of(phrase.split(" +"))) //Stream.of(phrase.split(" +") - означает, создать стрим из
                //phrase, взять каждое слово разделенное пробелом или несколькими пробелами
                //" +" - указывает что есть разделитель пробел или пробелы
                .distinct()
                .sorted()
                .collect(toList());
        System.out.println("Unique words: " + uniqueWords);
        //Unique words: [confounded, daddy, incumbent, jailer, mammy, perjury, skimming, sporadic]

        System.out.println("==========");

        /*Очень простой пример: разделить список полных имен, чтобы получить список имен, независимо от того, первый или последний*/
        System.out.println("===Разделить список полных имен, чтобы получить список имен==");

        List<String> fullNames = Arrays.asList("Barry Allen", "Bruce Wayne", "Clark Kent", "Peter Jackson");

        fullNames.stream()
                .flatMap(fullName -> Pattern.compile(" ").splitAsStream(fullName))
                .forEach(System.out::println);
        //Barry
        //Allen
        //Bruce
        //Wayne
        //Clark
        //Kent
        //Peter
        //Jackson

        System.out.println("===Разделить список полных имен, чтобы получить список имен, затем отсортировать по ASC==");

        List<String> fullNames2 = Arrays.asList("Barry Allen", "Bruce Wayne", "Clark Kent", "Peter Jackson");

        fullNames2.stream()
                .flatMap(fullName -> Pattern.compile(" +").splitAsStream(fullName))
                .map(v -> String.format(" %s", v)) //для красивого вывода в строку с пробелами
                .sorted()
                .forEach(System.out::print);
        //Allen Barry Bruce Clark Jackson Kent Peter Wayne

        System.out.println();
        System.out.println("==========");

        //Таблица умножения
        System.out.println("========= Таблица Умножения ==========");

        IntFunction<IntFunction<String>> function = i -> j -> String.format("%d x %2d = %2d", i, j, i * j);
        IntFunction<IntFunction<IntFunction<String>>> repeaterX = coUnt -> i -> j ->
                IntStream.range(0, coUnt)
                        .mapToObj(delta -> function.apply(i + delta).apply(j))
                        .collect(Collectors.joining("\t\t"));
        IntFunction<IntFunction<IntFunction<IntFunction<String>>>> repeaterY = countY ->
                countX -> i -> j ->
                        IntStream.range(0, countY)
                                .mapToObj(deltaY -> repeaterX.apply(countX).apply(i).apply(j + deltaY))
                                .collect(Collectors.joining("\n"));
        IntFunction<String> row = i -> repeaterY.apply(10).apply(4).apply(i).apply(1) + "\n";
        IntStream.of(2, 6).mapToObj(row).forEach(System.out::println);

        //Необходимо установить для каждого телефона цену со скидкой и цену без скидки.
        //То есть из одного объекта Phone нам надо получить два объекта с информацией,
        // например, в виде строки.
        System.out.println("========= Пример с методом flatMap() ==========");
        Stream<Phone> phoneStream = Stream.of(
                new Phone("iPhone 6 S", 54000),
                new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000),
                new Phone("Huawei P20", 25000));
//        System.out.println("===== Список всех телефонов =====");
//        phoneStream.forEach(System.out::println);

        System.out.println("===== Список телефонов со скидкой =====");
        System.out.println("===== Пример с flatMap() =====");

        phoneStream
                .flatMap(p -> Stream.of(
                        String.format("название: %s цена без скидки: %d", p.getName(), p.getPrice()),
                        String.format("название: %s цена со скидкой: %d", p.getName(),
                                p.getPrice() - (int) (p.getPrice() * 0.1))))
                .forEach(System.out::println);

        System.out.println("======= Пример с методом collect с его второй формой ==========");
        System.out.println("<R> R collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)");

        //•	supplier: создает объект коллекции
        //•	accumulator: добавляет элемент в коллекцию
        //•	combiner: бинарная функция, которая объединяет два объекта

        Stream<String> phones = Stream.of("iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

        ArrayList<String> filteredPhones = phones.filter(v -> v.length() < 12)
                .collect(
                        ArrayList::new, //создаем ArrayList method reference
                        //() -> new ArrayList<>(), //создаем ArrayList
                        ArrayList::add, // добавляем в список элемент method reference
                        //(list, item) -> list.add(item), // добавляем в список элемент
                        ArrayList::addAll); //добавляем в список другой список method reference
        //(list1, list2) -> list1.addAll(list2));//добавляем в список другой список
        filteredPhones.forEach(System.out::println);

        System.out.println("======= Пример с методом reduce ==========");
        //•	Optional reduce(BinaryOperator accumulator)
        //•	T reduce(T identity, BinaryOperator accumulator)
        //•	U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator combiner)
        System.out.println("1. вариант - Optional reduce(BinaryOperator accumulator)");
        Stream<Integer> integerNumbersStream = Stream.of(1, 2, 3, 4, 5, 6);
        Optional<Integer> resultInOptional = integerNumbersStream.reduce((x, y) -> x * y);
        System.out.println(resultInOptional.get());

        System.out.println("2. вариант - T reduce(T identity, BinaryOperator accumulator)");
        Stream<String> wordsStream = Stream.of("мама", "мыла", "раму");
        String sentence = wordsStream.reduce("Результат: ", (x, y) -> x + " " + y);
        System.out.println(sentence);

        System.out.println("3. вариант - U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator combiner)");
        //Нужно найти сумму цен тех телефонов, у которых цена меньше определенного значения.
        //Например, меньше 50000. Для этого используем третью версию метода reduce.
        Stream<Phone> phoneStream1 = Stream.of(new Phone("iPhone 6 S", 54000),
                new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000),
                new Phone("LG G 4", 32000));
        int sumPricePredicate = phoneStream1.reduce(
                0,
                (x, y) -> {
                    if (y.getPrice() < 50000) {
                        return x + y.getPrice();
                    } else {
                        return x + 0;
                    }
                },
                (x, y) -> x + y); //method reference (Integer::sum)

        System.out.println("sumPricePredicate " + sumPricePredicate);

        System.out.println("==========");
        System.out.println("=== Пример с функцией высшего порядка ===");
        //Функции высшего порядка принимают другие функции в качестве своих параметров
        //или возвращают другие функции в качестве своих результатов.
        Function<Integer, Integer> f = v -> v + 3;
        BiFunction<Function<Integer, Integer>, Integer, Integer> g =
                (func, v) -> func.apply(v) * func.apply(v);
        System.out.println(g.apply(f, 7)); //100 => (v+3)*(v+3) => (7+3)*(7+3)=100

        System.out.println("==========");

        //выбрать два четных числа, возвести в квадрат и вывести в список
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> twoEvenSquares =
                numbers.stream()
                        .filter(n -> {
                            System.out.println("filtering " + n);
                            return n % 2 == 0;
                        })
                        .map(n -> {
                            System.out.println("mapping " + n);
                            return n * n;
                        })
                        .limit(2)
                        .collect(toList());


    }
}
