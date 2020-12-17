package ru.pimalex1978.encoding;

import com.glaforge.i18n.io.CharsetToolkit;
import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import org.apache.any23.encoding.TikaEncodingDetector;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * https://ericbt.com/Blog/60
 * Как прочитать файл и автоматически указать правильную кодировку.
 * <p>
 * По умолчанию Java читает текстовые файлы, используя кодировку по умолчанию.
 * Если вам известна кодировка файла, ее можно указать в конструкторе объекта
 * FileInputStream, который используется для чтения файла. Следующий класс
 * автоматизирует процесс чтения файлов, используя правильную кодировку, при
 * условии, что кодировка файла либо UTF-8, UTF-16 (big or little endian -
 * прямой или большой порядок байтов), либо система по умолчанию.
 * <p>
 * Это может быть бесполезно, потому что только очень немногие файлы имеют спецификацию.
 * И UTF-8 с BOM не рекомендуется.
 * Лучше использовать библиотеку вроде GuessEncoding или juniversalchardet.
 * https://askdev.ru/q/java-kak-opredelit-pravilnuyu-kodirovku-kodirovki-potoka-18612/
 */
public class FileUtils {
    /***
     * Determines the encoding of the specified file. If a UTF16 Byte Order Mark (BOM)
     * is found an encoding of "UTF16" is returned.
     * If a UTF8 BOM is found an encoding of "UTF8" is returned. Otherwise
     * the default encoding is returned.
     *
     * Определяет кодировку указанного файла. Если обнаружена метка порядка байтов
     * (BOM) UTF16, возвращается кодировка «UTF16». Если обнаружена спецификация
     * UTF8, возвращается кодировка «UTF8».
     * В противном случае возвращается кодировка по умолчанию.
     *
     * @param filePath file path
     * @return "UTF8", "UTF16", or default encoding.
     */
    private static String getEncoding(String filePath) {
        String encoding = System.getProperty("file.encoding");

        BufferedReader bufferedReader = null;

        try {
            // In order to read files with non-default encoding, specify an encoding in the FileInputStream constructor.
            //Чтобы читать файлы с кодировкой, отличной от кодировки по умолчанию, укажите кодировку в конструкторе FileInputStream.
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));

            char[] buffer = new char[3];
            int length = bufferedReader.read(buffer);

            if (length >= 2) {
                if ((buffer[0] == (char) 0xff && buffer[1] == (char) 0xfe) /* UTF-16, little endian */
                        || (buffer[0] == (char) 0xfe && buffer[1] == (char) 0xff) /* UTF-16, big endian */) {
                    encoding = "UTF16";
                }
            }
            if (length >= 3) {
                if (buffer[0] == (char) 0xef && buffer[1] == (char) 0xbb
                        && buffer[2] == (char) 0xbf) /* UTF-8 */ {
                    encoding = "UTF8";
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return encoding;
    }

    /***
     * Returns the text of the specified file. If a Unicode Byte Order Mark (BOM)
     * is found, the file is read with the corresponding encoding.
     * Otherwise the file is read using the default encoding.
     *
     * Возвращает текст указанного файла. Если обнаружена метка порядка байтов
     * Unicode (BOM), файл читается с соответствующей кодировкой. В противном
     * случае файл читается с использованием кодировки по умолчанию.
     *
     * @param filePath file path
     * @return text of file
     * @throws IOException Exception
     */
    public static String readFile(String filePath) throws IOException {
        String encoding = getEncoding(filePath);
        //Выведем информацию о кодировке в консоль.
        //можно закомментировать эту строчку.
        System.out.printf("Encoding this \'%s\' file is: %s \r\n", filePath, encoding);

        BufferedReader bufferedReader = null;

        StringBuffer text = new StringBuffer();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), encoding));

            char[] buffer = new char[1024 * 16];
            int length;

            while ((length = bufferedReader.read(buffer)) != -1) {
                text.append(buffer, 0, length);
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return text.toString();
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

        String charset = null;

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

    // * in simple Java (просто подставляем разные кодировки, но определить можно
    // только дефолтную кодировку для виртуальной машины.)
    public static String guessCharset5(File file) throws IOException {
        final String[] encodings = {"US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16"};

        List<String> lines;
        Charset charset = null;

        for (String encoding : encodings) {
            try {
                //defaultCharset() - Возвращает кодировку по умолчанию для этой виртуальной машины Java.
                charset = Charset.defaultCharset();
                System.out.println("Default Charset encoding: " + charset);
                lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.forName(encoding));
                for (String line : lines) {
                    // do something...
//                    System.out.println(line);
                }
                break;
            } catch (IOException ioe) {
                System.out.println(encoding + " failed, trying next.");
            }
        }
        return charset.toString();
    }


}
