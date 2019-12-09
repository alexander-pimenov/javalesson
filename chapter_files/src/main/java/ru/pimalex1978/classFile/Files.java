package ru.pimalex1978.classFile;

import java.io.*;

public class Files {
    /**
     * Создадим обьект типа File
     * соответствующий нашему текущему каталогу.
     * в качестве имени задаем (".") - это принятое обозначение текущего каталога
     * для того чтобы посмотреть содержание каталога используем метод list() -
     * возвращает массив строк
     */

    public static void main(String[] args) throws FileNotFoundException, IOException {

        //Посмотрим, какие файлы и директории лежат в корне нашего проекта. - "."
        File dir = new File(".");

        //для того чтобы посмотреть содержание каталога используем метод list()
        String[] names = dir.list();
        //выведем это содержимое через цикл
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
        System.out.println("====================");

        //Другое задание, возмем какой нибудь файл, указав полный адрес
        File file = new File("c:\\test\\textTest.txt");

        //Если файла нет, то создадим его и что-нибудь запишем в него.
        if (!file.exists()) {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            String line = "Всем привет! \nЖелаю хорошего настроения и успехов в делах!\n";
            fw.write(line);
            fw.close();
        }

        //узнаем его длину. length() возращет тип лонг. выведем на экран
        System.out.println("размер файла textTest.txt = " + file.length());

        //прочитаем что нибудь из этого файла. используем FileInputStream, создав его объект
        /*FileInputStream может выбросить исключения, поэтому указываем его FileNotFoundException в методе psvm*/
        FileInputStream input = new FileInputStream(file);

        //командой available() узнаем сколько байт доступно для чтения
        int length = input.available(); //available() тоже выбрасывает исключения IOException
        System.out.println("доступно для чтения - " + length);

        /*прочитаем какие-нибудь данные. создадим массив byte в который эти данные будем читать
         * длину массива узнали выше - length*/
        byte[] data = new byte[length];
        //используем метод read() и указываем ему тот массив в который нужно прочитать данные
        input.read(data);

//        //выведем на экран все значения из этого массива с помощью цикла, будут разные цифры
//        for (int i=0; i<data.length; i++){
//            System.out.println(data[i]); // вывелось множество различных цифр
//        }

        //обличем эти цифры в Строку, т.е. преобрауем байтовый массив в текст
        String text = new String(data);

        //Печатаем в консоль содержимое файла.
        System.out.println(text);

        /**
         * Рассмотрим пример записи в файл. для этого создадим объект FileOutputStream
         * и в качестве параметра передадим ему наш file.
         * но чтобы мы добавили запись, а не перезаписали новым содержимым
         * добавим в параметр "true" !!!
         */
        FileOutputStream output = new FileOutputStream(file, true);
        //если append: true, то файл не переписывается, а дополняется

        //запишем туда строку newText
        String newText = "<!-- Hello world! -->\n";
        //преобразуем newText в байтовый массив для вставки в file
        byte[] newTextBytes = newText.getBytes(); // метод getBytes() возвращает массив байтов

        /*берем объект output, который соответствует файлу files и записываем туда
         * получившийся новый массив newTextBytes*/
        output.write(newTextBytes);

        //не ЗАБУДЕМ закрыть файл
        output.close();
        //в файле file появилась запись в конце  "<!-- Hello world! -->" и каждый раз, как запускаем программу
        // эта надпись добавляется снова))))

    }
}
