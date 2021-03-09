package ru.pimalex1978.missingnumber;

import org.junit.Test;

import static org.junit.Assert.*;

public class MissingNumberTest {
    MissingNumber missingNumber = new MissingNumber();

    @Test
    public void missingNumber01() {
        int result = missingNumber.missingNumber(new int[]{3, 0, 1});
        assertEquals(2, result);
    }

    @Test
    public void missingNumber02() {
        int result = missingNumber.missingNumber(new int[]{0, 1});
        assertEquals(2, result);
    }

    @Test
    public void missingNumber03() {
        int result = missingNumber.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1});
        assertEquals(8, result);
    }

    @Test
    public void missingNumber04() {
        int result = missingNumber.missingNumber(new int[]{0});
        assertEquals(1, result);
    }

    @Test
    public void missingNumber21() {
        int result = missingNumber.missingNumber(new int[]{3, 0, 1});
        assertEquals(2, result);
    }

    @Test
    public void missingNumber22() {
        int result = missingNumber.missingNumber(new int[]{0, 1});
        assertEquals(2, result);
    }

    @Test
    public void missingNumber23() {
        int result = missingNumber.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1});
        assertEquals(8, result);
    }

    @Test
    public void missingNumber24() {
        int result = missingNumber.missingNumber(new int[]{0});
        assertEquals(1, result);
    }
}