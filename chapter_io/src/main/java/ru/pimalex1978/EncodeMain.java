package ru.pimalex1978;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * С помощью FileInputStream получаем поток байтов из нашего источника.
 * Далее с помощью InputStreamReader преобразуем в поток символов и указываем кодировку.
 * Мы знаем, что в файле информация идет по строчно и далее для того чтоб считывать по
 * строчно обернем нашу конструкцию в BufferedReader, который на вход потребляет поток
 * символов, а на выход отдает поток строк. Он сам находит в потоке символов знаки
 * переноса строки, и отдает нам готовую строчку.
 * BufferedReader читает по строчно с помощью метода readLine(), если вернет null,
 * то уже нечего читать, конец файла.
 */
public class EncodeMain {
    public static void main(String[] args) {
        try {
            encodeDemo();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void encodeDemo() throws IOException {
        try (
                FileInputStream fis = new FileInputStream("C:\\projects\\javalessons\\chapter_io\\test.xml");
//        FileInputStream fis = new FileInputStream("test.xml");
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)
        ) {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        }
    }
}
