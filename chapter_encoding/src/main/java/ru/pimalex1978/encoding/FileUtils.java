package ru.pimalex1978.encoding;

import com.glaforge.i18n.io.CharsetToolkit;
import org.apache.any23.encoding.TikaEncodingDetector;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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


}
