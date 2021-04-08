package ru.pimalex.coffee2;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CoffeeMachineTest {

    @Test
    public void  whenPrise100andValue82Return10and5and2and1() {
        CoffeeMachine cf = new CoffeeMachine(10, 10, 5, 5);
        int[] result = cf.changes(100, 82);
        int[] expected = new int[] {10, 5, 2, 1};
        assertThat(result, is(expected));
    }

    @Test (expected = InsufficientFundsException.class)
    public void whenPriceGrater() {
        CoffeeMachine cf = new CoffeeMachine(10, 10, 5, 5);
        int[] result = cf.changes(100, 101);
    }

    @Test
    public void whenNotEnoughChangesThenReturnValue() {
        CoffeeMachine cf = new CoffeeMachine(0, 0, 0, 2);
        int[] result = cf.changes(100, 82);
        int[] expected = new int[] {100};
        assertThat(result, is(expected));
    }
}