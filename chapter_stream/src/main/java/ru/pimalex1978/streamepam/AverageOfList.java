package ru.pimalex1978.streamepam;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

/**
 * В классе показан подсчет среднего числа из списка целых чисел.
 * Числа задаются стримом IntStream от ... и до ...
 */
public class AverageOfList {
    public static void main(String[] args) {
        IntStream listStream = IntStream.range(15, 30);
        OptionalDouble optDouble = listStream.peek(e -> System.out.printf("Имеем число %s \r\n", e)).average();
        if (optDouble.isPresent()) {
            System.out.println("Average is: " + optDouble.getAsDouble());
        } else {
            System.out.println("Try Again!");
        }
        final OptionalDouble optionalDouble = IntStream.of(5, 6, 7, 8, 9, 9, 9, 4, 2, 1, 3).average();
        if (optionalDouble.isPresent()) {
            final double asDouble = optionalDouble.getAsDouble();
            System.out.println("Average is: " + Math.round(asDouble * 100.0) / 100.0); //округлил до 2-х знаков после запятой
        } else {
            System.out.println("Try Again!");
        }


    }
}
