package pimalex1978.console_progress_bar;

import java.util.stream.Stream;

/**
 * <a href="https://m.vk.com/@javatutorial-kak-otobrazit-indikator-vypolneniya-na-standartnoi-konsoli-s">Как отобразить индикатор выполнения на стандартной консоли с помощью Java</a>
 */
public class ProgressBar {

    public static void main(String[] args) {
        printMsgWithProgressBar("Loading", 75, 100);
    }

    public static void printMsgWithProgressBar(String message, int length, long timeInterval) {
        char incomplete = '░'; // U+2591 Unicode Character
        char complete = '█'; // U+2588 Unicode Character
        StringBuilder builder = new StringBuilder();
        Stream.generate(() -> incomplete).limit(length).forEach(builder::append);
        System.out.println(message);
        for (int i = 0; i < length; i++) {
            builder.replace(i, i + 1, String.valueOf(complete));
            String progressBar = "\r" + builder;
            System.out.print(progressBar);
            try {
                Thread.sleep(timeInterval);
            } catch (InterruptedException ignored) {
            }
        }
    }

}
