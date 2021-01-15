package ru.pimalex1978;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Вэтом приложении рассматриваем пример, как заливать
 * файлы в Dropbox.
 * <p>
 * Делаем это с помощью библиотеки Dropbox.
 * Берем её на GitHub.
 * Полный пример кода для подключения к Dropbox показан по ссылке
 * https://github.com/dropbox/dropbox-sdk-java  (Full Example Snippet)
 * <p>
 * Переходим по ссылке https://github.com/dropbox/dropbox-sdk-java/releases/tag/v3.1.1
 * Два способа подключения библиотека Dropbox.
 * 1-й способ:
 * Скачиваем файл "dropbox-core-sdk-3.1.1.zip", разархивируем и из пакета берем файл "dropbox-core-sdk-3.1.1.jar"
 * и в этом же пакете, но в папке "lib" берем файл "jackson-core-2.7.4.jar".
 * Это две библиотеки с кодом на Java, которые позволят нам подключиться к Dropbox.
 * Нужно в корне проекта создать папку lib (библиотека) и эту папку положить эти два файла.
 * (Это все делаем через IntelliJ IDEA: создаем папку lib (выбираем New->Directory->lib),
 * с компьтера в нее перетаскиваем нужные нам два файла)
 * Затем выделяем эти файлы, кликаем правой клавишей мыши и выбираем "Add as Library...", т.е. подключаем их
 * к проекту. Появится окошко "Create Library", кликаем ОК. И все, подключение произошло.
 * <p>
 * 2-й способ:
 * Т.к. мы используем Maven, то редактируем «pom.xml» нашего проекта и добавляем код в раздел <dependencies>:
 * <dependency>
 * <groupId>com.dropbox.core</groupId>
 * <artifactId>dropbox-core-sdk</artifactId>
 * <version>3.1.1</version>
 * </dependency>
 * И в этом наше преимущество, т.к. Maven самостоятельно подтягивает все нужные ему зависимости в External Libraries.
 * <p>
 * Далее возвращаемся на страничку github Dropbox (https://github.com/dropbox/dropbox-sdk-java) и выбираем раздел
 * Link an account. Копируем в наш код.
 * Далее переходим к "Try some API requests"->"Try uploading a file to your Dropbox." И также копируем код и
 * вставляем к себе в программу. Но немного его переписываем. Это видно по коду в программе.
 * <p>
 * Это программа, когда мы ее запускаем, теперь заливает файл в Dropbox. Проверь!
 * Можно вставить код, который делает скиншоты экрана с задержкой, например, 1 час, также заливаем его на Dropbox и
 * мы смом увидеть, чем занимались весь день. Удачи.
 */

public class Main {
    private static final String ACCESS_TOKEN = "Ti9y-OZ_2zUAAAAAAAADbS664Nd-eGmMVoT-orOT2gbACRA5lqs-jyEvyUAFxnuB";

    public static void main(String[] args) throws DbxException {

        // Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Upload "test.txt" to Dropbox
//        try (InputStream in = new FileInputStream("test.txt")) {
//            FileMetadata metadata = client.files().uploadBuilder("/test.txt")
//                    .uploadAndFinish(in);
//        }

        /*Перепишем немного код из github.
        Создаем некий InputStream, где в скобках указываем имя файла. Создаем его где нам необходимо.
        Копируем его путь в параметр new FileInputStream(...)*/
        try {
            InputStream in = new FileInputStream("C:\\Users\\Alex\\Downloads\\Skillbox intensive\\3.2_4.jpg");

            //Ниже код означает, что мы заливаем файл в корень под именем "/1.jpg", т.е. переименуем наш файл
            // из "3.2_4.jpg" в ""/1.jpg" ("/"- означает в корень).
            client.files().uploadBuilder("/5.jpg").uploadAndFinish(in);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

/*
        //4. Написать код получения скриншота экрана, который распечатает размеры экрана
        //Обернули в try/catch, что бы не выбрасывать исключение в main метод.
        BufferedImage screenCapture = null;
        try {
            screenCapture = new Robot()
                    .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

            ImageIO.write(screenCapture, "JPG", new File("screenShot.jpg"));
        } catch (AWTException | IOException e) {
            System.err.println("Something go wrong: " + e.getMessage());
            e.printStackTrace();
        }
        if (screenCapture != null) {
            System.out.println(screenCapture.getWidth() + "x" + screenCapture.getHeight());
        }

        //3. Нужно сделать так, чтобы дата выводилась в формате: 20191205_214218
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        System.out.println(dateFormat.format(new Date()));
*/
