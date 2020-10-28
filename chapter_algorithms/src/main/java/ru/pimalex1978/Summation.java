package ru.pimalex1978;

/**
 * Программа суммирования чисел входящих в заданое число.
 * http://espressocode.top/java-program-for-sum-the-digits-of-a-given-number/
 * Этот код предоставлен Gitanjali
 */
public class Summation {
    public static void main(String[] args) {

        System.out.println(getSumIterative(687)); // 21
        System.out.println(getSumOneString(687)); // 21
        System.out.println(getSumRecursive(687)); // 21
    }

    /**
     * Функция для получения суммы цифр.
     * %10 - получаем в остатке последний разряд
     * /10 - откидываем последний разряд,укорачиваем число, т.к. работаем с int
     * Пример: 687
     * 1-я итерация: 687%10=7 687/10=68 sum=7
     * 2-я итерация  68%10=8  68/10=6   sum=7+8=15
     * 3-я итерация  6%10=6   6/10=0    sum=7+8+6=21
     */
    public static int getSumIterative(int n) {
        int sum = 0;
        while (n != 0) {
            sum = sum + n % 10;
            n = n / 10;
        }
        return sum;
    }

    public static int getSumOneString(int n) {
        int sum;
        for (sum = 0; n > 0; sum += n % 10, n /= 10) ;
        return sum;
    }

    public static int getSumRecursive(int number) {

        return number == 0 ? 0 : number % 10 + getSumRecursive(number / 10);
    }
}
