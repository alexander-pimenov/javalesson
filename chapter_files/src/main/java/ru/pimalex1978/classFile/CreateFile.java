package ru.pimalex1978.classFile;

import java.util.Formatter;

/**
 * Здесь приводится пример создания и записи
 * в файл некоторых данных.
 */
public class CreateFile {
    private Formatter x;

    //метод создания файла
    public void createFile() {
        try {
            x = new Formatter("c:\\test\\chinese.txt");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("You have an error");
        }
    }

    //метод записи в файл некоторой информации, используя метод format
    public void addRecords() {
        x.format("%s%s%s%n", "20 ", "bucky ", "roberts");
        x.format("%s%s%s%n", "60 ", "joy ", "doe");
        x.format("%s%s%s%n", "50 ", "mike ", "ike");
        x.format("%s%s%s%n", "46 ", "sis ", "priss");
    }

    //ОБЯЗАТЕЛЬНО - метод закрывающий файл
    public void closeFile() {
        x.close();
    }

}
