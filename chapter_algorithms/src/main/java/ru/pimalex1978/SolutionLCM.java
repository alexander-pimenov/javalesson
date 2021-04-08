package ru.pimalex1978;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.google.common.math.LongMath.gcd;

/**
 * НОК (Наименьшее общее кратное) или lcm (Least Common Multiple)
 * НОК - наименьшее число, которое делится на a и b без остатка.
 * НОК можно найти через НОД по следующей формуле:
 * НОК(a,b)=(a*b)/(НОД(a,b))
 *
 * Здесь для вычисления НОД используем библиотеку com.google.common.math.LongMath.gcd
 */

public class SolutionLCM {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());

        System.out.println(lcm(a,b));
    }

    private static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

}
