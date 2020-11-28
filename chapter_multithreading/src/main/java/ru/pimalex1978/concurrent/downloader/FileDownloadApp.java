package ru.pimalex1978.concurrent.downloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownloadApp {
    private static final Logger LOG = LoggerFactory.getLogger(FileDownloadApp.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        LOG.info("Программа загрузки ресурса стартовала");
//        String file = args[0];
//        int size = Integer.parseInt(args[1]);
        String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        int size = 200;
        int fileSize = 0;
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp_1.xml")) {
            LOG.trace("Зашли в блок try-catch.");
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileSize = fileSize + bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (bytesRead > size) {
                    int secondsToPause = ((bytesRead / size) + 1) * 1000;
                    System.out.println(bytesRead + " [bytes]");
//                    System.out.println("-= " + secondsToPause + " =-");
                    Thread.sleep(secondsToPause);
                }
            }
        } catch (InterruptedException e) {
            LOG.error("Thread {} was interrupted.", Thread.currentThread().getName(), e);
//            LOG.error(e.getMessage(), e); //удобно искать ошибку в логе
//            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("Failed to read file {}.", file, e);
//            LOG.error(e.getMessage(), e); //удобно искать ошибку в логе
//            e.printStackTrace();
        }
        long passedTime = System.currentTimeMillis() - start;
        LOG.info("Программа закончена. Потрачено {} милли секунд", (passedTime));
        LOG.info("Загружен файл размером {} байт.", fileSize);
    }
}
