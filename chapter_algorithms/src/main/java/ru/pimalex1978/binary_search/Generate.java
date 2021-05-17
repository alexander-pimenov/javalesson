package ru.pimalex1978.binary_search;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

/**
 * Класс для тестирования с использованием
 * кода Двоичного поиска.
 * Генерирует 100_000 чисел для двух массивов.
 */
public class Generate {
    public static void main(String[] args) throws FileNotFoundException {
        new Generate().run();
    }

    private void run() throws FileNotFoundException {
        //Данные будем выводить в файл
        PrintWriter printWriter = new PrintWriter("inputBinarySearch.txt"); //файл в корне проекта
        int n = 100000; //количество запросов
        int k = 100000; //количество чисел

        //генератор рандомных чисел
        Random random = new Random();
        int[] a = new int[n];
        int[] b = new int[k];
        //заполним массив a[] случайными числами
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(1000_000_000);
        }
        //Отсортируем массив a[]
        Arrays.sort(a);

        //заполним массив b[] случайными числами, встречающимися в
        //массиве a[], чтоб было что искать
        for (int i = 0; i < k; i++) {
            b[i] = a[random.nextInt(n)];
        }
        //выводим числа массива a[] в файл
        printWriter.print(n);
        for (int i = 0; i < n; i++) {
            printWriter.print(" " + a[i]);
        }
        printWriter.println(); //перевод строчки в файле
        //выводим числа массива b[] в файл
        printWriter.print(k);
        for (int i = 0; i < k; i++) {
            printWriter.print(" " + b[i]);
        }
        printWriter.println(); //перевод строчки в файле
        printWriter.close(); //закрываем, иначе он не сбросит буфер и ничего не сохраниться
    }
}
