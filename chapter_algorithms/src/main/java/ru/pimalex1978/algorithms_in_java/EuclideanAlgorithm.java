package ru.pimalex1978.algorithms_in_java;

/*Алгоритм Евклида.
 * Нахождение наибольшего общего делителя.
 * Это такое число, на которое делятся оба числа без остатка.
 *
 * Будем использовать здесь Рекурсию.
 * Рекурсия - это вызов функции внутри самой себя.
 * */

public class EuclideanAlgorithm {

    public static void main(String[] args) {
        System.out.println("Наибольший общий делитель:");
        System.out.println(divide(21, 4));

        // 16 и 4 => 4
        // 18 и 4 => 2
        // 17 и 4 => 1
        // 17 и 4 => 1
        // 21 и 4 => 1

    }

    /*Будем использовать Рекурсию.
     * Важно, что в рекурсионном вызове должно быть условие выхода из него,
     * здесь это if (b == 0), и что в параметре метода переменные становяться по
     * указанным местам (первое место, второе место), и следующая рекурсия
     * вызывается уже с новыми переменными записанными в return divide(b, c)*/
    private static int divide(int a, int b) {
        /*если второе число = 0, т.е. деление произошло без остатка,
         * то возвращаем первое число.
         * */
        if (b == 0) {
            return a;
        } //это также и проверка деления на 0
        int c = a % b; //c - остаток от деления
        return divide(b, c);
        //Подразумевается, что a>b. Проверку эту не делаем.
    }
}
