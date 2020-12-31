package ru.pimalex1978.write;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFile {
    public static void main(String[] args) {

        String fileName = "Test.txt";
        String dirName = "Test_results";
        String dirNameSeveral = "Test/A/B/C";

        try {

            Path dir = createDirectories1(dirNameSeveral);

            System.out.println(dir);

            //СОЗДАЕМ ПУТЬ К ФАЙЛУ С УКАЗАНИЕМ САМОГО ФАЙЛА
            String pathString = dir.toString() + File.separator + fileName;
            createFile3(pathString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создания файла в корне рабочего проекта (user.dir)!!!
     *
     * @param fileName название файла
     * @throws IOException исключение
     */
    private static void createFile1(String fileName) throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        File file = new File(workingDirectory, fileName);
        System.out.println("Final filepath: " + file.getAbsolutePath());
        if (file.createNewFile()) {
            System.out.println("File is created!");
        } else {
            System.out.println("File is already existed!");
        }
    }

    /**
     * Метод создания файла в корне рабочего проекта (user.dir)!!!
     *
     * @param fileName название файла
     * @throws IOException исключение
     */
    private static void createFile2(String fileName) throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        String absolutePath = workingDirectory + File.separator + fileName;
        System.out.println("Final filepath: " + absolutePath);
        File file = new File(absolutePath);
        if (file.createNewFile()) {
            System.out.println("File is created!");
        } else {
            System.out.println("File is already existed!");
        }
    }

    private static void createFile3(String fileName) throws IOException {
        Files.createFile(Paths.get(fileName));
    }

    /**
     * Метод создания директории.
     * Возможно создать цепочку вложенных директорий:
     * A/B/C
     *
     * @param dirName название директории
     * @return путь к директории
     * @throws IOException иксепшен
     */
    private static Path createDirectories1(String dirName) throws IOException {

        return Files.createDirectories(Paths.get(dirName));
    }

}
