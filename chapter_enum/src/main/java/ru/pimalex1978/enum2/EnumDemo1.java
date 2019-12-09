package ru.pimalex1978.enum2;

/**
 * Перечисление сортов яблок.
 */
enum Apple1 {
    Jonatan, GoldenDel, RedDel, Winesap, Cortland
}

public class EnumDemo1 {
    public static void main(String[] args) {
        Apple1 ap;
        ap = Apple1.RedDel;

        //вывести значение перечислимого типа
        System.out.println("Значение ap: " + ap);
        System.out.println();

        ap = Apple1.GoldenDel;

        //сравнить два значения перечислимого типа
        if (ap == Apple1.GoldenDel)
            System.out.println("Переменная ap содержит GoldenDel.\n");

        //применить перечмсления для управления оператором switch
        switch (ap) {
            case Jonatan:
                System.out.println("Сорт Jonatan красный.");
                break;
            case GoldenDel:
                System.out.println("Сорт GoldenDel желтый.");
                break;
            case RedDel:
                System.out.println("Сорт RedDel красный.");
                break;
            case Winesap:
                System.out.println("Сорт Winesap красный.");
                break;
            case Cortland:
                System.out.println("Сорт Cortland красный.");
                break;
        }
    }
}
