package ru.pimalex1978.duplicate.tests;

/**
 * Простая задача "Что выведется в консоль?"
 * Раскомментируй код и посмотри.
 */

public class TestFloat {
    public static void main(String[] args) {

        /*если оставить эту переменную Float f, то получим ошибку
         * компиляции "несовместимый тип".
         * У нас есть тип Float, а требуется char, byte, short, int,
         * Character, Byte, Short, Integer, String or enum*/
        //Float f = new Float("10");

        var f = 10;

        //если не указть break, то процесс пройдет через все case и выведет "Ten" "Zero" "Default"
        switch (f) {
            case 10:
                System.out.println("Ten");
                //break;
            case 0:
                System.out.println("Zero");
                //break;
            default:
                System.out.println("Default");
        }
    }
}
