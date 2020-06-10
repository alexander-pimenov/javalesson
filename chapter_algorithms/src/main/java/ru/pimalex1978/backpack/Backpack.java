package ru.pimalex1978.backpack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Backpack {
    private List<Item> bestItems = null;

    private double maxW;
    private double bestPrice;

    //вычисляет общее время на посещение всех достопримечательностей (вычисляет общий вес набора предметов)
    private double calcWeight(List<Item> items) {
        double result = 0;
        for (Item item : items) {
            result += item.getWeight();
        }
        return result;
    }

    // вычисляет общую стоимость набора предметов
    private double calcPrice(List<Item> items) {
        double result = 0;
        for (Item item : items) {
            result += item.getPrice();
        }
        return result;
    }

    //проверка, является ли данный набор лучшим решением задачи
    private void checkSet(List<Item> items) {
        if (this.bestItems == null) {
            if (this.calcWeight(items) <= this.maxW) {
                this.bestItems = items;
                this.bestPrice = this.calcPrice(items);
            }
        } else {
            if (this.calcWeight(items) <= this.maxW && this.calcPrice(items) > this.bestPrice) {
                this.bestItems = items;
                this.bestPrice = this.calcPrice(items);
            }
        }
    }

    //создание всех наборов перестановок значений
    public void makeAllSets(List<Item> items) {
        if (items.size() > 0) {
            this.checkSet(items);
        }
        for (int i = 0; i < items.size(); i++) {
            List<Item> newSet = new ArrayList<>(items);
            newSet.remove(i);
            makeAllSets(newSet);
        }
    }

    public Backpack(double maxW) {
        this.maxW = maxW;
    }

    public List<Item> getBestItems() {
        return this.bestItems;
    }

    //сортировка всех предметов по удельной ценности
    public void sortingItemsBySpecificValue(List<Item> items){
        items.sort(Comparator.comparingDouble(Item::valuePerUnitOfWeight).reversed());
    }
}
