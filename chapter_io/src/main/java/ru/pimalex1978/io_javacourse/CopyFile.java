package ru.pimalex1978.io_javacourse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Пример программы копирования файлов.
 * <p>
 * Все достаточно просто — мы открыли два потока — один на чтение, другой на запись.
 * (FileInputStream и FileOutputStream). И потом в цикле мы читаем в переменную size массивы байтов
 * из входного потока, проверяем, что нам вернулся не “-1” (признак окончания) и пишем уже в выходной поток.
 * (причем тип переменной должен быть int). Опять же- закрытие наших файлов происходит автоматически.
 * <p>
 * Тут для ускорения работы
 * на помощь придет буферизованные версии чтения/записи.
 * Т.е. Вы считываете сразу несколько килобайт за один раз.
 * Это радикально повышает производительность. Для этого надо
 * использовать другой вариант вызова методов read и write
 * — он принимает в качестве входного параметра массив байтов.
 * Т.е. при чтении поток сразу заполняет весь массив (или его часть)
 * и делает операцию сразу для большого количества — читает или пишет.
 * <p>
 * Источник:
 * https://java-course.ru/begin/io_file/
 */
public class CopyFile {
    public static void main(String[] args) {
        copyFile("C:/test/testlog.txt", "C:/test/second_testlog.txt");
    }

    private static void copyFile(String source, String target) {

        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(target)) {

            byte[] buffer = new byte[8192];   // размер буфера 8 Кб
            int size = 0;
            while ((size = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, size);
            }

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
