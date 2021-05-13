package ru.pimalex1978;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * НОД
 * Наибольший общий делитель (НОД).
 * gcd (Greatest Common Divisor).
 * Ввести с клавиатуры 2 целых положительных числа.
 * Вывести в консоль наибольший общий делитель.
 * <p>
 * Для начала разберемся, что это и как это работает.
 * Алгоритм Евклида позволяет найти нам наибольший общий делитель чисел.
 * <p>
 * НОД существует для любых двух целых чисел.
 * <p>
 * Наибольший общий делитель существует и однозначно определён,
 * если хотя бы одно из чисел m или n не ноль.
 * <p>
 * gcd (Greatest Common Divisor)
 * <p>
 * Алгоритм Евклида:
 * на каждой итерации находим остаток от деления первого числа на второе,
 * значение второго числа присваиваем первому числу, а значение остатка —
 * второму числу. Так и продолжаем до тех пор, пока в результате очередного
 * деления, в остатке не получим ноль. Оставшееся первое число и будет
 * результатом.
 */
public class SolutionGCD {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите первое число:");
        int a = Integer.parseInt(reader.readLine());
        System.out.println("Введите второе число:");
        int b = Integer.parseInt(reader.readLine());

        System.out.println(gcd(a, b));
        System.out.println(gcd_2(a, b));
        //System.out.println(gcd_3(a, b));
        System.out.println(gcd_4(a, b));
        System.out.println(gcd_5(a, b));

    }

    /*
     * Пусть a = 18, b = 30.
     * Цикл: a!=0 and b!=0
     * Если a > b, то a = a % b, если меньше, то b = b % a, таким образом мы сначала находим остаток деления,
     * а потом повторяем действия. У нас a < b, значит, ищем остаток деления b % a (30 % 18) = 12,
     * присваиваем b = 12, повторяем цикл но теперь у нас уже a > b(b = 12)
     * значит выполняем a % b (18 % 12) = 6? снова переходим к циклу, теперь снова b > a,
     * значит выполняем b % a (30 % 6), остаток от деления 0, на этом мы прекращаем наш цикл и узнаем,
     * что наибольший общий делитель 18 и 30 = 6. и выводим a + b (b = 0, a = 6).
     */

    private static int gcd(int a, int b) {
        //проверка, что два аргумента одновременно не 0.
        if (a == 0 & b == 0) {
            throw new IllegalArgumentException("Нельзя использовать в аргументах два нуля!!!");
        }
        //перевод из отрицательных чисел в положительные
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private static int gcd_2(int a, int b) {
        //проверка, что два аргумента одновременно не 0.
        if (a == 0 & b == 0) {
            throw new IllegalArgumentException("Нельзя использовать в аргументах два нуля!!!");
        }
        if (b == 0)
            return Math.abs(a);
        return gcd_2(b, a % b);
    }

    /*
     * Классический алгоритм Евклида.
     *
     * НО ЕСТЬ БОЛЬШОЕ НО!!! Если хоть один аргумент будет равен 0,
     * мы получаем бесконечный цикл. И программа не завершиться.
     */

    private static int gcd_3(int a, int b) {
        //проверка, что два аргумента одновременно не 0.
        if (a == 0 & b == 0) {
            throw new IllegalArgumentException("Нельзя использовать в аргументах два нуля!!!");
        }
        //перевод из отрицательных чисел в положительные
        a = Math.abs(a);
        b = Math.abs(b);

        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    private static long gcd_4(long a, long b) {
        return b == 0 ? Math.abs(a) : gcd_4(b, a % b);
    }

    private static int gcd_5(int a, int b) {
        //Если первое число меньше второго, меняем их местами.
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        //Нахождение НОД
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}
