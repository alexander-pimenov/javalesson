package ru.pimalex.coffee4;

import java.util.Arrays;

public class CoffeeMachine {

    private int[] NOMINAL = {1, 2, 5, 10};

    //метод выдачи сдачи кофейным автоматом
    int[] change(int price, int value) {
        int theChange = value - price;
        int[] result = new int[theChange];
        int i = 0;
        int index = NOMINAL.length - 1;
        while (theChange != 0) {
            while (theChange - NOMINAL[index] < 0) {
                index--;
            }
            theChange -= NOMINAL[index];
            result[i++] = NOMINAL[index];
        }
        return Arrays.copyOf(result, i);
    }
}

