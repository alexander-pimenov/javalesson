package ru.pimalex;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

/**
 * "Чтение из файла CSV и поиск записи в Java [Max O'Didily]"
 * "Reading from a CSV File and Searching for a Record in Java"
 * https://www.youtube.com/watch?v=bIjMDpspzog
 * Результат выводится в всплывающем окне (библиотека swing).
 */
public class ReadNSearchFile {
    private static Scanner x;

    public static void main(String[] args) {
//        String filePath = "tutorial.csv";
        String filePath = ReadNSearchFile.class.getResource("/tutorial.csv").getPath();
        String searchTerm = "5464"; //вводим ID, который надо найти

        readRecordAndSearch(searchTerm, filePath);
    }

    public static void readRecordAndSearch(String searchTerm, String filePath) {
        /*Обрати внимание, что из файла все данные читаем, как тип String,
        * хотя конечно можно их читать и как int, например.*/
        boolean found = false;
        String ID = "";
        String name1 = "";
        String age = "";
        try {
            x = new Scanner(new File(filePath));
            //Укажем разделитель
            x.useDelimiter("[,\n]"); //разделитель - (,) или (перевод строки)
//            x.useDelimiter("[,;\n]"); //разделитель - (,) или (;) или (перевод строки)
//            x.useDelimiter(","); // так в разделителе укажем только (,)

            while (x.hasNext() && !found) {
                ID = x.next();
                name1 = x.next();
                age = x.next();

                //проверим на соответствие с тем, что ищем searchTerm
                if (ID.equals(searchTerm)) {
                    found = true;
                }
            }
            //Если нашли, то что искали, то:
            if (found) {
                JOptionPane.showMessageDialog(null, "ID: " + ID
                        + " Name: " + name1 + " Age: " + age);
            }
            //если не нашли, то:
            else {
                JOptionPane.showMessageDialog(null, "Record not found");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
    }
}
