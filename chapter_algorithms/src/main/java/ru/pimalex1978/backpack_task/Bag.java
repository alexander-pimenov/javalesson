package ru.pimalex1978.backpack_task;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private int maxW;
    private int bestPrice;
    private List<String> contents = new ArrayList<>();

    public Bag(int maxW, int bestPrice) {
        this.maxW = maxW;
        this.bestPrice = bestPrice;
    }

    boolean alreadyHas(Item item) {
        return contents.contains(item.getName());
    }

    public int getMaxW() {
        return maxW;
    }

    public int getBestPrice() {
        return bestPrice;
    }

    public List<String> getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "weight=" + maxW +
                ", value=" + bestPrice + "\n"+
                "contents=" + contents;
    }

    void add(List<String> name) {
        contents.addAll(name);
    }
}
