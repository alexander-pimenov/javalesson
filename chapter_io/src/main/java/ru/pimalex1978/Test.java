package ru.pimalex1978;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

public class Test {

    //Код не правильно работает. Пока не разбирался.
//    public static void main(String[] args) throws Exception {
//        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("."))) {
//            for (Path filePath : stream) {
//                String fileName = filePath.getFileName().toString();
//                System.out.println("processing file: name=" + fileName + ", size=" + Files.size(filePath) + " bytes");
//                Files.copy(filePath, Paths.get("income", fileName), StandardCopyOption.REPLACE_EXISTING);
//                Files.write(Paths.get("log.txt"), ("file processed: name=" + fileName
//                                + ",size=" + Files.size(filePath) + " bytes\n").getBytes(),
//                        StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
//            }
//        }
//    }

    public static void main(String[] args) {
        String s = " fkfkfk";
        String trim = s.trim();
        String[] s1 = trim.split(" ");
        if (s1.length == 0) {
            System.out.println("пусто");
        } else {
            for (int i = 0; i < s1.length; i++) {
                System.out.println(i + " " + s1[i]);
            }
            System.out.println("размер массива = " + s1.length);
        }

        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());
    }
}

//ввод:
//3 3 12
//DISABLE 1 2
//DISABLE 2 1
//DISABLE 3 3
//GETMAX
//RESET 1
//RESET 2
//DISABLE 1 2
//DISABLE 1 3
//DISABLE 2 2
//GETMAX
//RESET 3
//GETMIN

//вывод:
//1
//2
//1
