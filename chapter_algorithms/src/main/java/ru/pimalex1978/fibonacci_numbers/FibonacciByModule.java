package ru.pimalex1978.fibonacci_numbers;

/**
 * Класс производящий вычисление чисел Фибоначчи.
 * Вычисление без помощи кеша.
 * Выводим значение по модулю 1e9+7
 * поэтому можем использовать тип Integer
 */
public class FibonacciByModule {

    //для вывода числа по модулю заведем константу
    private static final int MOD = (int) (1e9 + 7);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new FibonacciByModule().run(10000);
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

        //будем хранить 0-е и 1-е число Фибоначчи
        int a = 0;
        int b = 1;

        //вычислим числа Фибоначчи, которые идут после a и b
        for (int i = 0; i < n; i++) {
            int c = (a + b) % MOD;
            //сдвигаем a и b на 1 шаг вперед, т.е. для a и b
            //присвоим значения b и c соответственно
            a = b;
            b = c;
        }
        return a;
    }

    /**
     * Метод запускающий вычисление числа Фибоначчи
     * и выводящий значения полученного числа на экран
     */
    private void run(int n) {
        System.out.println(n + ": " + fibonacciByModule(n));
    }
}
