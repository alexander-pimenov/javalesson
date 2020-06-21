package ru.pimalex1978.split;

import java.util.Arrays;

/**
 * Метод split с регулярным выражением.
 * Разбиение строки на слова только уже с помощью регулярного выражения.
 * Ниже представлен листинг кода,
 * который разбивает входную строку на слова по пробелам и запятыми.
 */
public class TestSplit3 {
    public static void main(String[] args) {

        // написали регулярку
        String pattern = "\\s+|,\\s*";

        // создали какую-то строку с разными разделителями
        String inputString = "Просто, строка в,     java";

        String[] splitResult = inputString.split(pattern);
        System.out.println(Arrays.toString(splitResult));
    }
}
