package ru.pimalex1978.concurrent.threadsafety.mathutils;

import java.math.BigInteger;

/*
 * https://www.baeldung.com/java-thread-safety
 */
public class MathUtils {

    public static BigInteger factorial(int number) {
        BigInteger f = new BigInteger("1");
        for (int i = 2; i <= number; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        return f;
    }
}