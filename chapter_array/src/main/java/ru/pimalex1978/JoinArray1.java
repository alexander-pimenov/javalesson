package ru.pimalex1978;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * 1. Apache Commons Lang - ArrayUtils
 * Самый простой способ - добавить библиотеку Apache Commons Lang и использовать ее ArrayUtils.
 * addAll для объединения массивов.
 * Этот метод поддерживает массивы как примитивных, так и объектных типов.
 */

public class JoinArray1 {
    public static void main(String[] args) {

        String[] s1 = new String[]{"a", "b", "c"};
        String[] s2 = new String[]{"d", "e", "f"};
        String[] s3 = new String[]{"g", "h", "i"};

        String[] result = ArrayUtils.addAll(s1, s2);
        String[] resultAll = ArrayUtils.addAll(result, s3);

        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(resultAll));

        int[] int1 = new int[]{1, 2, 3};
        int[] int2 = new int[]{4, 5, 6};

        int[] result2 = ArrayUtils.addAll(int1, int2);

        System.out.println(Arrays.toString(result2));

    }
}
