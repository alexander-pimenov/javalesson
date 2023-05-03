package ru.pimalex1978.download;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * <a href="https://javadevblog.com/kak-skachat-fajl-v-java.html">kak-skachat-fajl-v-java</a>
 */


public class DownloadFileFromURL {
    public static void main(String[] args) {

        // будем качать карту сайта моего сайта - в вашем случае замените ссылку на свою
        String url = "https://javadevblog.com/sitemap.xml";
        //или файл pom.xml по указанному url
        String urlFile = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try {

            //качаем файл с помощью NIO
            downloadUsingNIO(url, "c:/test/sitemap_nio.xml");

            //качаем файл с помощью Stream
            downloadUsingStream(url, "c:/test/sitemap_stream.xml");

            //качаем файл с помощью Stream
            downloadUsingStream_2(urlFile, "pom_tmp02.xml");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //метод для качания файла с помощью Stream (Java IO InputStream)
    private static void downloadUsingStream(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fos.write(buffer, 0, count);
        }
        fos.close();
        bis.close();
    }

    public static void downloadUsingStream_2(String urlFile, String targetFilePath) throws Exception {
        try (BufferedInputStream in = new BufferedInputStream(new URL(urlFile).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(targetFilePath)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                System.out.println(bytesRead);
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //метод для качания файла с помощью NIO (Java NIO Channels)
    private static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
}
