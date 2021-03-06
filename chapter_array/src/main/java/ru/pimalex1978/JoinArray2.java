package ru.pimalex1978;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 2. Java API
 * Пример чистого API Java, поддерживает как примитивные, так и универсальные типы.
 */
public class JoinArray2 {
    public static void main(String[] args) {

        String[] s1 = new String[]{"a", "b", "c"};
        String[] s2 = new String[]{"d", "e", "f"};
        String[] s3 = new String[]{"g", "h", "i"};
        String[] s4 = new String[]{"j", "k", "l"};

        String[] result = joinArrayGeneric(s1, s2, s3, s4);

        System.out.println(Arrays.toString(result));

        int[] int1 = new int[]{1, 2, 3};
        int[] int2 = new int[]{4, 5, 6};
        int[] int3 = new int[]{7, 8, 9};

        int[] result2 = joinArray(int1, int2, int3);

        System.out.println(Arrays.toString(result2));

    }

    /*
    * Обобщенный метод для соединения нескольких массивов в один
    */
    private static <T> T[] joinArrayGeneric(T[]... arrays) {
        int length = 0;
        for (T[] array : arrays) {
            length += array.length;
        }

        //T[] result = new T[length];
        //Используем Рефлексию, чтобы создать новый массив
        @SuppressWarnings("unchecked")
        final T[] result = (T[]) Array.newInstance(arrays[0].getClass().getComponentType(), length);

        int offset = 0;
        for (T[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    /*
     * Метод для соединения нескольких int[] массивов в один
     */
    private static int[] joinArray(int[]... arrays) {
        int length = 0;
        for (int[] array : arrays) {
            length += array.length;
        }

        final int[] result = new int[length];

        int offset = 0;
        for (int[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }

    //create other overloaded primitive type - long, double...
    //static long[] joinArray(long[]... arrays)
}
