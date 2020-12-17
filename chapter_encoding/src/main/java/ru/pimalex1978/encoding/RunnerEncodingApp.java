package ru.pimalex1978.encoding;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/*
 * Из примера видно, что вызывая разные методы определения кодировки, мы получаем
 * разные названия кодировки. Т.е. как и говорилось в каком то источнике, такое
 * возможно.
 */
public class RunnerEncodingApp {
    public static void main(String[] args) {
        String path = "C:\\test\\file for read.txt";
        try {
            String encoding = System.getProperty("file.encoding");
            System.out.println("1: System encoding = " + encoding);
            String data = FileUtils.readFile(path);

            Charset charset = FileUtils.guessCharset2(new File(path));
            System.out.println("2: " + charset);

            Charset charset1 = FileUtils.guessCharset(new FileInputStream(new File(path)));
            System.out.println("3: " + charset1);

            String stringCharset = FileUtils.guessCharset3(new File(path));
            System.out.println("4 " + stringCharset);

            String charset5 = FileUtils.guessCharset4(new File(path));
            System.out.println("5: " + charset5);

            String charset6 = FileUtils.guessCharset5(new File(path));
            System.out.println("6: " + charset6);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
