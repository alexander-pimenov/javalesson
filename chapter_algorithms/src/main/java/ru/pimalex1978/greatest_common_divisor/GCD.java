package ru.pimalex1978.greatest_common_divisor;

public class GCD {

    /**
     * Метод перебирает все делители чисел и
     * находит максимальный
     *
     * @param a первое число
     * @param b второе число
     * @return gcd
     */
    private int gcd(int a, int b) {
        //переменная с ответом
        int res = 0;
        //переберем все числа от 1 до маскимального из этих
        //двух числе
        for (int i = 1; i < Math.max(a, b); i++) {
            //проверяем что оба числа делятся на это число
            if (a % i == 0 && b % i == 0) {
                res = i;
            }
        }
        return res;
    }

    private void run() {
        System.out.println(gcd(1141595114, 5457531));
        System.out.println(gcd(1141 * 5951, 1141 * 5531));
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new GCD().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }
}
