package ru.pimalex1978.function_interface;

import java.util.function.Function;

/**
 * Function  - это встроенный функциональный интерфейс, добавленный в Java SE 8 в пакет java.util.function.
 * Принимает значение в качестве аргумента одного типа и возвращает
 * другое значение. Часто используется для преобразования одного значения в другое.
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
