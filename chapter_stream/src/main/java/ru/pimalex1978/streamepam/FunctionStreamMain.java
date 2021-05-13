package ru.pimalex1978.streamepam;

import ru.pimalex1978.streamepam.beans.Department;
import ru.pimalex1978.streamepam.beans.Employee;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class FunctionStreamMain {
    public static void main(String[] args) {

        //Step 1: Создание функций из стандартных функциональных интерфейсов
        //и их использование
        System.out.println("===функциональные интерфейсы===");

        /*делаем проверку на четность в отдельном методе isEvent*/
        Predicate<Integer> isPrime = number -> isEvent(number);
        //применим функцию
        boolean test = isPrime.test(11);
        System.out.println(test);

        /*потребитель*/
        Consumer<Integer> messageVerification = message -> System.out.println(message);
        //применим функцию
        messageVerification.accept(10); //возвращаемый тип void

        /*и потребляем и производим*/
        Function<String, Department> strangeConstructor = name -> new Department(name);
        //применим функцию
        Department ddd = strangeConstructor.apply("DDD");
        System.out.println("Department - " + ddd);

        //функция для конструирования, например инкремент
        Function<Integer, Integer> addTwo = a -> a + 2;
        /*как мы применим функцию*/
        System.out.println(addTwo.apply(2));

        /*производитель*/
        //возвращаем какой то случайный рандом булевого типа
        Supplier<Boolean> random = () -> ThreadLocalRandom.current().nextBoolean();

        //возьмем какую то последовательность чисел и применим функции заданные выше в коде
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .stream() //создаем стрим
                .map(addTwo) //map-функция высшего порядка может принимать ссылки на другие функции
                .filter(isPrime) //здесь тоже внутрь передадим ссылку на функцию
                .forEach(messageVerification);

        /*создадим с помощью функций обращение к конструктору*/
        Function<String, Department> constructor = name -> new Department(name); //1 вариант
        Function<String, Department> constructor2 = Department::new; //2 вариант

        //покажем использование функции constructor
        Arrays.asList("Backend", "Frontend").stream().map(constructor2)
                .forEach(System.out::println);

        /*работа с BiFunction
         * Сами придумываем, что дадим стинговую строку, и какую то последовательность для
         * проверки и выдадим булевый результат*/
        BiFunction<String, CharSequence, Boolean> contains = (st, ch) -> {
            if (st.contains(ch)) {
                return true;
            } else {
                return false;
            }
        };
//        BiFunction<String, CharSequence, Boolean> contains = String::contains;

        //проверим что какая то строка содержит в себе другую подстроку
        System.out.println(contains.apply("Russia big country", "Alaska"));

        /*Работа с BiFunction которая переходит в TriFunction*/
        //https://stackoverflow.com/questions/18400210/java-8-where-is-trifunction-and-kin-in-java-util-function-or-what-is-the-alt
        //вот как можно эмулировать TriFunction без дополнительного интерфейса:
        Function<Integer, Function<Integer, UnaryOperator<Integer>>> tri1 = a -> b -> c -> a + b + c;
        System.out.println(tri1.apply(1).apply(2).apply(3)); //(1+2+3) prints 6

        //можно комбинировать функции и другими способами, например:
        /*Хотя каррирование было бы естественным для любого языка, который
        поддерживает функциональное программирование за пределами
        лямбда-выражений, Java не построена таким образом, и, хотя это
        достижимо, код трудно поддерживать, а иногда и читать. Однако
        это очень полезно в качестве упражнения, и иногда частичные
        функции занимают достойное место в вашем коде.*/
        BiFunction<Integer, Integer, UnaryOperator<Integer>> tri2 = (a, b) -> c -> a + b + c;
        System.out.println(tri2.apply(1, 2).apply(3)); //prints 6
        //partial function can be, of course, extracted this way
        UnaryOperator partial = tri2.apply(1, 2); //this is partial, eq to c -> 1 + 2 + c;
        System.out.println(partial.apply(4)); //prints 7
        System.out.println(partial.apply(5)); //prints 8

        /*примеры с UnaryOperators*/
        //функция для получения модуля числа
        final DoubleUnaryOperator doubleUnary = Math::abs;

        //контролируем примитивы double через applyAsDouble
        System.out.println(doubleUnary.applyAsDouble(-3.33));

        /*пример противоположной функции */
        //берем департамент и извлекаем имя
        Function<Department, String> getName = Department::getName;
        //во вложенной функции мы создаем департамент с именем Mobile, а
        //во внешней функции мы извлекаем его название.
        System.out.println(getName.apply(constructor2.apply("Mobile")));


        // Step 2: Specialized FlatMap: sum of distinct values only
        String[][] arrayOfArrays = {{"1", "2"}, {"3", "4"}, {"1", "4", "1", "4"}};

        //в операторе flatMapToLong мы преобразуем стрим массивов в стрим отдельных
        //чисел массивов и в mapToLong каждое стрим-число преобразуется в число типа Long
        //потом удаляем все дубликаты distinct
        LongStream longStream = Arrays.stream(arrayOfArrays)
                .flatMapToLong(innerArray -> Arrays.stream(innerArray)
                        .peek(e -> System.out.println("промежуточный вывод - " + e))
                        .mapToLong(Long::new)).distinct();

/*        // Step 3: Possible solution
        longStream = Arrays.stream(arrayOfArrays)
                .flatMapToLong(innerArray -> Arrays.stream(innerArray)
                        .mapToLong(Long::new))
                .distinct();*/

        // Step 4: peek for debug purposes
        System.out.println(longStream.peek(System.out::println).sum());

        // Step 5: Print out all Strings by chars
        Stream.of("Scala", "Java", "Kotlin")
                .flatMap(name -> IntStream.range(0, name.length())
//                        .peek(System.out::println)
                        .mapToObj(name::charAt))

                .forEach(System.out::print);

    }

    /**
     * Метод делающий проверку на четность
     *
     * @param number число
     * @return результат как булево значение
     */
    private static boolean isEvent(Integer number) {
        if (number % 2 == 0) {
            return true;
        }
        return false;
    }

    /**
     * Метод создающий список сотрудников.
     *
     * @return список сотрудников.
     */
    private static List<Employee> hireEmployees() {
        Department financialDepartment = new Department("Finance department");
        Department backendDepartment = new Department("Backend department");

        return Arrays.asList(new Employee(19, "Vasja", "Java", 1, financialDepartment),
                new Employee(20, "Petja", ".NET", 3, financialDepartment),
                new Employee(38, "Johann", "Delfi", 5, backendDepartment),
                new Employee(45, "Sergey", "Java", 2, backendDepartment));
    }
}
