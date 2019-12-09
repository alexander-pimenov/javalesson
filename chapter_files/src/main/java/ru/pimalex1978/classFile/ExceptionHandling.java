package ru.pimalex1978.classFile;

import java.util.Scanner;

/**
 * Разбираем пример с исключением.
 */

public class ExceptionHandling {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        //сделаем цикл, чтоб если ввели не верные цифры и
        //получили деление на 0, то продолжали действия
        int x = 1;
        do {
            //т.к. во время вычислений возможно деление на 0, то
            //возьмем этот блок в блок try-catch
            try {
                System.out.println("Enter first num: ");
                int n1 = input.nextInt();
                System.out.println("Enter second num: ");
                int n2 = input.nextInt();
                int div = n1 / n2;
                System.out.println("Result div: " + div);
                //если правильно посчитали, то х=2 и мы выходим из цикла
                x = 2;
            } catch (Exception e) {
                System.out.println("You can't do that");
                //
                // e.printStackTrace();
            }
        } while (x == 1);
    }
}
