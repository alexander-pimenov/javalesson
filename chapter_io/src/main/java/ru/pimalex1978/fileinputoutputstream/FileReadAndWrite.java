package ru.pimalex1978.fileinputoutputstream;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Лучше читать данные не по 1 байту, а буфером в несколько байт 4096, 8192.
 * Файловая система работает блоками, читает и пишет блоками по 4 - 8 кБ.
 * Если файл вычитался и ничего нет, то вернется -1.
 *
 * new FileOutputStream("...", true)) //true - информация будет дописываться в файл.
 */
public class FileReadAndWrite {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("C:\\test\\FISHER - Losing It.mp3");
             FileOutputStream fos = new FileOutputStream("C:\\test\\TEST_IO_FISHER - Losing It.mp3")) {
            byte[] buffer = new byte[4096];
            int read = fis.read(buffer);
            while (read != -1) {
                fos.write(buffer, 0, read);
                read = fis.read(buffer);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
