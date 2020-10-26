package ru.pimalex1978.function_interface;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Predicate встроенный функциональный интерфейс, добавленный в Java SE 8 в пакет java.util.function.
 * Принимает на вход значение, проверяет состояние и возвращает boolean значение в качестве результата.
 * Predicate подтверждает какое-то значение как true или false.
 * <p>
 * Predicate — функциональный интерфейс для проверки соблюдения некоторого
 * условия. Если условие соблюдается, возвращает true, иначе — false.
 * <p>
 * Предикат показывает условие, которое должно обрабатывать какой то класс.
 * Например, у нас в примерах ниже, показаны предикаты,
 * которые обрабатывают Integer - Predicate<Integer>, Стринг - Predicate<Integer>
 * <p>
 * Рассмотрим пример использования интерфейса Predicate для нахождения отрицательных чисел.
 * И пример использования метода and() интерфейса Predicate.
 * Функциональный дескриптор интерфейса:
 * T -> boolean
 */
public class PredicateDemo1 {
    public static void main(String[] args) {

        //Предикат обрабатывающий Integer, в условии указываем, какое условие
        //обрабатываем: ищем значение меньше 0
        Predicate<Integer> negative = i -> i < 0;
        System.out.println(negative.test(-6)); //true
        System.out.println(negative.test(6)); //false
        System.out.println(negative.test(0)); //false

        //Еще пример: рассмотрим создание Predicate, который будет проверять
        // на чётность числа типа Integer
        Predicate<Integer> isEventNumber = x -> x % 2 == 0;
        System.out.println(isEventNumber.test(4)); //вызываем метод test(T) true
        System.out.println(isEventNumber.test(3)); //false

        //Рассмотрим пример использования дефолтного метода and()
        //Здесь Предикат обрабатывающий String.
        //Создаем две переменные с предикатом, которые описывают разные
        //условия
        Predicate<String> containsA = t -> t.contains("A");
        Predicate<String> containsB = t -> t.contains("B");
        //В основной метод test() передаем то что нужно проверить
        System.out.println(containsA.and(containsB).test("ABCDEFG")); //true

        /*Для примера возьмем метод класса Stream — filter, который в качестве
         * аргумента принимает Predicate и возвращает Stream только с теми
         * элементами, которые удовлетворяют условию Predicate.
         * В контексте Stream-а это означает, что он пропускает только те
         * элементы, которые возвращают true при использовании их в методе
         * test интерфейса Predicate.
         * Вот как будет выглядеть наш пример для Predicate, но уже для
         * фильтра элементов в Stream
         * И, как мы помним, collect будет собирать все элементы в некоторую
         * коллекцию: в нашем случае — в List.
         * */
        List evenNumbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .filter(x -> x % 2 == 0) //здесь есть Predicate
                .collect(Collectors.toList());
        System.out.println(evenNumbers); //[2, 4, 6, 8]

    }
}

//Описание интерфейса Predicate:
//@FunctionalInterface
//public interface Predicate<T> {
//    boolean test(T t);
//
//    default Predicate<T> and(Predicate<? super T> other);
//    default Predicate<T> or(Predicate<? super T> other);
//    default Predicate<T> negate();
//}

