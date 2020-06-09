package ru.pimalex1978;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Классы больших чисел BigInteger и BigDecimal.
 * <p>
 * Классы больших чисел используют для вычислений с крайне высокими
 * требованиями к точности.
 * У них теоретически нет максимального размера. (Число весит 100 бит)
 * Классы больших чисел не используют в своей работе операторы ("+" "-" "*" "/")
 * а предоставляют вместо этого набор методов.
 * методы для осуществления арифметических операций:
 * add(), subtract(), multiply(), divide().
 * Используются для операций сложения, вычитания, умножения и деления соответственно.
 * <p>
 * Их объекты являются неизменяемыми (Immutable).
 * <p>
 * doubleValue(), intValue(), floatValue(), longValue() и т.д. — используются
 * для преобразования большого числа к примитивному типу Java.
 * Будь осторожен при их использовании и не забывай про разницу во вместимости!
 * <p>
 * min() и max() — позволяют найти минимальное и максимальное значение из двух
 * переданных больших чисел.
 * Обрати внимание: методы не являются статическими!
 */

public class BigNumbersTest {
    public static void main(String[] args) {

        BigInteger integer = new BigInteger("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        BigInteger integer2 = new BigInteger("222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");

        //сложение больших чисел
        BigInteger result = integer.add(BigInteger.valueOf(33333333));
        //преобразования большого числа к примитивному типу
        long resultLong = integer.longValue();

        System.out.println(integer); //объекты являются неизменяемыми (Immutable).
        System.out.println(result);
        System.out.println(resultLong); //8198552921648689607 - не вместилось!!!
        System.out.println(integer.max(integer2));

        BigDecimal decimal = new BigDecimal("123.444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444");
        System.out.println(decimal);

        /*Управление округлением*/
        //Всего у BigDecimal существует 8 режимов округления.
        //метод setScale() и параметр режима округления (RoundingMode).
        BigDecimal decimal1 = new BigDecimal("111.5555555555");

        //ROUND_CEILING — округление в большую сторону
        BigDecimal decimalRound1 = decimal1.setScale(3, RoundingMode.CEILING);
        System.out.println("округление в большую сторону " + decimalRound1);

        //ROUND_DOWN — отбрасывание разряда
        //Округление в меньшую сторону по модулю, т.е. знак не учитываем
        BigDecimal decimalRound2 = decimal1.setScale(3, RoundingMode.DOWN);
        System.out.println("отбрасывание разряда " + decimalRound2);
        //ROUND_FLOOR — округление в меньшую сторону
        BigDecimal decimalRound3 = decimal1.setScale(3, RoundingMode.FLOOR);
        System.out.println("округление в меньшую сторону " + decimalRound3);
        //ROUND_HALF_UP — округление в большую сторону, если число после запятой >= .5
        BigDecimal decimalRound4 = decimal1.setScale(1, RoundingMode.HALF_UP);
        System.out.println("ROUND_HALF_UP " + decimalRound4);
        //ROUND_HALF_DOWN — округление в большую сторону, если число после запятой > .5
        BigDecimal decimalRound5 = decimal1.setScale(1, RoundingMode.HALF_DOWN);
        System.out.println("ROUND_HALF_DOWN " + decimalRound5);
        //ROUND_HALF_EVEN — округление будет зависеть от цифры слева от запятой.
        //Если цифра слева будет четной, то округление будет произведено вниз,
        //в меньшую сторону. Если цифра слева от запятой нечетная, то округление
        //будет произведено вверх.
        BigDecimal decimalRound6 = decimal1.setScale(2, RoundingMode.HALF_EVEN);
        System.out.println("ROUND_HALF_EVEN " + decimalRound6); //112 т.к. цифра слева от запятой - 1 - нечетная. Округление происходит вверх.
        //ROUND_UP — округление в большую сторону.
        BigDecimal decimalRound7 = decimal1.setScale(3, RoundingMode.HALF_UP);
        System.out.println("ROUND_UP — округление в большую сторону" + decimalRound7);

        /* Сравнение Больших Чисел.
         * Для корректного сравнения двух BigDecimal нужно использовать
         * метод compareTo()
         * возвращает (-1 если x < y), (0 если x == y), (1 если x > y)*/

        BigDecimal x = new BigDecimal("1.5");
        BigDecimal y = new BigDecimal("1.500");
        System.out.println("Сравнение Больших Чисел:");
        System.out.println(x.compareTo(y));

    }
}
