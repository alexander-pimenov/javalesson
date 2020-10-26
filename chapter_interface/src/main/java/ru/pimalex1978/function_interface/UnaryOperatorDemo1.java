package ru.pimalex1978.function_interface;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * UnaryOperator — функциональный интерфейс, принимает в качестве
 * параметра объект типа T, выполняет над ним некоторые операции
 * и возвращает результат операций в виде объекта того же типа T
 */
public class UnaryOperatorDemo1 {
    public static void main(String[] args) {

        //UnaryOperator, который своим методом apply возводит число в квадрат
        UnaryOperator<Integer> squareValue = x -> x * x;
        System.out.println(squareValue.apply(11));
        System.out.println("-------");

        /*В качестве метода, использующего UnaryOperator как аргумент,
         * возьмем метод класса Stream — iterate.
         * Данный метод схож с методом generate: он также генерирует
         * бесконечную последовательность но имеет два аргумента:
         * первый — элемент, с которого начинается генерация последовательности;
         * второй — UnaryOperator, который указывает принцип генерации новых
         * элементов с первого элемента.
         * Как будет выглядеть наш пример UnaryOperator, но в методе iterate*/
        Stream.iterate(9, x -> x * x) //здесь UnaryOperator
                .limit(4)
                .forEach(System.out::println);
        System.out.println("-------");
        Stream.generate(() ->
                (int) (Math.random() * 10)) //здесь Supplier
                .limit(4).forEach(System.out::println);
        System.out.println("-------");
    }
}

//@FunctionalInterface
//public interface UnaryOperator {
//   T apply(T t);
//}
