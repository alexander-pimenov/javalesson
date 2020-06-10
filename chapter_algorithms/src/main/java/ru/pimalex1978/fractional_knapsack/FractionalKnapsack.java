package ru.pimalex1978.fractional_knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FractionalKnapsack {

    public static void main(String[] args) {

        //эти Item у нас константы, в ходе программы мы их менять не будем
        final Item item1 = (new Item("Исаакиевский собор", 10, 5));
        final Item item2 = (new Item("Эрмитаж", 11, 8));
        final Item item3 = (new Item("Кунсткамера", 4, 3));
        final Item item4 = (new Item("Петропавловская крепость", 7, 10));
        final Item item5 = (new Item("Ленинградский зоопарк", 15, 9));
        final Item item6 = (new Item("Медный всадник", 17, 1));
        final Item item7 = (new Item("Казанский собор", 3, 4));
        final Item item8 = (new Item("Спас на Крови", 9, 2));
        final Item item9 = (new Item("Зимний дворец Петра I", 12, 7));
        final Item item10 = (new Item("Зоологический музей", 6, 5));
        final Item item11 = (new Item("Музей обороны и блокады Ленинград", 19, 2));
        final Item item12 = (new Item("Русский музей", 8, 5));

        //массив из наших предметов, т.е. набор объектов которые у нас есть
        final Item[] items = {item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12};

        /*по нашему жадному алгоритму, если мы хотим взять в наш рюкзак наилучший
         * набор, но сначала нужно отсортировать наши предметы по удельному весу
         * В метод Arrays.sort() передаем наш массив предметов и Компаратор, в
         * котором укажем логику сортировки предметов: сортируем по удельной ценности,
         * которую вычисляет метод valuePerUnitOfWeight(), а также развернем наш массив,
         * т.е. сортируем от большего к меньшему.*/
        Arrays.sort(items, Comparator.comparingDouble(Item::valuePerUnitOfWeight).reversed());


        //выведем отсортированный массив.
        //сортировка имеет сложность O(N*log(N))
        System.out.println(Arrays.toString(items));
        System.out.println("====================");

        //максимальное предоставленное время (вместимость нашего рюкзака)
        final int W = 32;

        //аккумулируем текущий вес
        int weightSoFar = 0;
        //аккумулируем ценность, которую мы уже набрали наш рюкзак
        double valueSoFar = 0;
        //индекс текущего предмета
        int currentItem = 0;
        //наилучший набор
        Item[] bestBag = new Item[items.length];

        //currentItem<items.length - пока есть предметы для перебора
        //и weightSoFar != W т.е. пока не вышли за пределы рюкзака
        while (currentItem < items.length && weightSoFar != W) {
            if ((weightSoFar + items[currentItem].getWeight()) < W) {
                //берем объект целиком
                valueSoFar = valueSoFar + items[currentItem].getValue();
                weightSoFar = weightSoFar + items[currentItem].getWeight();
                bestBag[currentItem] = items[currentItem];

            } else {
                //берем часть предмета: часть его ценности и часть его веса
                //сначала вычисляем какую часть обекта мы можем взять
                //затем этот результат делим наполный вес объекта
                //т.е. получаем значение, какую часть объекта мы можем взять
                //потом умножаем на ценность объекта, т.о. получим частичную
                //стоимость объекта
                valueSoFar = valueSoFar + (W - weightSoFar) / (double) items[currentItem].getWeight() * items[currentItem].getValue();

                //назначаем значение размера рюкзака, т.к. рюкзак полный теперь
                weightSoFar = W;
            }
            currentItem++;
        }

        //смотрим что у нас лежит в наборе (рюкзаке)
        System.out.println("Ценность наилучшего набора: " + valueSoFar);

        //согласно жадного алгоритма
        System.out.println("Наилучший набор: " + Arrays.toString(bestBag));

    }
}

/**
 * Класс представляет сущность предмет.
 */
class Item {
    //название предмета
    private String name;
    //ценность предмета
    private int value;
    //вес предмета
    private int weight;


    public Item(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    //считает удельную ценность предмета (ценность на единицу веса)
    public double valuePerUnitOfWeight() {
        //return weight * 1.0 / value; // приводим к типу double
        return value / (double) weight; // приводим к типу double
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" +
                "'" + name + "\'" +
                ", v: " + value +
                ", w: " + weight +
                "}";
    }
}
