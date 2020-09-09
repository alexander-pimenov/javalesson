package ru.pimalex1978;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //System.in - чтение данных из консоли. Читает байтовое представление.
        //System.out - вывод данных в консоль.
        //InputStream - поток для чтения байтов (поток ввода).
        //Reader - поток для чтения символов (поток ввода).
        //InputStreamReader - данный поток читает байты и авоматически преобразует их в символы (поток ввода).
        //Но эта конструкция InputStreamReader читает только ОДИН символ в строке (первый)!!! Не забывай.
        //Чтобы считать все символы используем BufferedReader!!! Т.е. буферное чтение.
        //Т.е. сначала считывает все символы в буфер, а после нажатия enter записывает в переменную.
        //Используем метод readLine() для чтения символов построчно. В строку String.

        System.out.println("Введите символ или символы: \r");
//        final int data = System.in.read(); //вводим в консоли любой символ: f
//        System.out.println(data); //получаем код символа: 102
//        System.out.println((char) data); //получаем сам символ: f

        /*Латинские буквы представленны в виде одного байта.
         * f -> 102 -> f
         * Любой символ кириллицы представлен двумя байтами.
         * у -> 209 -> Ñ здесь мы видим преобразование только первого байта
         * из двух.*/

        /*Посмотрим на все байты представления символов.
         * 10 - перевод строки.
         * у -> 209 -> Ñ  131 -> f
         * ъ -> 209 -> Ñ  138 -> */

//        while (true) {
//            final int data2 = System.in.read();
//            System.out.println(data2);
//            System.out.println((char) data2);
//        }

//        InputStreamReader streamReader = new InputStreamReader(System.in);
////        System.out.println(streamReader.read());
//        System.out.println((char)streamReader.read());

        System.out.println("===Введите любую строчку===");
        String s = reader.readLine();
        System.out.println(s);

        System.out.println("===Введите 1-е число===");
        int num1 = Integer.parseInt(reader.readLine());
        System.out.println("===Введите 2-е число===");
        int num2 = Integer.parseInt(reader.readLine());
        System.out.println("===Сумма чисел===");
        System.out.println(sum(num1, num2));
    }

    private static int sum(int number1, int number2) {
        int res = number1 + number2;
        return res;
    }
}
