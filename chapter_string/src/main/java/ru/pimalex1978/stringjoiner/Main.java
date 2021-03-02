package ru.pimalex1978.stringjoiner;

import java.util.StringJoiner;

/**
 * Класс StringJoiner используется, чтобы создать последовательность строк, разделенных разделителем
 * с возможностью присоединить к полученной строке префикс и суффикс
 */
public class Main {
    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner(".", "prefix-", "-suffix");
        for (String s : "Hello the brave world".split(" ")) {
            joiner.add(s);
        }
        System.out.println(joiner); //prefix-Hello.the.brave.world-suffix
    }
}
