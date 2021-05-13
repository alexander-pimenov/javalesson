package ru.pimalex1978.fibonacci_numbers;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс производящий вычисление чисел Фибоначчи.
 * Рекурсивное вычисление.
 * Чтобы не было переполнения числа, будем использовать BigInteger,
 * а не Integer или Long
 */
public class FibonacciRecursive {

    //для хранения вычисленных значений создадим кеш
    Map<Integer, BigInteger> cache = new HashMap<>();

    //счетчик рекурсивных вызовов
    long cnt;

    public static void main(String[] args) {
        //так можно увидеть сколько ра вызывается метод для каждого числа
//        for (int i = 0; i < 1000; i++) {
//            long startTime = System.currentTimeMillis();
//            new FibonacciRecursive().run(i);
//            long finishTime = System.currentTimeMillis();
//            System.out.println(finishTime - startTime + " ms");
//        }

        long startTime = System.currentTimeMillis();
        new FibonacciRecursive().run(1000);
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");

    }

    /**
     * Наивный метод рекурсивно вычисляющий число Фибоначчи.
     * Чтобы не делать много повторных вызовов, будем сохранять значения
     * уже вычисленных чисел и не будем вычислять их по второму разу.
     *
     * @param n входящее положительное число
     * @return число Фибоначчи
     */
    private BigInteger fibonacciRecursive(int n) {
        //при каждом вызове увеличиваем счетчик, т.е. наглядно видим
        //сколько раз вызывается функция
        //Увеличиваться будет по экспоненте
        cnt++;

        if (n < 2) {
            return BigInteger.valueOf(n);
        }

        //если значение уже посчитано, т.е. мы уже положили его в кеш,
        //то вернем это значение
        if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            //если нет его в кеше, то оно еще не посчитано и мы его посчитаем
            //для ускорения работы этого метода посчитаем сначала все меньшие числа
            //сохранив их в кеш, а потом большие
            for (int i = 2; i <= n; i++) {
                BigInteger result = fibonacciRecursive(i - 1).add(fibonacciRecursive(i - 2));
//            long result = fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
                //сохраним теперь его в кеше
                cache.put(n, result);
            }
            return cache.get(n);
        }
    }

    /**
     * Метод запускающий вычисление числа Фибоначчи
     * и выводящий значения числа и количество вызова
     * функции на экран
     */
    private void run(int n) {
        System.out.println(n + ": " + fibonacciRecursive(n));
        System.out.println("[ " + cnt + " вызовов ]");
    }
}
