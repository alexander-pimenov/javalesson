package ru.pimalex1978.backpack;

public class Item {
    private String name; //название достопримечательности (как название предмета)
    private double weight; //время затрачиваемое на осмотр достопримечательности (как вес предмета)
    private int price; //важность достопримечательности (как ценность предмета)

    public Item(String name, double weight, int price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    //считает удельную ценность предмета (ценность на единицу веса)
    public double valuePerUnitOfWeight(){
        return price / weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "'" + name + '\'' +
                ", w: " + weight +
                ", p: " + price +
                "}";
    }
}
