package ru.pimalex1978.encoding;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * В примере в методе main первым параметром приходит имя файла,
 * тело которого в кодировке Windows-1251. В метод main вторым
 * параметром приходит имя файла, в который необходимо записать
 * содержимое первого файла в кодировке UTF-8.
 */
public class ReadAndWrite {
    private final static int BUFFER_SIZE = 1000;

    public static void main(String[] args) {
        try (final FileInputStream fileInputStream = new FileInputStream(args[0]);
             final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "windows-1251");
             final FileOutputStream fileOutputStream = new FileOutputStream(args[1]);
             final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8)) {
            final char[] buffer = new char[BUFFER_SIZE];
            int data;
            while ((data = inputStreamReader.read(buffer, 0, BUFFER_SIZE)) > 0)
                outputStreamWriter.write(buffer, 0, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

