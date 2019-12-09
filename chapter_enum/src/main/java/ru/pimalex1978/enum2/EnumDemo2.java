package ru.pimalex1978.enum2;

/**
 * В этой программе нужно воспользоваться встроенными
 * в перечисление методами.
 */


//перечисление сортов яблок
enum Apple2 {
    Jonatan, GoldenDel, RedDel, Winesap, Cortland
}

public class EnumDemo2 {
    public static void main(String[] args) {
        Apple2 ap;

        System.out.println("Константы перечислимого типа Apple2:");

        //применить метод values()
//        Apple2 allapples[] = Apple2.values();
//        for (Apple2 a : allapples) {
//            System.out.println(a);
//        }
//        System.out.println();

        /* values() - возвращает массив, содержащий список
        констант перечислимого типа.*/
        for (Apple2 a : Apple2.values()) {
            System.out.println(a);
        }
        System.out.println();

        //применить метод valueOf()
        /*valuesOf() - возвращает константу перечислимого типа,
        значение которой соответствует.*/
        ap = Apple2.valueOf("Winesap");
        System.out.println("переменная ap содержит " + ap);
    }
}
