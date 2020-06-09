package ru.pimalex1978.backpack_task;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private int weight;
    private int value;
    List<String> contents = new ArrayList<>();

    public Bag(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    boolean alreadyHas(Item item) {
        return contents.contains(item.getName());
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public List<String> getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "weight=" + weight +
                ", value=" + value + "\n"+
                "contents=" + contents;
    }

    //    @Override
//    public String toString() {
//        return "weight " + weight + " , value " + value + "\n" + contents.toString();
//    }

    void add(List<String> name) {
        contents.addAll(name);
    }
}
