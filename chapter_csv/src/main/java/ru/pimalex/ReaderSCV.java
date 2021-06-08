package ru.pimalex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * "Java read CSV File [BroCode]"
 * https://www.youtube.com/watch?v=zKDmzKaAQro
 *
 * alexlorenlee.com - "Java: Read a CSV File into an Array"
 * https://www.youtube.com/watch?v=-Aud0cDh-J8
 * <p>
 * CSV - Comma-Separated Values
 * text file that uses a comma to separate values
 *
 * Используем BufferedReader, т.к. он намного быстрее Scanner.
 */

public class ReaderSCV {
    public static void main(String[] args) {

        //CSV - Comma-Separated Values
        //      text file that uses a comma to separate values


        String path1 = ReaderSCV.class.getResource("/SacramentoCrimeJanuary2006.csv").getPath();
        String path2 = ReaderSCV.class.getResource("/students.csv").getFile();
        String path3 = ReaderSCV.class.getResource("/username.csv").getFile();
        String path4 = ReaderSCV.class.getResource("/FL_insurance_sample.csv").getFile();

        extracted(path3);
    }

    private static void extracted(String path) {
        BufferedReader reader = null;
        String line = "";
        int count = 0; //чтобы можно было выводить только часть данных

        try {
            reader = new BufferedReader(new FileReader(path));

            while ((line = reader.readLine()) != null) {
                //чтобы можно было читать весь файл, а не несколько строчек,
                //то эту строчку можно закомментировать.
                if (count == 15) break;

                //System.out.println(line);
                //break; //чтобы прочитать только 1-ю строчку

                //Деление строки, используя запятую
//                String[] row = line.split(",");

                //Деление строки:
                //use this if your values already contain commas
                //Это регулярное выражение, и оно очень сложное. Он ищет строковые шаблоны.
                String[] row = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                //вывод разделенных данных
                for (String index : row) {
                    System.out.printf("%-15s", index);
                }
                System.out.println();
                count++;

                /*Если бы нам нужны были только определенные столбцы, то нужно было бы использовать
                * индексы в массиве. Конечно соответствующие определенным названиям
                * в первой строке, т.е. в шапке. Например:
                System.out.println("Date: " + row[0] + ", Description: " + row[4]);
                */

            }
            System.out.println("-= Количество строк записей в файле: " + (count - 1) + " =-");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
//student        drade          pass/fail
//Chad           61             pass
//Karen          50             fail
//SnoopDog       100            pass
