package ru.pimalex1978.split;

import java.util.Arrays;

/**
 * Разделить строку на слова
 * Метод split(String regex) используется для расщепления строки на массив строк.
 * split(String regex, int numOfStrings) — перегруженная функция, которая
 * разделяет входную строку на определенное количество строк.
 * <p>
 * Метод split() — в Java разделяет данную строку вокруг
 * данного регулярного выражения и имеет два варианта.
 * Метод возвращает массив элементов разделенных выражением.
 * Поэтому массив можно перебрать в цикле или вызывать
 * нужный элемент массива.
 * <p>
 * Синтаксис метода:
 * <p>
 * public String[] split(String regex, int limit)
 * или
 * public String[] split(String regex)
 * <p>
 * Подробная информация о параметрах:
 * <p>
 * regex — разграничение регулярного выражения;
 * limit — порог, результатом которого означает, как много строк, должно быть возвращено.
 * Возвращаемое значение
 * В Java split() возвращает массив строк, вычисленных путем
 * разделения данной строки вокруг данного регулярного выражения.
 */
public class TestSplit2 {
    public static void main(String args[]) {
        String str = new String("Разделяем эту строку на слова");

        System.out.println("Возвращаемое значение: ");
        for (String retval : str.split(" ")) {
            System.out.println(retval);
        }
        System.out.println();
        System.out.println("Возвращаемое значение: ");
        for (String retval : str.split(" ", 2)) {
            System.out.println(retval);
        }
        System.out.println();

        System.out.println("Возвращаемое значение: ");
        String s0 = str.split(" ")[0];
        String s1 = str.split(" ")[1];
        String s4 = str.split(" ")[4];
        System.out.println(s0);
        System.out.println(s1);
        System.out.println(s4);

        //метод split со специальным разделителем
        System.out.println("Возвращаемое значение: ");
        String wordsSpecial = "Как|использовать|метод|split";
        String[] numbers = wordsSpecial.split("\\|");
        System.out.println("метод split со специальным разделителем: " + Arrays.toString(numbers));
        System.out.println(numbers[0]);
        System.out.println(numbers[3]);
    }
}
