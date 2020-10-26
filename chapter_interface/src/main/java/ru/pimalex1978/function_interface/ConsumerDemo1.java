package ru.pimalex1978.function_interface;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Consumer - встроенный функциональный интерфейс, добавленный в Java
 * SE 8 в пакет java.util.function.
 * Принимает значение в качестве аргумента и ничего не возвращает.
 * <p>
 * Consumer (с англ. — “потребитель”) — функциональный интерфейс, который
 * принимает в качестве входного аргумента объект типа T, совершает некоторые
 * действия, но при этом ничего не возвращает.
 * <p>
 * Consumer интерфейс используется в случае, если необходимо передать объект
 * на вход и произвести над ним некоторые операции не возвращая результат.
 * Самый частый случай использования этого интерфейса - это вывод на консоль.
 * Потребитель вызывается для своих побочных эффектов.
 * В терминах Java Consumer это идиома для void метод.
 * Хорошим примером являются методы "сеттера"
 * Функциональный дескриптор интерфейса:
 * T -> void
 */
public class ConsumerDemo1 {
    public static void main(String[] args) {
        //Использование интерфейса Consumer для вывода на консоль строки в верхнем регистре
        //Консумер обрабатывает Стринг, переводя все символы в верхний регистр.
        Consumer<String> printUpperCase = str -> System.out.println(str.toUpperCase());

        printUpperCase.accept("hello world"); //вызываем accept(T) HELLO WORLD

        //Рассмотрим пример использования дефолтного метода andThen()
        Consumer<String> printLowerCase = str -> System.out.println(str.toLowerCase());

        printUpperCase.andThen(printLowerCase).accept("Hello world");
        //HELLO WORLD
        //hello world

        //Еще пример с BiConsumer:
        Consumer<String> printConsumer = a1 -> System.out.println(a1);
        BiConsumer<Integer, Integer> sumConsumer = (a1, a2) -> System.out.println(a1 + a2);

        /*Одним из методом в Stream, который использует функциональный
         * интерфейс Consumer, является метод peek.
         * Но так как метод peek работает с Consumer, модификации строк
         * в Stream не произойдет, а сам peek вернет Stream с
         * изначальными элементами: такими, какими они ему пришли.
         * Поэтому список peopleGreetings будет состоять из элементов
         * "Elena", "John", "Alex", "Jim", "Sara".
         * Также есть часто используемый метод foreach, который аналогичен
         * методу peek, но разница состоит в том, что он
         * конечный — терминальный.
         * Так будет выглядеть наш пример для Consumer в Stream*/
        List peopleGreetings = Stream.of("Elena", "John", "Alex", "Jim", "Sara")
                .peek(x -> System.out.println("Hello " + x + " !!!"))
                .collect(Collectors.toList());
    }

}

//Описание интерфейса Consumer:
//@FunctionalInterface
//public interface Consumer<T> {
//    void accept(T t);
//
//    default Consumer<T> andThen(Consumer<? super T> after)
//}

//Еще небольшое объяснение потребителя:
//public void sum(Integer a, Integer b) {
//    System.out.println(a + b);
//}

