package ru.pimalex1978.currency_converter2;

public class Currency {
    //название валюты
    private String name;
    //коэффициент
    private double rate = 0;

    // Конструктор, который позволяет создавать объекты валюты
    public Currency(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    // геттеры и сеттеры для экземпляров Currency
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
