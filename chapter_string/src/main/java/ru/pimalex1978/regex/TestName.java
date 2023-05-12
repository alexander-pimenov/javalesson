package ru.pimalex1978.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestName {
    public static void main(String[] args) {

        String text1 = "Егоров-Ольшанский";
        String text2 = "Алладан д'Артан";
        String text3 = "Аврора Агафо$нова-Ада";

        //регулярное выражение проверяющее, что в составных именах фамилиях нет символов указанных в
        //исключающем наборе, таких как: 0-9_!¡?÷?¿/\+=@#$%ˆ&*(){}|~<>;:[]
        String regEx = "^[\\w'\\-,.А-я][^0-9_!¡?÷?¿/\\\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]*$";
        Pattern pattern = Pattern.compile(regEx, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        Matcher matcher1 = pattern.matcher(text1);
        System.out.println(matcher1.matches());
        System.out.printf("Входная строка <<%s>>. Длина этой строки: %d%n", text1, text1.length());
        Matcher matcher2 = pattern.matcher(text2);
        System.out.println(matcher2.matches());
        System.out.printf("Входная строка <<%s>>. Длина этой строки: %d%n", text2, text2.length());
        Matcher matcher3 = pattern.matcher(text3);
        System.out.println(matcher3.matches());
        System.out.printf("Входная строка <<%s>>. Длина этой строки: %d%n", text3, text3.length());

        String input2 = "Hello Java! Hello JavaScript! JavaSE 8.0";
        System.out.printf("Разбиваем предложение <<%s>> по определенным символам%n",input2);
        //Это регулярное выражение разбивает строку по символам
        // !, ,, ., ? и пробелам. Также оно игнорирует лишние пробелы между словами.
        String regEx2 = "\\s*[!,.?]\\s*|\\s+";
        Pattern pattern2 = Pattern.compile(regEx2);
        String[] words = pattern2.split(input2);
        for (String word : words) {
            System.out.printf("%s - его длина %d%n", word, word.length());
        }
        System.out.println(Arrays.toString(words));

    }
}
