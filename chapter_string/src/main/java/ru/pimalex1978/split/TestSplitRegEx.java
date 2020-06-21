package ru.pimalex1978.split;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Пример из статьи
 * https://javarush.ru/groups/posts/regulyarnye-vyrazheniya-v-java
 * Из примера видно, что методы replaceFirst и replaceAll создают
 * новый объект String – строку, представляющую собой исходный текст,
 * в котором совпадения с шаблоном заменены на текст, переданный
 * методу в качестве аргумента. Причём метод replaceFirst заменяет
 * только первое совпадение, а replaceAll – все совпадения в тесте.
 * Исходный текст остается без изменений.
 *
 * Если вам нужно заменить текст или сравнить строки в программе
 * без написания лишнего кода, используйте методы класса String.
 * Если же вам нужны расширенные возможности –
 * вспомните о классах Pattern и Matcher.
 */

public class TestSplitRegEx {
    public static void main(String[] args) {
        String text = "Егор Алла Анна Аврора Агафон Петра Ася Антон Ада";
        Pattern pattern = Pattern.compile("А.+?а");

        Matcher matcher = pattern.matcher(text);
        System.out.println("Длина строки: " + text.length());
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            System.out.println("Найдено совпадение " + text.substring(start, end) + " с " + start + " по " + (end - 1) + " позицию");
        }
        System.out.println(matcher.replaceFirst("Ира"));
        System.out.println(matcher.replaceAll("Ольга"));
        System.out.println(text);
    }
}

//Длина строки: 48
//Найдено совпадение Алла с 5 по 8 позицию
//Найдено совпадение Анна с 10 по 13 позицию
//Найдено совпадение Аврора с 15 по 20 позицию
//Найдено совпадение Ага с 22 по 24 позицию
//Найдено совпадение Ася Антон Ада с 35 по 47 позицию
//Егор Ира Анна Аврора Агафон Петра Ася Антон Ада
//Егор Ольга Ольга Ольга Ольгафон Петра Ольга
//Егор Алла Анна Аврора Агафон Петра Ася Антон Ада