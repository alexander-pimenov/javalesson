package ru.pimalex1978.classFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Для считывания данных из CSV файла мы можем использовать Java класс Scanner.
 * Если вам нужно считывать с файла, опираясь на разделитель, то желательно использовать класс Scanner.
 * У нас в файле .csv есть разделитель ';'
 */

public class ReadCSVWithScanner {
    public static void main(String[] args) throws IOException {
        // открываем файл
        BufferedReader reader = new BufferedReader(new FileReader("c:\\test\\testEmp.csv"));

        // считываем построчно
        String line = null;
        Scanner scanner = null;
        int index = 0;

        List<Employee> empList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            Employee emp = new Employee();
            //считаем строку
            scanner = new Scanner(line);
            //укажем разделитель в строке
            scanner.useDelimiter(";");
            //переберем все эелементы строки разделенные разделителем
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0)
                    emp.setId(Integer.parseInt(data));
                else if (index == 1)
                    emp.setName(data);
                else if (index == 2)
                    emp.setRole(data);
                else if (index == 3)
                    emp.setSalary(data);
                else
                    System.out.println("Некорректные данные::" + data);
                index++;
            }
            index = 0;
            empList.add(emp);
        }

        //закрываем наш ридер
        reader.close();

        System.out.println(empList);
    }
}
