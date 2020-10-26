package ru.pimalex1978.comparator;

import java.util.*;

/**
 * Код программы, которая сортирует строки по длине, будет выглядеть вот так.
 * Реализацию Comparator для выполнения кастомной сортировки можно выполнить несколькими
 * способами. Они показаны в коде.
 */
public class Solution {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "Privet", "kak", "dela", "tvoji", "Tolikas", "?");
        printList(list);
        Collections.sort(list); //Стандартная сортировка, по алфавиту
        printList(list);
        Collections.sort(list, new StringLengthComparator()); //сортировка по моему компаратору
        printList(list);

        //Или для примера будем использовать анонимный внутренний класс
        //это то же самое, что и отдельый класс, реализующий интерфейс Comparator<String>
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };

        Collections.sort(list, comparator);
        printList(list);

        //Или для примера будем использовать анонимный внутренний класс
        //это то же самое, что и отдельый класс, реализующий интерфейс Comparator<String>
        //Через лямбда-выражения.
        // Тут и объявление переменной, и создание анонимного класса — все вместе.
        //Однако есть способ записать этот код короче.
        Comparator<String> comparator2 = (String o1, String o2) -> {
            return o1.length() - o2.length();
        };

        Collections.sort(list, comparator2);
        printList(list);

        //Или даже так мжно записать:
        Collections.sort(list, (String obj1, String obj2) ->
                {
                    return obj1.length() - obj2.length();
                }
        );
        printList(list);

        //Или еще короче:
        Collections.sort(list, (o1, o2) -> o1.length() - o2.length());
        printList(list);

    }

    /**
     * Выводит на печать список
     *
     * @param list список, как параметр
     */
    private static void printList(List<String> list) {
        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}

/**
 * Класс, реализующий интерфейс Comparator,
 * для задания собственного порядка сортировки.
 * В данном случае мы сортируем по длине слова.
 */
class StringLengthComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}

//Или можно так в одном классе с использованием Анонимного внутренного класса:
//public class Solution
//{
//    public static void main(String[] args)
//    {
//        ArrayList<String> list = new ArrayList<String>();
//        Collections.addAll(list, "Привет", "как", "дела?");
//
//        Comparator<String> comparator = new Comparator<String>()
//        {
//            public int compare (String obj1, String obj2)
//            {
//                return obj1.length() – obj2.length();
//            }
//        };
//
//        Collections.sort(list, comparator);
//    }
//}