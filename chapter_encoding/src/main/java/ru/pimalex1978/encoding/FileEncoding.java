package ru.pimalex1978.encoding;

import com.glaforge.i18n.io.CharsetToolkit;
import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import org.apache.any23.encoding.TikaEncodingDetector;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * https://askdev.ru/q/java-kak-opredelit-pravilnuyu-kodirovku-kodirovki-potoka-18612/
 * <p>
 * Какую библиотеку использовать?
 * На момент написания этой статьи они представляют собой три библиотеки, которые появляются:
 * <p>
 * GuessEncoding
 * ICU4j
 * juniversalchardet
 * Я не включаю Apache Any23 потому что он использует ICU4j 3.4 под капотом.
 * <p>
 * Как сказать, какой из них обнаружил право charset (или так близко, как возможно)?
 * невозможно подтвердить кодировку, обнаруженную каждой из вышеперечисленных библиотек.
 * Тем не менее, можно спросить их по очереди и набрать возвращенный ответ.
 * <p>
 * Как забить возвращенный ответ?
 * каждому ответу может быть присвоена одна точка. Чем больше точек имеет ответ, тем
 * больше уверенности имеет обнаруженная кодировка. Это простой метод подсчета очков.
 * Вы можете разработать другие.
 * <p>
 * есть ли пример кода?
 * здесь полный фрагмент, реализующий стратегию, описанную в предыдущих строках.
 * <p>
 * The guessEncoding метод полностью считывает inputstream. Для больших inputstreams
 * это может быть проблемой. Все эти библиотеки будут читать весь inputstream.
 * Это потребует больших затрат времени на обнаружение кодировки.
 * <p>
 * можно ограничить начальную загрузку данных несколькими байтами и выполнить
 * обнаружение кодировки на этих нескольких байтах только.
 */
public class FileEncoding {
    public static String guessEncoding(InputStream input) throws IOException {
        // Load input data - Загрузить входные данные
        long count = 0;
        int n = 0, EOF = -1;
        byte[] buffer = new byte[4096];
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        while ((EOF != (n = input.read(buffer))) && (count <= Integer.MAX_VALUE)) {
            output.write(buffer, 0, n);
            count += n;
        }

        if (count > Integer.MAX_VALUE) {
            throw new RuntimeException("Inputstream too large.");
        }

        byte[] data = output.toByteArray();

        // Detect encoding - Обнаружить кодировку
        Map<String, int[]> encodingsScores = new HashMap<>();

        // * GuessEncoding
        updateEncodingsScores(encodingsScores, new CharsetToolkit(data).guessEncoding().displayName());

        // * ICU4j
        CharsetDetector charsetDetector = new CharsetDetector();
        charsetDetector.setText(data);
        charsetDetector.enableInputFilter(true);
        CharsetMatch cm = charsetDetector.detect();
        if (cm != null) {
            updateEncodingsScores(encodingsScores, cm.getName());
        }

        // * juniversalchardset
        UniversalDetector universalDetector = new UniversalDetector(null);
        universalDetector.handleData(data, 0, data.length);
        universalDetector.dataEnd();
        String encodingName = universalDetector.getDetectedCharset();
        if (encodingName != null) {
            updateEncodingsScores(encodingsScores, encodingName);
        }

        // Find winning encoding - Найдите выигрышную кодировку
        Map.Entry<String, int[]> maxEntry = null;
        for (Map.Entry<String, int[]> e : encodingsScores.entrySet()) {
            if (maxEntry == null || (e.getValue()[0] > maxEntry.getValue()[0])) {
                maxEntry = e;
            }
        }

        String winningEncoding = maxEntry.getKey();
        //dumpEncodingsScores(encodingsScores);
        return winningEncoding;
    }

    private static void updateEncodingsScores(Map<String, int[]> encodingsScores, String encoding) {
        String encodingName = encoding.toLowerCase();
        int[] encodingScore = encodingsScores.get(encodingName);

        if (encodingScore == null) {
            encodingsScores.put(encodingName, new int[]{1});
        } else {
            encodingScore[0]++;
        }
    }

    private static void dumpEncodingsScores(Map<String, int[]> encodingsScores) {
        System.out.println(toString(encodingsScores));
    }

    private static String toString(Map<String, int[]> encodingsScores) {
        String GLUE = ", ";
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, int[]> e : encodingsScores.entrySet()) {
            sb.append(e.getKey() + ":" + e.getValue()[0] + GLUE);
        }
        int len = sb.length();
        sb.delete(len - GLUE.length(), len);

        return "{ " + sb.toString() + " }";
    }

    // * TikaEncodingDetector
    public static Charset guessCharset(InputStream is) throws IOException {
        return Charset.forName(new TikaEncodingDetector().guessEncoding(is));
    }

    // * GuessEncoding
    public static Charset guessCharset2(File file) throws IOException {
        return CharsetToolkit.guessEncoding(file, 4096, StandardCharsets.UTF_8);
    }

    // * Universal Detector
    /*Как его использовать.
     * 1) Создайте экземпляр org.mozilla.universalchardet.UniversalDetector.
     * 2) Передайте некоторые данные (обычно несколько тысяч байтов) в детектор,
     * вызвав UniversalDetector.handleData ().
     * 3) Сообщите детектору об окончании данных, вызвав UniversalDetector.dataEnd ().
     * 4) Получите обнаруженное имя кодировки, вызвав UniversalDetector.getDetectedCharset ().
     * 5) Не забудьте вызвать UniversalDetector.reset() перед повторным использованием
     * экземпляра детектора.*/
    public static String guessCharset3(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[4096];
        //(1)
        UniversalDetector detector = new UniversalDetector(null);
        //(2)
        int nread;
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        //(3)
        detector.dataEnd();
        //(4)
        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            System.out.println("Detected encoding = " + encoding); //Обнаруженная кодировка
        } else {
            System.out.println("No encoding detected."); //Кодировка не обнаружена
        }
        //(5)
        detector.reset();
        return encoding;
    }

    // * ICU4j
    public static String guessCharset4(File file) throws IOException {
        String charset = "ISO-8859-1"; //Default charset, put whatever you want
        byte[] fileContent = null;
        FileInputStream fin = null;
        //create FileInputStream object
        fin = new FileInputStream(file.getPath());

        /*
         * Create byte array large enough to hold the content of the file.
         * Use File.length to determine size of the file in bytes.
         */
        fileContent = new byte[(int) file.length()];

        /*
         * To read content of the file in byte array, use
         * int read(byte[] byteArray) method of java FileInputStream class.
         *
         */
        fin.read(fileContent);

        byte[] data = fileContent;


        CharsetDetector detector = new CharsetDetector();
        detector.setText(data);

        CharsetMatch cm = detector.detect();

        if (cm != null) {
            int confidence = cm.getConfidence();
            System.out.println("Encoding: " + cm.getName() + " - Confidence: " + confidence + "%");
            charset = cm.getName();
            //Here you have the encode name and the confidence
            //In my case if the confidence is > 50 I return the encode, else I return the default value
            if (confidence > 50) {
                charset = cm.getName();
            }
        }
        return charset;
    }
}

// * ICU4j
//BufferedInputStream bis = new BufferedInputStream(input);
//CharsetDetector cd = new CharsetDetector();
//cd.setText(bis);
//CharsetMatch cm = cd.detect();
//
//if (cm != null) {
//   reader = cm.getReader();
//   charset = cm.getName();
//}else {
//   throw new UnsupportedCharsetException()
//}
