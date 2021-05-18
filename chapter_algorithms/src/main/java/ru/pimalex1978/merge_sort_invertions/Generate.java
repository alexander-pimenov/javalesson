package ru.pimalex1978.merge_sort_invertions;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Класс генерирующий 100_000 рандомных целых чисел и
 * записывающий их в файл inputInversions.txt в корне проекта.
 */
public class Generate {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("inputInversions.txt");

        //размер теста 100_000
        int n = 100_000;
        Random random = new Random();
        printWriter.println(n);
        for (int i = 0; i < n; i++) {
            printWriter.print(random.nextInt() + " ");
        }
        printWriter.close();
    }
}
