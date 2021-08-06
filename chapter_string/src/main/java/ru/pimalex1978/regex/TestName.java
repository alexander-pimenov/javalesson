package ru.pimalex1978.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestName {
    public static void main(String[] args) {

        String text1 = "Егоров-Ольшанский";
        String text2 = "Алладан д'Артан";
        String text3 = "Аврора Агафо$нова-Ада";

        //регулярное выражение проверяющее что в составных именах фамилиях нет символов указанных в
        //исключающем наборе, таких как: 0-9_!¡?÷?¿/\+=@#$%ˆ&*(){}|~<>;:[]
        String regEx = "^[\\w'\\-,.А-я][^0-9_!¡?÷?¿/\\\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]*$";
        Pattern pattern = Pattern.compile(regEx, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        Matcher matcher1 = pattern.matcher(text1);
        System.out.println(matcher1.matches());
        Matcher matcher2 = pattern.matcher(text2);
        System.out.println(matcher2.matches());
        Matcher matcher3 = pattern.matcher(text3);
        System.out.println(matcher3.matches());
        System.out.println("Длина строки: " + text1.length());
        System.out.println("Длина строки: " + text2.length());
        System.out.println("Длина строки: " + text3.length());

    }
}
