package ru.pimalex1978.split;

/**
 * Метод split() — в Java разделяет данную строку вокруг данного
 * регулярного выражения и имеет два варианта.
 * Возвращаемое значение:
 * В Java split() возвращает массив строк, вычисленных
 * путем разделения данной строки вокруг данного регулярного выражения.
 */

public class TestSplit {
    public static void main(String args[]) {
        String str = new String("Добро-пожаловать-на-ProgLang.su");

        System.out.println("Возвращаемое значение: ");
        for (String retval : str.split("-", 1)) {
            System.out.println(retval);
        }
        System.out.println();
        System.out.println("Возвращаемое значение: ");
        for (String retval : str.split("-", 2)) {
            System.out.println(retval);
        }
        System.out.println();
        System.out.println("Возвращаемое значение: ");
        for (String retval : str.split("-", 3)) {
            System.out.println(retval);
        }
        System.out.println();
        System.out.println("Возвращаемое значение: ");
        for (String retval : str.split("-", 0)) {
            System.out.println(retval);
        }
        System.out.println();
        System.out.println("Возвращаемое значение: ");
        for (String retval : str.split("-")) {
            System.out.println(retval);
        }
    }
}
