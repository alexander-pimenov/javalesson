package ru.pimalex1978.io_javacourse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Преобразование потоков и кодировка.
 * Чтение одного и того же файла с разными кодировками.
 * Источник:
 * https://java-course.ru/begin/io_file/
 */

public class EncodeReader {
    public static void main(String[] args) {
        final String fileName = "C:/test/text_example.txt";    // Подставить имя текстового файла

        readFile(fileName, "UTF-8");          // Читаем в кодировке UTF-8
        System.out.println();
        readFile(fileName, "windows-1251");   // Читаем в кодировке Windows-1251
    }

    private static void readFile(String fileName, String charset) {
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis, Charset.forName(charset));
             BufferedReader br = new BufferedReader(isr)) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
