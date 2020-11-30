package ru.pimalex1978.download;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * https://stackoverflow.com/questions/1605332/java-nio-filechannel-versus-fileoutputstream-performance-usefulness
 * <p>
 * Я пытаюсь выяснить, есть ли разница в производительности (или преимуществах),
 * когда мы используем nio по FileChannel сравнению с обычным
 * FileInputStream/FileOutputStream для чтения и записи файлов в файловую систему.
 * Я заметил, что на моей машине оба работают на одном уровне, а также во много
 * раз FileChannel медленнее. Могу я узнать более подробную информацию о
 * сравнении этих двух методов. Вот код, который я использовал, файл, с
 * которым я тестирую, есть 350MB.
 * <p>
 * Выполните несколько тестов с буферами
 * 1 КБ, 2 КБ, 4 КБ, 8 КБ, 16 КБ, 32 КБ и 64 КБ, чтобы убедиться в этом сами.
 */
public class JavaIOandNIOTest {
    public static void main(String[] args) throws Exception {
        useNormalIO();
        useNormalIOBuffered();
        useFileChannel();
        useNIO();
    }

    private static void useNormalIO() throws Exception {
        File src = new File("/home/developer/test.iso");
        File dest = new File("/home/developer/test2");

        long time1 = System.currentTimeMillis();
        InputStream is = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        byte[] buf = new byte[64 * 1024];
        int len = 0;
        while ((len = is.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fos.flush();
        fos.close();
        is.close();

        long time2 = System.currentTimeMillis();
        System.out.println("Time taken: " + (time2 - time1) + " ms");
    }

    private static void useNormalIOBuffered() throws Exception {
        File src = new File("/home/developer/test.iso");
        File dest = new File("/home/developer/test2");

        long time1 = System.currentTimeMillis();

        String encoding = "UTF-8";
        int maxlines = 100;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(src), encoding));
            int count = 0;
            for (String line; (line = reader.readLine()) != null; ) {
                if (count++ % maxlines == 0) {
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/smallfile" + (count / maxlines) + ".txt"), encoding));
                }
                writer.write(line);
                writer.newLine();
            }
        } finally {
            writer.close();
            reader.close();
        }

        writer.flush();
        writer.close();
        reader.close();

        long time2 = System.currentTimeMillis();
        System.out.println("Time taken: " + (time2 - time1) + " ms");
    }

    private static void useFileChannel() throws Exception {
        File src = new File("/home/developer/test.iso");
        File dest = new File("/home/developer/test2");

        long time1 = System.currentTimeMillis();
        FileInputStream is = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        FileChannel inChannel = is.getChannel();
        FileChannel outChannel = fos.getChannel();

        ByteBuffer buf = ByteBuffer.allocateDirect(64 * 1024);
        long len = 0;
        while ((len = inChannel.read(buf)) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        inChannel.close();
        outChannel.close();

        long time2 = System.currentTimeMillis();
        System.out.println("Time taken: " + (time2 - time1) + " ms");
    }

    private static void useNIO() throws Exception {
        File src = new File("/home/developer/test.iso");
        File dest = new File("/home/developer/test2");

        long time1 = System.currentTimeMillis();
        final FileInputStream inputStream = new FileInputStream(src);
        final FileOutputStream outputStream = new FileOutputStream(dest);
        final FileChannel inChannel = inputStream.getChannel();
        final FileChannel outChannel = outputStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inChannel.close();
        outChannel.close();
        inputStream.close();
        outputStream.close();

        long time2 = System.currentTimeMillis();
        System.out.println("Time taken: " + (time2 - time1) + " ms");
    }
}