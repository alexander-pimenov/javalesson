package ru.pimalex1978.write;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteFile {
    public static void main(String[] args) {
        String data = "Это тестовая строка для записи в файл!";
        int noOfLines = 10000;

        //1. пишем с помощью FileWriter
//        writeUsingFileWriter(data);

        //2. пишем с помощью BufferedWriter
//        writeUsingBufferedWriter(data, noOfLines);

        //3. пишем с помощью Files
        writeUsingFiles(data);

        //4. пишем с помощью OutputStream
//        writeUsingOutputStream(data);
    }

    //1. Метод для записи в файл с помощью FileWriter
    private static void writeUsingFileWriter(String data) {
        String path = "FileWriterDemo.txt";
        final Path pathAbsolute = Paths.get(path).toAbsolutePath();
        System.out.println(pathAbsolute);
        File file = new File(path); //указываем куда записывать данные
//        File file = new File("c:/test/FileWriter.txt"); //указываем куда записывать данные
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(data); //записываем данные
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close(); //закрываем FileWriter
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //2. Метод для записи в файл с помощью BufferedWriter
    private static void writeUsingBufferedWriter(String data, int noOfLines) {
        String path = "BufferedWriterDemo.txt";
        File file = new File(path); //указываем куда записывать данные
//        File file = new File("c:/test/BufferedWriter.txt"); //указываем куда записывать данные
        FileWriter fw = null;
        BufferedWriter bw = null;
        String dataWithNewLine = data + System.getProperty("line.separator"); //("line.separator") - добавили новую строку
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            for (int i = noOfLines; i > 0; i--) {
                bw.write(dataWithNewLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close(); //закрываем BufferedWriter
                fw.close(); //закрываем FileWriter
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //3. Метод для записи в файл с помощью Files
    private static void writeUsingFiles(String data) {
        try {
            String workingDirectory = System.getProperty("user.dir");
            Files.write(Paths.get(workingDirectory, "FilesDemo.txt"), data.getBytes()); //указываем куда записывать данные
//            Files.write(Paths.get("c:/test/Files.txt"), data.getBytes()); //указываем куда записывать данные
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //4. Метод для записи в файл с помощью OutputStream
    private static void writeUsingOutputStream(String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File("OS_fileDemo.txt")); //указываем куда записывать данные
//            os = new FileOutputStream(new File("c:/test/OS_file.txt")); //указываем куда записывать данные
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close(); //закрываем FileOutputStream
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
