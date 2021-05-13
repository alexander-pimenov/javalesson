package ru.pimalex1978.fibonacci_numbers;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс производящий вычисление чисел Фибоначчи.
 * Чтобы не было переполнения числа, будем использовать BigInteger,
 * а не Integer или Long
 * Выводим значение по модулю 1e9+7
 */
public class FibonacciWithCacheByModule {

    //для вывода числа по модулю заведем константу
    private static final int MOD = (int) (1e9 + 7);

    //для хранения вычисленных значений создадим кеш
    List<Integer> cache = new ArrayList<>();

    //инициализируем кеш сразу положив туда первые два числа Фибоначчи
    {
        //0-е число
        cache.add(0);
        //1-е число
        cache.add(1);
    }

    public static void main(String[] args) {
        //так можно увидеть сколько ра вызывается метод для каждого числа
//        for (int i = 0; i < 1000; i++) {
//            long startTime = System.currentTimeMillis();
//            new Fibonacci().run(i);
//            long finishTime = System.currentTimeMillis();
//            System.out.println(finishTime - startTime + " ms");
//        }

        long startTime = System.currentTimeMillis();
        new FibonacciWithCacheByModule().run(10000);
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");

    }

    /**
     * Метод вычисляющий число Фибоначчи путем вычисления
     * по формуле каждого числа и сохранения значения
     * для этого числа в кеш.
     *
     * @param n входящее положительное число
     * @return число Фибоначчи
     */
    private int fibonacciByModule(int n) {
        //в кеше лежат значения от 0 до cache.size - 1
        //нужно положить в кеш от cache.size ... до n
        for (int i = cache.size(); i <= n; i++) {
            //кладем в кеш i-е число Фибоначчи
            //вычисляем его по формуле F(n-1)+F(n-2)
            //и берем по модулю
            int res = (cache.get(i - 1) + cache.get(i - 2)) % MOD;
            cache.add(res); //автоматически кладем в конец кеша
        }
        return cache.get(n);
    }

    /**
     * Метод запускающий вычисление числа Фибоначчи
     * и выводящий значения полученного числа на экран
     */
    private void run(int n) {
        System.out.println(n + ": " + fibonacciByModule(n));
    }
}
