package ru.pimalex1978.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class ChannelExample {
    public static void main(String[] args) {
        fransferDemo();
    }

    /**
     * Метод копирования файла с помощью одного метода transferTo(position, count, target).
     * Т.е. мы записываем из одного канала в другой налету.
     * Из inChannel записываем данные в outChannel с позиции 0 до
     * позиции размера файла inChannel.size()
     * new FileOutputStream("...", true)) //true - информация будет дописываться в файл.
     */
    public static void fransferDemo() {
        try (FileInputStream fis = new FileInputStream("C:\\test\\FISHER - Losing It.mp3");
             FileOutputStream fos = new FileOutputStream("C:\\test\\TEST_NIO_FISHER - Losing It.mp3")) {

            FileChannel inChannel = fis.getChannel();
            FileChannel outChannel = fos.getChannel();

            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
