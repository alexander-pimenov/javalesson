package ru.pimalex1978.streamepam;

import ru.pimalex1978.streamepam.beans.Department;
import ru.pimalex1978.streamepam.beans.Employee;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

public class FunctionStreamMain {
    public static void main(String[] args) {

        System.out.println("===функциональные интерфейсы===");

        /*делаем проверку на четность в отдельном методе isEvent*/
        Predicate<Integer> isPrime = number -> isEvent(number);

        /*потребитель*/
        Consumer<Integer> messageVerification = message -> System.out.println(message);

        /*и потребляем и производим*/
        Function<String, Department> strangeConstructor = name -> new Department(name);

        //функция для конструирования, например инкремент
        Function<Integer, Integer> addTwo = a -> a + 2;

        /*как мы применим функцию*/
        System.out.println(addTwo.apply(2));

        /*производитель*/
        //возвращаем какой то случайный рандом
        Supplier<Boolean> random = () -> ThreadLocalRandom.current().nextBoolean();

        //возьмем какую то последовательность
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .stream()
                .map(addTwo) //map-функция высшего порядка может принимать ссылки на другие функции
                .filter(isPrime) //здесь тоже внутрь передадим ссылку на функцию
                .forEach(messageVerification);

        /*создадим с помощью функций обращение к конструктору*/
        Function<String, Department> constructor = name -> new Department(name); //1 вариант
        Function<String, Department> constructor2 = Department::new; //2 вариант

        Arrays.asList("Backend", "Frontend").stream().map(constructor2)
                .forEach(System.out::println);

        /*работа с BiFunction*/
        BiFunction<String, CharSequence, Boolean> contains = String::contains;

        //проверим что какая то строка содержит в себе другую подстроку
        System.out.println(contains.apply("Russia big country", "Alaska"));

        /*примеры с UnaryOperators*/
        //функция для получения модуля числа
        final DoubleUnaryOperator doubleUnary = Math::abs;

        //контролируем примитивы double через applyAsDouble
        System.out.println(doubleUnary.applyAsDouble(-3.33));

        /*пример противоположной функции */
        //берем департамент и извлекаем имя
        Function<Department, String> getName = Department::getName;
        System.out.println(getName.apply(constructor2.apply("Mobile")));
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
