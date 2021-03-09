package ru.pimalex1978.missingnumber;

import java.util.Arrays;

/**
 * Задача: https://leetcode.com/problems/missing-number/
 * Для массива nums, содержащего n различных чисел в диапазоне [0, n],
 * вернуть единственное число в диапазоне, которое отсутствует в массиве.
 * Числа изменяются с шагом 1.
 * Решение:
 * https://www.youtube.com/watch?v=b6hewWmJ-L4
 */
public class MissingNumber {
    /*1-й очень быстрый вариант. Используем формулу*/
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        int sum2 = 0;
        for (int i : nums) {
            sum2 += i;
        }
        return sum - sum2;
    }

    /*2-й способ. Не быстрый, но решенный обычным перебором и сравнением с
    * отсутствующим числом.*/
    public int missingNumber2(int[] nums) {
        Arrays.sort(nums);
        int expected = 0;
        for (int n : nums) {
            if (expected != n)
                break;
            expected++;
        }
        return expected;
    }
}
