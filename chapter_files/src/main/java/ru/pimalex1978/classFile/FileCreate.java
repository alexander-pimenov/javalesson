package ru.pimalex1978.classFile;

import java.util.Formatter;

/**
 * Создание файла с помощью Форматера Formatter.
 */

public class FileCreate {
    public static void main(String[] args) {

        //создаем переменную Форматера
        final Formatter x;

        try {
            x = new Formatter("c:\\test\\fred.txt");
            //x = new Formatter("fred.txt"); - или просто
            // название файла и он будет создан в корне проекта
            System.out.println("You created a file!!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("You got an error");
        }
    }

}
