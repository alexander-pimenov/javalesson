package ru.pimalex1978.concurrent;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

/*
 * https://bugs.java.com/bugdatabase/view_bug.do?bug_id=6608965
 *
 * Если прерванный поток использует файловые каналы для каких-либо внутренних нужд,
 * тогда «Thread.interrupt()» работает аналогично «Thread.stop()» и опасен по
 * тем же причинам.
 *
 * Избегайте только «Thread.interrupt()», а также «Thread.stop()» для любых потоков,
 * кроме тех, в которых мы абсолютно уверены, что они не работают с файловыми каналами.
 * Чтобы остановить такие потоки, мы всегда должны использовать собственные изменчивые
 * флаги, которые не влияют на поведение подсистемы ввода-вывода Java.
 */
public class TestThreadInterrupted {
    public static void main(final String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " started...");
//        final String fileName = args[0];
        final String fileName = "C:\\projects\\javalessons\\resources\\source.txt";
        Thread thread = new Thread() {
            public void run() {
                try {
                    for (int k = 0; k < 10000000; k++) {
                        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
                        FileChannel fc = raf.getChannel();
                        // fc.map(FileChannel.MapMode.READ_ONLY, 0, raf.length()); // также привести к проблеме
                        final int read = fc.read(ByteBuffer.allocateDirect(1000000));
//                        System.out.println("Прочитано: "+ read + " байт.");
                        // - прервано здесь, а не во время следующей проверки "Thread.interrupted()"!
                        raf.close();
                        if (k % 100 == 0) {
                            // предположим, что правильное состояние для прерывания - это когда только k%100 == 0
                            if (Thread.interrupted()) {
                                System.out.println("\rINTERRUPTED!");
                                break;
                            }
                        }
                        System.out.print("\r" + k);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        Thread.sleep(5000);
        System.out.println("\rAttempt to interrupt..."); //Попытка прервать
        thread.interrupt();

        System.out.println(Thread.currentThread().getName() + " finished.");
    }
}
