package ru.pimalex1978.example1;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * В этом классе приводится пример работы с Большими числами.
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


public class BigDecimalClass {
    public static void main(String[] args) {
        // Примеры работы с BigDecimal
        // пример сути проблемы с точностью представления double
        double d1 = 2;
        double d2 = 1.1;
        System.out.println("Использование типов double:");
        System.out.println("d1 = " + d1 + " d2 = " + d2);
        System.out.println("d1 - d2 = " + (d1 - d2)); // 0.8999999999999999
        // как это решается с использованием BigDecimal
        BigDecimal bd1 = BigDecimal.valueOf(2);
        BigDecimal bd2 = BigDecimal.valueOf(1.1);
        System.out.println("Использование типов BigDecimal:");
        System.out.println("bd1 = " + bd1 + " bd2 = " + bd2);
        System.out.println("bd1 - bd2 = " + (bd1.subtract(bd2))); // 0.9

        double d3 = 10;
        double d4 = 0.0825;
        System.out.println("Использование типов double:");
        System.out.println("d3*d4 = " + (d3 * d4)); // 0.8250000000000001

        // лучше создавать объекты BigDecimal из строки
        // или при помощи метода valueOf()
        System.out.println("Использование типов BigDecimal:");
        BigDecimal bd3 = new BigDecimal(0.3);
        System.out.println("bd3 = " + bd3); // 0.299999999999999988897769753748434595763683319091796875
        bd3 = new BigDecimal("0.3");
        System.out.println("bd3 = " + bd3); // 0.3
        bd3 = BigDecimal.valueOf(0.3);
        System.out.println("bd3 = " + bd3); // 0.3
        //BigDecimal bd4 = new BigDecimal("10");
        BigDecimal bd4 = BigDecimal.valueOf(10);
        BigDecimal bd5 = BigDecimal.valueOf(0.0825);
        System.out.println("bd4*bd5 = " + (bd4.multiply(bd5))); // 0.8250

        //примеры округления
        //bd3.setScale(1); //выдаст ошибку т.к. нет параметра округления
        System.out.println("ROUND_CELLING bd3 = " + bd3.setScale(1, BigDecimal.ROUND_CEILING));
        System.out.println("ROUND_CELLING bd5 = " + bd5.setScale(2, RoundingMode.CEILING));
        // ROUND_CEILING: В большую сторону
        System.out.println();
        System.out.println("ROUND_CELLING: Округление в большую сторону");
        BigDecimal bdr = new BigDecimal("0.333");
        System.out.println("bdr = " + bdr);
        System.out.println("   ROUND_CELLING bdr = " + bdr.setScale(2, RoundingMode.CEILING)); // 0.34
        bdr = new BigDecimal("-0.333");
        System.out.println("bdr = " + bdr);
        System.out.println("   ROUND_CELLING bdr = " + bdr.setScale(2, RoundingMode.CEILING)); // -0.33

        // ROUND_DOWN: Округление в меньшую сторону по модулю, т.е. знак не учитываем
        System.out.println();
        System.out.println("ROUND_DOWN: Округление в меньшую сторону по модулю");
        bdr = new BigDecimal("0.333");
        System.out.println("bdr = " + bdr);
        System.out.println("   ROUND_DOWN bdr = " + bdr.setScale(2, RoundingMode.DOWN)); // 0.33
        bdr = new BigDecimal("-0.333");
        System.out.println("bdr = " + bdr);
        System.out.println("   ROUND_DOWN bdr = " + bdr.setScale(2, RoundingMode.DOWN)); // -0.33

        // ROUND_UP: Округление в большую сторону по модулю, т.е. знак не учитываем
        System.out.println();
        System.out.println("ROUND_UP: Округление в большую сторону по модулю");
        bdr = new BigDecimal("0.333");
        System.out.println("bdr =  " + bdr);
        System.out.println("   ROUND_UP bdr =  " + bdr.setScale(2, BigDecimal.ROUND_UP)); // 0.34
        bdr = new BigDecimal("-0.333");
        System.out.println("bdr = " + bdr);
        System.out.println("   ROUND_UP bdr = " + bdr.setScale(2, RoundingMode.UP)); // -0.34

        // ROUND_FLOOR: В меньшую сторону
        System.out.println();
        System.out.println("ROUND_FLOOR: В меньшую сторону");
        bdr = new BigDecimal("0.333");
        System.out.println("bdr =  " + bdr);
        System.out.println("   ROUND_FLOOR bdr =  " + bdr.setScale(2, BigDecimal.ROUND_FLOOR)); // 0.33
        bdr = new BigDecimal("-0.333");
        System.out.println("bdr = " + bdr);
        System.out.println("   ROUND_FLOOR bdr = " + bdr.setScale(2, RoundingMode.FLOOR)); // -0.34

        // ROUND_HALF_UP: Округление вверх, если число после запятой >= .5
        System.out.println();
        System.out.println("ROUND_HALF_UP: Округление вверх, если число после запятой >= .5");
        bdr = new BigDecimal("0.335");
        System.out.println("bdr =  " + bdr);
        System.out.println("   ROUND_HALF_UP bdr =  " + bdr.setScale(2, BigDecimal.ROUND_HALF_UP)); //0.34
        bdr = new BigDecimal("-0.335");
        System.out.println("bdr = " + bdr);
        System.out.println("   ROUND_HALF_UP bdr = " + bdr.setScale(2, RoundingMode.HALF_UP)); //-0.34
        bdr = new BigDecimal("0.333");
        System.out.println("bdr =  " + bdr);
        System.out.println("   ROUND_HALF_UP bdr =  " + bdr.setScale(2, BigDecimal.ROUND_HALF_UP)); //0.33
        bdr = new BigDecimal("-0.333");
        System.out.println("bdr = " + bdr);
        System.out.println("   ROUND_HALF_UP bdr = " + bdr.setScale(2, RoundingMode.HALF_UP)); //-0.33

        System.out.println("ROUND_HALF_DOWN: Округление вверх, если число после запятой > .5");
        bdr = new BigDecimal("0.335");
        System.out.println("bdr =  " + bdr);
        System.out.println("   ROUND_HALF_DOWN bdr =  " + bdr.setScale(2, BigDecimal.ROUND_HALF_DOWN)); //0.33
        bdr = new BigDecimal("-0.335");
        System.out.println("bdr = " + bdr);
        System.out.println("   ROUND_HALF_DOWN bdr = " + bdr.setScale(2, BigDecimal.ROUND_HALF_DOWN)); //-0.33
        bdr = new BigDecimal("0.336");
        System.out.println("bdr =  " + bdr);
        System.out.println("   ROUND_HALF_DOWN bdr =  " + bdr.setScale(2, RoundingMode.HALF_DOWN)); //0.34
        bdr = new BigDecimal("-0.336");
        System.out.println("bdr = " + bdr);
        System.out.println("   ROUND_HALF_DOWN bdr = " + bdr.setScale(2, RoundingMode.HALF_DOWN)); //-0.34

        // ROUND_HALF_EVEN: Округление по четности
        //ROUND_HALF_EVEN — округление будет зависеть от цифры слева от запятой.
        //Если цифра слева будет четной, то округление будет произведено вниз,
        //в меньшую сторону. Если цифра слева от запятой нечетная, то округление
        //будет произведено вверх.
        System.out.println();
        System.out.println("ROUND_HALF_EVEN: Округление по четности");
        bdr = new BigDecimal("0.334");
        System.out.println("bdr =  " + bdr);
        System.out.println("  ROUND_HALF_EVEN bdr =  " + bdr.setScale(2, BigDecimal.ROUND_HALF_EVEN)); //0.33
        bdr = new BigDecimal("0.335");
        System.out.println("bdr =  " + bdr);
        System.out.println("  ROUND_HALF_EVEN bdr =  " + bdr.setScale(2, BigDecimal.ROUND_HALF_EVEN)); //0.34
        bdr = new BigDecimal("0.336");
        System.out.println("bdr =  " + bdr);
        System.out.println("  ROUND_HALF_EVEN bdr =  " + bdr.setScale(2, BigDecimal.ROUND_HALF_EVEN)); //0.34

        bdr = new BigDecimal("0.324");
        System.out.println("bdr =  " + bdr);
        System.out.println("  ROUND_HALF_EVEN bdr =  " + bdr.setScale(2, BigDecimal.ROUND_HALF_EVEN)); //0.32
        bdr = new BigDecimal("0.325");
        System.out.println("bdr =  " + bdr);
        System.out.println("  ROUND_HALF_EVEN bdr =  " + bdr.setScale(2, BigDecimal.ROUND_HALF_EVEN)); //0.32
        bdr = new BigDecimal("0.326");
        System.out.println("bdr =  " + bdr);
        System.out.println("  ROUND_HALF_EVEN bdr =  " + bdr.setScale(2, BigDecimal.ROUND_HALF_EVEN)); //0.33

        bdr = new BigDecimal("-0.335");
        System.out.println("bdr = " + bdr);
        System.out.println("  ROUND_HALF_EVEN bdr = " + bdr.setScale(2, BigDecimal.ROUND_HALF_EVEN)); //-0.34
        bdr = new BigDecimal("-0.325");
        System.out.println("bdr = " + bdr);
        System.out.println("  ROUND_HALF_EVEN bdr = " + bdr.setScale(2, BigDecimal.ROUND_HALF_EVEN)); //-0.32

        // ROUND_UNNECESSARY: без округления
        System.out.println();
        System.out.println("ROUND_UNNECESSARY: без округления");
        bdr = new BigDecimal("0.333");
        System.out.println("bdr =  " + bdr);
        System.out.println("   ROUND_UNNECESSARY bdr =  "
                + bdr.setScale(3, BigDecimal.ROUND_UNNECESSARY)); //0.333
        bdr = new BigDecimal("-0.333");
        System.out.println("bdr = " + bdr);
        System.out.println("   ROUND_UNNECESSARY bdr = "
                + bdr.setScale(3, RoundingMode.UNNECESSARY)); //-0.333

        System.out.println("\nПример деления с округлением");
        BigDecimal bd01 = new BigDecimal("1");
        BigDecimal bd03 = new BigDecimal("3");
        // BigDecimal bd1d3 = bd01.divide(bd03); // вызовет ошибку ArithmeticException
        BigDecimal bd1d3 = bd01.divide(bd03, 5, BigDecimal.ROUND_HALF_UP);
        System.out.println("bd1d3 = " + bd1d3); // 0.33333

        System.out.println("\nПримеры сравнения");
        BigDecimal a = new BigDecimal("2.00");
        BigDecimal b = new BigDecimal("2.0");
        System.out.println("a = " + a + "  b = " + b);
        System.out.println("a.equals(b) = " + a.equals(b)); // ложь

        /* Сравнение Больших Чисел.
         * Для корректного сравнения двух BigDecimal нужно использовать
         * метод compareTo()*/
        // возвращает (-1 если a < b), (0 если a == b), (1 если a > b)
        System.out.println("a.compareTo(b) = " + a.compareTo(b)); // 0
        // возвращает (-1 если a < 0), (0 если a == 0), (1 если a > 0)
        System.out.println("a.signum() = " + a.signum()); // 1

        // убираем незначащие нули в конце BigDecimal
        System.out.println("\nУбираем незначащие нули в конце BigDecimal");
        BigDecimal bd_1 = new BigDecimal("1.55");
        BigDecimal bd_2 = new BigDecimal("3.15");
        BigDecimal bd_3 = bd_1.add(bd_2);
        System.out.println("bd_3 = " + bd_3); // bd_3 = 4.70
        System.out.println("bd_3 = " + bd_3.stripTrailingZeros()); // bd_3 = 4.7

    }
}
