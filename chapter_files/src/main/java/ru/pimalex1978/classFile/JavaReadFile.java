package ru.pimalex1978.classFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Ниже представлен класс на Java для чтения содержимого файла с помощью
 * Scanner, Files, BufferedReader с поддержкой кодировки и FileReader
 */

public class JavaReadFile {
    public static void main(String[] args) throws IOException {

        String fileName = "c:\\test\\source.txt";

        // используем класс Files для обработки небольших файлов,
        // получаем содержимое файла
        System.out.println("Чтение файла спомощью Files:");
        readUsingFiles(fileName);

        // используем класс Scanner для больших файлов, читаем построчно
        //Если вам нужно считывать с файла, опираясь на разделитель, то
        //желательно использовать класс Scanner.
        System.out.println("\nЧтение файла спомощью Scanner:");
        readUsingScanner(fileName);

        // построчно читаем с файла с помощью BufferedReader
        System.out.println("\nЧтение файла спомощью BufferedReader:");
        readUsingBufferedReader(fileName);
        readUsingBufferedReaderJava7(fileName, StandardCharsets.UTF_8);
        readUsingBufferedReader(fileName, StandardCharsets.UTF_8);

        // читаем с помощью FileReader без поддержки кодировки
        System.out.println("\nЧтение файла спомощью FileReader:");
        readUsingFileReader(fileName);

    }

    private static void readUsingFileReader(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            // обрабатываем считанную строку - пишем ее в консоль
            System.out.println(line);
        }
        br.close();
        fr.close();
    }

    private static void readUsingBufferedReader(String fileName, Charset cs) throws IOException {
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, cs);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            // обрабатываем считанную строку - пишем ее в консоль
            System.out.println(line);
        }
        br.close();
    }

    private static void readUsingBufferedReaderJava7(String fileName, Charset cs) throws IOException {
        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path, cs);
        String line;
        while ((line = br.readLine()) != null) {
            // обрабатываем считанную строку - пишем ее в консоль
            System.out.println(line);
        }
        br.close();
    }

    private static void readUsingBufferedReader(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            // обрабатываем считанную строку - пишем ее в консоль
            System.out.println(line);
        }
        br.close();
        fr.close();
    }

    private static void readUsingScanner(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        //читаем построчно
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // обрабатываем считанную строку - пишем ее в консоль
            System.out.println(line);
        }
        //scanner.close();
    }

    private static void readUsingFiles(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        //1. считываем содержимое файла в массив байт, создав его предварительно
        byte[] bytes = Files.readAllBytes(path);
        System.out.println("Размер файла: " + bytes.length + " bytes");
        //2. считываем содержимое файла в список строк
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String string : allLines) {
            System.out.println(string);
        }
    }
}
