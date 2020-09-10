package ru.pimalex1978.function_interface;

import java.util.function.Consumer;

/**
 * Consumer - встроенный функциональный интерфейс, добавленный в Java SE 8 в пакет java.util.function.
 * Принимает значение в качестве аргумента и ничего не возвращает.
 * Consumer интерфейс используется в случае, если необходимо передать объект на вход и произвести над
 * ним некоторые операции не возвращая результат.
 * Самый частый случай использования этого интерфейса - это вывод на консоль.
 * Функциональный дескриптор интерфейса:
 * T -> void
 */
public class ConsumerDemo1 {
    public static void main(String[] args) {
        //Использование интерфейса Consumer для вывода на консоль строки в верхнем регистре
        //Консумер обрабатывает Стринг, переводя все символы в верхний регистр.
        Consumer<String> printUpperCase = str -> System.out.println(str.toUpperCase());

        printUpperCase.accept("hello world"); //HELLO WORLD

        //Рассмотрим пример использования дефолтного метода andThen()
        Consumer<String> printLowerCase = str -> System.out.println(str.toLowerCase());

        printUpperCase.andThen(printLowerCase).accept("Hello world");
        //HELLO WORLD
        //hello world
    }

}

//Описание интерфейса Consumer:
//@FunctionalInterface
//public interface Consumer<T> {
//    void accept(T t);
//
//    default Consumer<T> andThen(Consumer<? super T> after)
//}
