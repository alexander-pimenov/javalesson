package ru.pimalex1978.classFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Класс java.util.Scanner был введен в Java 1.5 в качестве простого
 * текстового сканера, с помощью которого можно разбирать примитивные
 * типы и строки с использованием регулярных выражений.
 * Также класс java.util.Scanner может быть использован для работы с
 * файлами, а именно для чтения данных из файла в примитивные типы,
 * а также использует split(), чтобы получить String, int, long, Integer
 * и другие классы-оболочки.
 * В примере ниже мы будем построчно считывать CSV-файл на Java.
 */

public class JavaFileScanner {
    public static void main(String[] args) throws IOException {
        /**
         * У нас есть файл c:\test\source.txt, в нем заисаны такие данные:
         *
         * Меня зовут Андрей
         * Мой сайт javadevblpg.com
         * Номер тел. 10987654321
         */
        String fileName = "c:\\test\\source.txt";
        Path path = Paths.get(fileName);

        //Создаем объект Scanner, чтобы с его помощью прочитать файл, путь к которому
        //передали Сканеру в параметре.
        Scanner scanner = new Scanner(path);

        // построчно считываем файл
        // getProperty Получает системное свойство, указанное
        // указанным ключом "line.separator" (Параметр: key - имя системного свойства)

        scanner.useDelimiter(System.getProperty("line.separator"));
        while (scanner.hasNext()) {
            System.out.println("Строка: " + scanner.next());
        }
        scanner.close();

        // считываем CSV файл и парсим его в массив объектов
        /**
         * Андрей,20,мужчина
         * Оля,25,женщина
         * Витя,30,мужчина
         */
        // вызовем scanner записав одной строкой
        scanner = new Scanner(Paths.get("c:\\test\\data.csv"));
        scanner.useDelimiter(System.getProperty("line.separator"));
        while (scanner.hasNext()) {
            // парсим строку в объект Employee с помощью метода parseCSVLine
            Employee2 emp = parseCSVLine(scanner.next());
            System.out.println(emp.toString());
        }
        scanner.close();

        //читаем с System.in
        scanner = new Scanner(System.in);
        System.out.println("Вводим первое слово: " + scanner.next());
    }

    private static Employee2 parseCSVLine(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter("\\s*,\\s*");
        String name = scanner.next();
        int age = scanner.nextInt();
        String gender = scanner.next();
        JavaFileScanner jfs = new JavaFileScanner();
        return jfs.new Employee2(name, age, gender);
    }

    public class Employee2 {
        private String name;
        private int age;
        private String gender;

        public Employee2(String n, int a, String gen) {
            this.name = n;
            this.age = a;
            this.gender = gen;
        }

        @Override
        public String toString() {
            return "Name=" + this.name + "::Age=" + this.age + "::Gender=" + this.gender;
        }
    }
}
