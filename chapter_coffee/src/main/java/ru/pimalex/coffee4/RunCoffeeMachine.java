package ru.pimalex.coffee4;

import java.util.Arrays;

public class RunCoffeeMachine {
    public static void main(String[] args) {
        CoffeeMachine cm = new CoffeeMachine();
        //в параметрах сначала вводим price, затем value.
        //если поменять порядок, то получим ошибку
        System.out.println(Arrays.toString(cm.change(30,50)));
    }
}
