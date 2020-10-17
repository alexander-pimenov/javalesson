package ru.pimalex1978;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тест - это клиент нашего кода.
 */
public class WhenConvertArabicToRomanNumber {

    @Test
    public void convert_1_To_I() {
        String romanNumber = ConvertNumber.toRoman(1);
        assertEquals("I", romanNumber);
    }

    @Test
    public void convert_2_To_II() {
        String romanNumber = ConvertNumber.toRoman(2);
        assertEquals("II", romanNumber);
    }

    @Test
    public void convert_3_To_III() {
        String romanNumber = ConvertNumber.toRoman(3);
        assertEquals("III", romanNumber);
    }

    @Test
    public void convert_5_To_V() {
        String romanNumber = ConvertNumber.toRoman(5);
        assertEquals("V", romanNumber);
    }

    @Test
    public void convert_6_To_VI() {
        String romanNumber = ConvertNumber.toRoman(6);
        assertEquals("VI", romanNumber);
    }
    @Test
    public void convert_8_To_VIII() {
        String romanNumber = ConvertNumber.toRoman(8);
        assertEquals("VIII", romanNumber);
    }

    @Test
    public void convert_4_To_IV() {
        String romanNumber = ConvertNumber.toRoman(4);
        assertEquals("IV", romanNumber);
    }

    @Test
    public void convert_10_To_X() {
        String romanNumber = ConvertNumber.toRoman(10);
        assertEquals("X", romanNumber);
    }

    @Test
    public void convert_9_To_IX() {
        String romanNumber = ConvertNumber.toRoman(9);
        assertEquals("IX", romanNumber);
    }

    @Test
    public void convert_19_To_XIX() {
        String romanNumber = ConvertNumber.toRoman(19);
        assertEquals("XIX", romanNumber);
    }

    @Test
    public void convert_20_To_XX() {
        String romanNumber = ConvertNumber.toRoman(20);
        assertEquals("XX", romanNumber);
    }

    @Test
    public void convert_35_To_XXXV() {
        String romanNumber = ConvertNumber.toRoman(35);
        assertEquals("XXXV", romanNumber);
    }
}
