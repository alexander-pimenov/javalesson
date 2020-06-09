package ru.pimalex1978.backpack_task;

public class Item {
    //название предмета
    private String name;
    //ценность предмета
    private int value;
    //вес предмета
    private int weight;

    public Item(String name, int value, int weight ) {
        this.value = value;
        this.weight = weight;
        this.name = name;
    }


    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }
}
