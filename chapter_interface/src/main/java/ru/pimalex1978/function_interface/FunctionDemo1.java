package ru.pimalex1978.function_interface;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Function  - это встроенный функциональный интерфейс, добавленный в Java
 * SE 8 в пакет java.util.function.
 * <p>
 * Function — этот функциональный интерфейс принимает аргумент T и приводит
 * его к объекту типа R, который и возвращается как результат.
 * <p>
 * Принимает значение в качестве аргумента одного типа и возвращает
 * другое значение. Часто используется для преобразования одного
 * значения в другое.
 * <p>
 * Function<T,R>, который представляет собой функцию, которая принимает
 * один аргумент типа T и возвращает значение типа R. Вот и все.
 * Что делает эта функция?
 * Ну, он может все ... пока он принимает один аргумент и возвращает
 * одно значение. Вот почему спецификация для Function<T,R> немного
 * больше ,чем " представляет функцию, которая принимает один аргумент
 * и дает результат."
 * Это можно назвать "трансформацией".
 * Функциональный дескриптор интерфейса:
 * T -> R
 */
public class FunctionDemo1 {
    public static void main(String[] args) {

        //Использование интерфейса Function. Он обрабатывает Double и на
        //выход отдает Long
        Function<Double, Long> function = d -> Math.round(d);
        System.out.println(function.apply(5.7)); //6

        //Пример использования дефолтных методов
        //compose - составить
        Function<String, String> f1 = s -> s + "1";
        Function<String, String> f2 = s -> s + "2";
        Function<String, String> f3 = s -> s + "3";
        Function<String, String> f4 = s -> s + "4";
        System.out.println(f1.andThen(f2).compose(f3).compose(f4).apply("Compose")); //Compose4312
        System.out.println(f1.andThen(f2).andThen(f3).compose(f4).apply("Compose")); //Compose4123
        System.out.println(f1.andThen(f2).andThen(f3).apply("AndThen")); //AndThen123

        //Статический метод интерфейса Function: identity()
        //который всегда возвращает входной параметр.
        Function<String, String> f = Function.identity();
        System.out.println(f.apply("Some Value")); //Some Value

        //Еще пример: возьмём Function, который конвертирует числа из формата строк (String) в
        // формат чисел (Integer)

        Function<String, Integer> valueConverter = x -> Integer.valueOf(x);
        System.out.println(valueConverter.apply("789"));

        /*Типичный пример метода в Stream c аргументом Function — метод map,
         * который принимает элементы одного типа, что-то с ними делает и
         * передает дальше, но это уже могут быть элементы другого типа.
         * Как может выглядеть пример с Function в Stream*/
        List values = Stream.of("32", "43", "74", "54", "3", "56", "15")
                .map(x -> Integer.valueOf(x)).collect(Collectors.toList());
        System.out.println(values); //список в формате Integer
    }
}

//@FunctionalInterface
//public interface Function<T, R> {
//    R apply(T t);
//
//    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after);
//    default <V> Function<V, R> compose(Function<? super V, ? extends T> before);
//
//    static <T> Function<T, T> identity()
//}
