package ru.pimalex1978.streamepam;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Числа Фибоначчи - это
 * ряд чисел, в котором первые два числа равны либо
 * 1 и 1, либо 0 и 1, а каждое последующее число
 * равно сумме двух предыдущих чисел.
 */
public class FunctionMainCompositions {
    public static void main(String[] args) {

        System.out.println("===композиции===");
        /*очередность выполнения функций*/
        List<Integer> fibonacciNumbers = Arrays.asList(1, 1, 2, 3, 5, 8, 13);

        //сделаем ряд преобразований над числами
        //создадим две функции для преобразования чисел
        Function<Integer, Integer> addTwo = e -> e + 2;
        Function<Integer, Integer> multipleTen = e -> e * 10;

        //используем функцию addTwo
        fibonacciNumbers.stream().map(addTwo).forEach(System.out::println);
        //output (3 3 4 5 7 10 15)
        //а теперь хотим в точке map сделать композицию двух функций
        //сначала +2 а потом *10
        fibonacciNumbers.stream().map(addTwo.andThen(multipleTen)).forEach(System.out::println);
        //output (30 30 40 50 70 100 150)

        //сначала *10 а потом +2 (порядок поменялся!!!!!!!!)
        fibonacciNumbers.stream().map(addTwo.compose(multipleTen)).forEach(System.out::println);
        //output (12 12 22 32 52 82 132)

        /*сделаем свой вариант compose*/
        /*берем функцию высшего порядка map и передаем в неё функцию, которая будет брать и композировать
         * две других функции*/
        fibonacciNumbers.stream().map(compose(addTwo, multipleTen)).forEach(System.out::println);
        //output (30 30 40 50 70 100 150)

    }

    /**
     * Делаем свой вариант compose.
     * В результате вызываем метод apply одной функции
     * передав ей в параметр
     *
     * @param x первая функция
     * @param y вторая функция
     * @return функция результат
     */
    private static Function<Integer, Integer> compose(Function<Integer, Integer> x, Function<Integer, Integer> y) {
        return result -> y.apply(x.apply(result));
    }
}
