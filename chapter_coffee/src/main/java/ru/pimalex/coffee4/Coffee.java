package ru.pimalex.coffee4;

public class Coffee {
    private int price;
    private int value;

    public Coffee(int price, int value) {
        this.price = price;
        this.value = value;
    }

    public int getPrice() {
        return price;
    }

    public int getValue() {
        return value;
    }
}
