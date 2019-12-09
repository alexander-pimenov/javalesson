package ru.pimalex1978.classFile;

import java.io.File;
import java.util.Scanner;

/**
 * Пример чтения данных из файла с помощью Сканнера.
 */

public class ReadFile {
    private Scanner x;

    //метод для открытия файла
    public void openFile() {
        try {
            x = new Scanner(new File("c:\\test\\chik.txt"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Could not file");
        }
    }

    //метод чтения из файла
    //тоже обернули в try-catch,т.к. если файла такого нет то его
    //и не открыть и не прочитать
    public void readFile() {
        try {
            //прочитать содержимое. читать пока есть содержимое
            while (x.hasNext()) {
                String a = x.next();
                String b = x.next();
                String c = x.next();

                System.out.printf("%s %s %s\n", a, b, c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Could not file");
            //e.printStackTrace();
        }
    }

    //метод закрытия файла
    public void closeFile(){
        x.close();
    }
}
