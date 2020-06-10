package ru.pimalex1978.backpack;

import java.util.ArrayList;
import java.util.List;

/**
 * К сожалению сложность полного перебора для решения этой задачи равна О(!N)
 * где N - количество достопримечательностей (предметов),
 * поэтому программа будет выполняться достаточно долго при количестве достопримечательнсотей
 * более 12 шт!
 */

public class Main {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
//        items.add(new Item("Book", 1, 600));
//        items.add(new Item("Binoculars", 2, 5000));
//        items.add(new Item("First-aid kid", 4, 1500));
//        items.add(new Item("Notebook", 2, 40000));
//        items.add(new Item("Cauldron", 1, 500));

        items.add(new Item("Исаакиевский собор", 5, 10));
        items.add(new Item("Эрмитаж", 8, 11));
        items.add(new Item("Кунсткамера", 3.5, 4));
        items.add(new Item("Петропавловская крепость", 10, 7));
        items.add(new Item("Ленинградский зоопарк", 9, 15));
        items.add(new Item("Медный всадник", 1, 17));
        items.add(new Item("Казанский собор", 4, 3));
        items.add(new Item("Спас на Крови", 2, 9));
        items.add(new Item("Зимний дворец Петра I", 7, 12));
        items.add(new Item("Зоологический музей", 5.5, 6));
        items.add(new Item("Музей обороны и блокады Ленинград", 2, 19));
        items.add(new Item("Русский музей", 5, 8));
//        items.add(new Item("Навестить друзей", 12, 20));
//        items.add(new Item("Музей восковых фигур", 2, 13));
//        items.add(new Item("Литературно-мемориальный музей Ф.М. Достоевского", 4, 2));
//        items.add(new Item("Екатерининский дворец", 1.5, 5));
//        items.add(new Item("Петербургский музей кукол", 1, 14));
//        items.add(new Item("Музей микроминиатюры «Русский Левша»", 3, 18));
//        items.add(new Item("Всероссийский музей А.С. Пушкина и филиалы", 6, 1));
//        items.add(new Item("Музей современного искусства Эрарта", 7, 16));

        Backpack backpack = new Backpack(32);
        backpack.makeAllSets(items);
        System.out.println(items);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(backpack.getBestItems());

//        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
//        backpack.sortingItemsBySpecificValue(items);
//        System.out.println(items);
    }
}
