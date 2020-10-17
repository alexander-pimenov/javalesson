package ru.pimalex1978;

import java.util.ArrayList;

public class DigitsMap {
    public final ArrayList<PairOgDigits> digits;

    public DigitsMap() {
        digits = new ArrayList<>(); //инициализируем
        /*Заполним список соответствующими патернами,
         * но обязательно от большего к меньшему 10 5 1*/
        digits.add(new PairOgDigits(1000, 'M'));
        digits.add(new PairOgDigits(500, 'D'));
        digits.add(new PairOgDigits(100, 'C'));
        digits.add(new PairOgDigits(50, 'L'));
        digits.add(new PairOgDigits(10, 'X'));
        digits.add(new PairOgDigits(5, 'V'));
        digits.add(new PairOgDigits(1, 'I'));
    }

    public char nextDigit(char digit) {
        for (int i = 0; i < digits.size(); i++) {
            if (digits.get(i).getRoman() == digit) {
                return digits.get(i - 1).getRoman();
            }
        }
        return digit;
//        throw new IllegalArgumentException();
    }
}
