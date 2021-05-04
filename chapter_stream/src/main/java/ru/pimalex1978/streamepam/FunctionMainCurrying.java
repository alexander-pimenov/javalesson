package ru.pimalex1978.streamepam;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Каррирование — это преобразование функции, берущей несколько
 * параметров за один раз, в функцию, берущую только один параметр.
 * Функция не выполнится, пока не будут переданы все параметры.
 * Кроме того, каррирование можно использовать при обращении к
 * глобальным переменным, т. е. делать это «чисто».
 * В разделе рассматриваются: чистые функции (Pure functions) и
 * композиция (Composition)
 * Рассмотрим пример кастомного каррирования.
 * Предположим нужно написать функциональный интерфейс (представлен в
 * коде ниже), который будет позволять принимать два аргумента
 * (наследник BiFunction) и что то возвращает.
 * Т.е. имея функцию с двумя аргументами мы можем фиксировать один
 * аргумент и довычислять другой аргумент.
 * Метод apply() в интерфейсе будет доступен, т.к. мы extends BiFunction
 */
public class FunctionMainCurrying {
    public static void main(String[] args) {

        System.out.println("===каррирование===");

        //счетная машинка
        CalculatePerDiem machine = new CalculatePerDiem();

        //фиксируем один аргумент, курс доллара, т.е. каррируемся по первому аргументу
        Function<Integer, Double> curriedByFirstArgument = machine.curryFirstArgument(74.4);

        //рассчитать сумму за 5 дней
        System.out.println(curriedByFirstArgument.apply(5));
        //рассчитать сумму за 10 дней
        System.out.println(curriedByFirstArgument.apply(10));

        //фиксируем количество дней командировки, каррируемся по второму аргументу
        Function<Double, Double> curryBySecondArgument = machine.currySecondArgument(10);

        //рассчитать сумму за при курсе 73,12
        System.out.println(curryBySecondArgument.apply(73.12));
        //рассчитать сумму за при курсе 73,5
        System.out.println(curryBySecondArgument.apply(73.5));
    }
}

/**
 * Функция принимающая два аргумента и наследующая
 * функциональный интерфейс BiFunction
 *
 * @param <T> первый параметр
 * @param <U> второй параметр
 * @param <R> возвращающий тип
 */
@FunctionalInterface
interface CurriedBiFunction<T, U, R> extends BiFunction<T, U, R> {
    //объявим два дефолных метода чтобы каррироваться по первому аргументу и второму
    default Function<U, R> curryFirstArgument(T t) {
        return u -> apply(t, u);
    }

    default Function<T, R> currySecondArgument(U u) {
        return t -> apply(t, u);
    }
}

/**
 * Класс для рассчета суточных.
 * Для работы с этим классом нужны численные типы
 * Integer, Double
 */
class CalculatePerDiem implements CurriedBiFunction<Double, Integer, Double> {

    //ставка как константа
    private static final Double perDiemRate = 10.15; //например это сумма в долларах

    /**
     * Метод считающий сумму денег в зависимости от курса, коэффициента
     * и количества дней.
     * Переопределяем метод apply().
     *
     * @param dollarExchangeRate курс доллара
     * @param amountOfDays       количество дней в командировке
     * @return результирующая сумма
     */
    @Override
    public Double apply(Double dollarExchangeRate, Integer amountOfDays) {
        return dollarExchangeRate * amountOfDays * perDiemRate;
    }
}