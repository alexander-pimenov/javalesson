package ru.pimalex1978;

/**
 * https://www.youtube.com/watch?v=e3wcDos9gAI
 * https://www.youtube.com/watch?v=lDdJYid8NpE&feature=emb_logo
 * <p>
 * Класс конвертирующий арабские цифры в римские.
 * В римских цифрах есть правило, что одна и та же
 * цифра не повторяется больше 3-х раз подряд.
 * <p>
 * Перевод из римской нотации.
 * Для целой римской записи получаем такое регулярное выражение:
 * ^(M{0,3})(D?C{0,3}|C[DM])(L?X{0,3}|X[LC])(V?I{0,3}|I[VX])$
 */
public class ConvertNumber {
    public static String toRoman(int arabicNumber) {
//        На 3-х тестах было так, далее стали рефакторить.
//
//        if (arabicNumber == 1) {
//            return "I";
//        } else if (arabicNumber == 2) {
//            return "II";
//        } else {
//            return "III";
//        }
        /*Получим число и будем его допиливать*/
        String romanNumber = getRomanNumber(arabicNumber);
        romanNumber = romanNumber.replace("IIII", "IV");
        romanNumber = replaceIntegerlacedDigits(romanNumber);
        return romanNumber;
    }

    /**
     * Замена повторяющихся символов.
     *
     * @param romanNumber римское число.
     * @return измененное римское число.
     */
    private static String replaceIntegerlacedDigits(String romanNumber) {
        for (int i = 2; i < romanNumber.length(); i++) {
            /*Проверяем, что имеем три одинаковых символа*/
            if (romanNumber.charAt(i - 2) == romanNumber.charAt(i)
                    && romanNumber.charAt(i - 1) != romanNumber.charAt(i)) {
                /*Заменяем римское число*/
                romanNumber = romanNumber.substring(0, i - 2) + romanNumber.charAt(i - 1) +
                        nextDigit(romanNumber.charAt(i)) + romanNumber.substring(i + 1);
            }
        }
        return romanNumber;
    }

    private static char nextDigit(char digit) {
        return new DigitsMap().nextDigit(digit);
    }

    private static String getRomanNumber(int arabicNumber) {
        StringBuilder romanNumber = new StringBuilder();
        /*После введем класс для мапинга пары чисел Pair
         * Будем сохранять их в списке.*/
        DigitsMap digitsMap = new DigitsMap();
        /*Будем итерироваться по каждому элементу списка и для
         * каждого элемента повторим цикл while*/
        for (PairOgDigits digit : digitsMap.digits) {
            while (arabicNumber >= digit.getArabic()) {
                romanNumber.append(digit.getRoman());
                arabicNumber -= digit.getArabic();
            }
        }
        return romanNumber.toString();
    }
}
