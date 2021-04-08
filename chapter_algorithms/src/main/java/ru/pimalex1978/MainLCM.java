package ru.pimalex1978;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Найти наименьшее общее кратное всех целых чисел от 1 до n.
 * <p>
 * Наименьшим общим кратным натуральных чисел a1, a2, ..., ak называется число A,
 * такое что A делится на ai для всех i от 1 до k, причем A –
 * наименьшее натуральное число, обладающее таким свойством.
 * <p>
 * Вход. Одно целое число n (1 ≤ n ≤ 1000).
 * <p>
 * Выход. Выведите одно целое число – наименьшее общее кратное всех чисел от 1 до n.
 * <p>
 * Пример входа 3, 2
 * <p>
 * Пример выхода НОК 6
 * <p>
 * Анализ алгоритма
 * <p>
 * Поскольку n ≤ 1000, то следует воспользоваться длинной арифметикой.
 * Например, классом BigInteger в Java.
 * Напомним, что НОК(a, b) = a * b / НОД(a, b).
 */

public class MainLCM {
    public static void main(String[] args) {

        System.out.println("Введите целое число n: ");
        Scanner con = new Scanner(System.in);

        int n = con.nextInt();

        BigInteger res = BigInteger.ONE;

        for (int i = 1; i <= n; i++) {

            BigInteger x = BigInteger.valueOf(i);

            res = res.multiply(x).divide(res.gcd(x));

            // res = res * x / gcd(res,x)

        }

        System.out.println(res);

        con.close();

    }
}
