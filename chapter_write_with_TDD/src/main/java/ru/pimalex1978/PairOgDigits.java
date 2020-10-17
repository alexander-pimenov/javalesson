package ru.pimalex1978;

/**
 * Класс для хранения пар соответствия
 * 1 - I
 * 5 - V
 * 10 - X
 * 50 - L
 * 100 - C
 * 500 - D
 * 1000 - M
 */
public class PairOgDigits {
    private int arabic;
    private char roman;

    public PairOgDigits(int arabic, char roman) {
        this.arabic = arabic;
        this.roman = roman;
    }

    public int getArabic() {
        return arabic;
    }

    public char getRoman() {
        return roman;
    }
}
