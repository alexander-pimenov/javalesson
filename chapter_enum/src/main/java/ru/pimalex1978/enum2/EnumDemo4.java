package ru.pimalex1978.enum2;

/**
 * Продемонстрировать применение методов ordinal(), compareTo(), equals().
 */

//перечисление сортов яблок
enum Apple4 {
    Jonatan, GoldenDel, RedDel, Winesap, Cortland
}

public class EnumDemo4 {
    public static void main(String[] args) {
        Apple4 ap, ap2, ap3;

        //получить все порядковые значения с помощью метода ordinal()
        System.out.println("Все константы сортов яблок " +
                " и их порядковые значения: ");
        for (Apple4 a : Apple4.values()) {
            System.out.println(a + " " + a.ordinal());
        }
        ap = Apple4.RedDel;
        ap2 = Apple4.GoldenDel;
        ap3 = Apple4.RedDel;
        System.out.println();

        //продемонстрировать применение методов compareTo(), equals()

        if (ap.compareTo(ap2) < 0)
            System.out.println(ap + " предшествует " + ap2);
        if (ap.compareTo(ap2) > 0)
            System.out.println(ap2 + " предшествует " + ap);
        if (ap.compareTo(ap3) == 0)
            System.out.println(ap + " равно " + ap3);
        System.out.println();

        if (ap.equals(ap2))
            System.out.println("Ошибка!");
        if (ap.equals(ap3))
            System.out.println(ap + " равно " + ap3);
        if (ap == ap3)
            System.out.println(ap + " == " + ap3);

    }
}
//Вывод в консоль:
//Все константы сортов яблок  и их порядковые значения:
//Jonatan 0
//GoldenDel 1
//RedDel 2
//Winesap 3
//Cortland 4
//
//GoldenDel предшествует RedDel
//RedDel равно RedDel
//
//RedDel равно RedDel
//RedDel == RedDel
