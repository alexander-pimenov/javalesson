package ru.pimalex1978.backpack_task;

public class Item {
    //название достопримечательности (как название предмета)
    private String name;
    //важность достопримечательности (как ценность предмета)
    private int value;
    //время затрачиваемое на осмотр достопримечательности (как вес предмета)
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

    @Override
    public String toString() {
        return "{" +
                "'" + name + '\'' +
                ", v: " + value +
                ", w: " + weight +
                '}';
    }
}
