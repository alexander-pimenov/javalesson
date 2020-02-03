package ru.pimalex1978.yandex;

import java.util.Scanner;

/**
 * Даны два числа
 * A и B. Вам нужно вычислить их сумму
 * A + B.
 * В этой задаче для работы с входными и выходными данными
 * вы можете использовать и файлы и потоки на ваше усмотрение.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число a:");
        int a = sc.nextInt();
        System.out.println("Введите число b:");
        int b = sc.nextInt();
        System.out.println("Сумма a + b = " + (a + b));
    }

}
