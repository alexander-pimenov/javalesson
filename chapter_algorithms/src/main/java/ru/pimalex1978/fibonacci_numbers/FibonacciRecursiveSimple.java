package ru.pimalex1978.fibonacci_numbers;

/* Простая реализация поиска числа Фибоначчи.
 * Поиск значения 40 выполняется 1,5 секунды.
 * А поиск значения 50 выполняется уже 110 секунд, т.е. почти 2 минуты!!!
 * Поэтому для увеличения скорости программы нужно использовать
 * кеш, для хранения промежуточного значения.
 *
 *   // 1, 1, 2, 3, 5, 8, 13, ...
 *   // f(0) = 1
 *   // f(1) = 1
 *   // f(n) = f(n-1) + f(n-2)
 * */
public class FibonacciRecursiveSimple {
    public static int fibonacci(int input) {
        if (input < 0) {
            return 1;
        }
        if (input < 2) {
            return input;
        }
        return fibonacci(input - 1) + fibonacci(input - 2);

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(fibonacci(1));
        System.out.println(System.currentTimeMillis() - start);
    }
}
