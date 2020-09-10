package ru.pimalex1978.function_interface;

import java.util.function.Predicate;

/**
 * Predicate встроенный функциональный интерфейс, добавленный в Java SE 8 в пакет java.util.function.
 * Принимает на вход значение, проверяет состояние и возвращает boolean значение в качестве результата.
 * Predicate подтверждает какое-то значение как true или false.
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

        //Рассмотрим пример использования дефолтного метода and()
        //Здесь Предикат обрабатывающий String.
        //Создаем две переменные с предикатом, которые описывают разные
        //условия
        Predicate<String> containsA = t -> t.contains("A");
        Predicate<String> containsB = t -> t.contains("B");
        //В основной метод test() передаем то что нужно проверить
        System.out.println(containsA.and(containsB).test("ABCDEFG")); //true
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

