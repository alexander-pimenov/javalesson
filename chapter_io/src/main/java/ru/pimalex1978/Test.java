package ru.pimalex1978;

import java.io.IOException;
import java.nio.file.*;

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
}
