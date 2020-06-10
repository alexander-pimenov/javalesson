package ru.pimalex1978.backpack_task;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Knapsack {
    private static final List<Item> ITEMS = new ArrayList<>();
    private static final Map<Integer, Bag> CACHE = new HashMap<>();
    private static final boolean FINITE_ITEMS = true; //whether an item can be added more than once

    public static void main(String[] args) {

        ITEMS.add(new Item("Исаакиевский собор", 10, 5));
        ITEMS.add(new Item("Эрмитаж", 11, 8));
        ITEMS.add(new Item("Кунсткамера", 4, 3));
        ITEMS.add(new Item("Петропавловская крепость", 7, 10));
        ITEMS.add(new Item("Ленинградский зоопарк", 15, 9));
        ITEMS.add(new Item("Медный всадник", 17, 1));
        ITEMS.add(new Item("Казанский собор", 3, 4));
        ITEMS.add(new Item("Спас на Крови", 9, 2));
        ITEMS.add(new Item("Зимний дворец Петра I", 12, 7));
        ITEMS.add(new Item("Зоологический музей", 6, 5));
        ITEMS.add(new Item("Музей обороны и блокады Ленинград", 19, 2));
        ITEMS.add(new Item("Русский музей", 8, 5));
        ITEMS.add(new Item("Навестить друзей", 20, 12));
        ITEMS.add(new Item("Музей восковых фигур", 13, 2));
        ITEMS.add(new Item("Литературно-мемориальный музей Ф.М. Достоевского", 2, 4));
        ITEMS.add(new Item("Екатерининский дворец", 5, 1));
        ITEMS.add(new Item("Петербургский музей кукол", 14, 1));
        ITEMS.add(new Item("Музей микроминиатюры «Русский Левша»", 18, 3));
        ITEMS.add(new Item("Всероссийский музей А.С. Пушкина и филиалы", 1, 6));
        ITEMS.add(new Item("Музей современного искусства Эрарта", 16, 7));

        Bag best = bestBagForCapacity(32);
        System.out.println(best.toString());
    }

    public static Bag bestBagForCapacity(int capacity) {
        if (CACHE.containsKey(capacity)) return CACHE.get(capacity);
        if (capacity < 0) return null;
        if (capacity == 0) return new Bag(0, 0);

        int currentWeight = -1;
        int currentValue = -1;
        String newItem = null;
        List<String> previousItems = null;
        for (Item item : ITEMS) {
            Bag previous = bestBagForCapacity(capacity - item.getWeight());
            if (previous == null) continue;

            int weightSoFar = previous.getMaxW(); //аккумулируем текущие времязатарты
            int valueSoFar = previous.getBestPrice(); //аккумулируем ценность, которую мы уже набрали в набор
            int nextBestValue = 0;
            Item candidateItem = null;
            for (Item candidate : ITEMS) {
                if (FINITE_ITEMS && previous.alreadyHas(candidate)) continue;
                if (weightSoFar + candidate.getWeight() <= capacity && nextBestValue < valueSoFar + candidate.getValue()) {
                    candidateItem = candidate;
                    nextBestValue = valueSoFar + candidate.getValue();
                }
            }

            if (candidateItem != null && nextBestValue > currentValue) {
                currentValue = nextBestValue;
                currentWeight = weightSoFar + candidateItem.getWeight();
                newItem = candidateItem.getName();
                previousItems = previous.getContents();
            }
        }

        if (currentWeight <= 0 || currentValue <= 0) throw new RuntimeException("cannot be 0 here");
        Bag bestBag = new Bag(currentWeight, currentValue);
        bestBag.add(previousItems);
        bestBag.add(Collections.singletonList(newItem));
        CACHE.put(capacity, bestBag);
        return bestBag;
    }
}
