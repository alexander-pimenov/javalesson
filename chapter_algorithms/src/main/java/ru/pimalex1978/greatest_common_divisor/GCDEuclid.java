package ru.pimalex1978.greatest_common_divisor;

import java.math.BigInteger;

/**
 * Чтобы НОД находился быстро, применим алгоритм Евклида.
 * Алгоритм Евклида:
 * на каждой итерации находим остаток от деления первого числа на второе,
 * значение второго числа присваиваем первому числу, а значение остатка —
 * второму числу. Так и продолжаем до тех пор, пока в результате очередного
 * деления, в остатке не получим ноль. Оставшееся первое число и будет
 * результатом.
 */
public class GCDEuclid {

    /**
     * Метод перебирает все делители чисел и
     * находит максимальный.
     * Используем рекурсию.
     *
     * @param a первое число
     * @param b второе число
     * @return gcd
     */
    private long gcdRecurcive(long a, long b) {
        //чтоб проследить, как работает алгоритм добавим вывод текущих чисел
        System.out.println(a + " " + b);

        //проверяем числа на 0
        if (a == 0) return b;

        if (b == 0) return a;

        //большее делим на меньшее
        if (a >= b) {
            return gcdRecurcive(a % b, b);
        } else {
            return gcdRecurcive(a, b % a);
        }
    }

    /**
     * Метод находящий НОД.
     * Не используем рекурсию.
     *
     * @param a первое число
     * @param b второе число
     * @return НОД
     */
    private long gcd(long a, long b) {
        while (true) {
            //чтоб проследить, как работает алгоритм добавим вывод текущих чисел
            System.out.println(a + " " + b);

            //проверяем числа на 0
            if (a == 0) return b;

            if (b == 0) return a;

            //большее делим на меньшее
            if (a >= b) {
                //a,b <- a % b, b
                a = a % b;
            } else {
                //a,b <- a, b % a
                b = b % a;
            }
        }
    }

    /**
     * Метод находящий НОД.
     * Не используем рекурсию.
     *
     * @param a первое число
     * @param b второе число
     * @return НОД
     */
    private BigInteger gcdBigInteger(BigInteger a, BigInteger b) {
        while (true) {
            //чтоб проследить, как работает алгоритм добавим вывод текущих чисел
            System.out.println(a + " " + b);

            //проверяем числа на 0
            if (a.equals(BigInteger.ZERO)) return b;

            if (b.equals(BigInteger.ZERO)) return a;

            //большее делим на меньшее
            if (a.compareTo(b) >= 0) {
                //a,b <- a % b, b
                a = a.mod(b);
            } else {
                //a,b <- a, b % a
                b = b.mod(a);
            }
        }
    }

    private void run() {
//        System.out.println(gcdRecurcive(941594159159L, 544564565758L));
//        System.out.println(gcd(11415951L, 1141553156L));
        
        System.out.println(gcdBigInteger(
                new BigInteger("11415951789456123258"),
                new BigInteger("114155318415254756")));
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new GCDEuclid().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }
}
