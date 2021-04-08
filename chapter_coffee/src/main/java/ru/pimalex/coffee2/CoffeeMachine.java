package ru.pimalex.coffee2;

import java.util.Arrays;

public class CoffeeMachine {
    private static final int[] COINS = {1, 2, 5, 10};
    private int[] bank;
    private int[] buffer;

    public CoffeeMachine(int one, int two, int five, int ten) {
        this.bank = new int[] {one, two, five, ten};
    }

    private boolean check(int value, int price) throws InsufficientFundsException {
        boolean result = false;
        int change = value - price;
        if (change < 0) {
            throw new InsufficientFundsException("Insufficient funds, Could you please put additional money?");
        }
        int[] array = new int[100];
        int size = 0;
        int i = COINS.length - 1;
        while (change > 0 && i >= 0) {
            while (change >= COINS[i] && bank[i] > 0) {
                array[size++] = COINS[i];
                change -= COINS[i];
                bank[i]--;
            }
            i--;
        }
        buffer = Arrays.copyOf(array, size);
        int sum = Arrays.stream(buffer).sum();
        if (sum == value - price) {
            result = true;
        }
        return result;
    }

    public int[] changes(int value, int price) throws InsufficientFundsException {
        if (check(value, price)) {
            return buffer;
        } else {
            return new int[] {value};
        }
    }
    /*public static void main(String[] args) {
        CoffeeMachine cf = new CoffeeMachine(29, 0, 0, 0);
        int[] sa = cf.changes(100, 70);
        for (int i = 0; i < sa.length; i++) {
            System.out.println(sa[i]);
        }
    }*/
}
