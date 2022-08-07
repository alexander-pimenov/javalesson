package ru.pimalex1978.stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.stream.IntStream;

/**
 * Как можно упростить/оптимизировать данный алгоритм solution1?
 * <p>
 * Это класс Solution, на вход поступает массив.
 * Сейчас в нем только 7 значений. А если значений очень много то он работает медленно.
 * Как ускорить его работу?
 * <p>
 * Сейчас сложность O(n^2), да в общем то это и минимум для этого алгоритма.
 * Можно только сделать break в циклах после находки необходимого.
 * <p>
 * Вот со стримами, работает шустро:
 */

public class Solution {
    public static void main(String[] args) {
        int[] array = new int[7];
        array[0] = 4;
        array[1] = 6;
        array[2] = 2;
        array[3] = 2;
        array[4] = 6;
        array[5] = 6;
        array[6] = 1;
        System.out.println("solution1 : " + solution1(array));
        System.out.println();
        System.out.println("solution2 : " + solution2(array));
    }

    private static int solution1(int[] array) {
        int result = 0;
        int i = 0;
        for (int elementX : array) {
            i++;
            int j = 0;
            for (int elementY : array) {
                j++;
                if (elementX == elementY) {
                    result = Math.max(result, Math.abs(i - j));
                }
            }
        }
        return result;
    }

    private static int solution2(int... a) {
        class Point {
            private int index;
            private int value;

            private Point(int index, int value) {
                this.index = index;
                this.value = value;
            }

            private int getIndex() {
                return index;
            }

            private int getValue() {
                return value;
            }
        }

        return IntStream.range(0, a.length)
                .mapToObj(i -> new Point(i, a[i]))
                .collect(groupingBy(Point::getValue, mapping(Point::getIndex, toList())))
                .values().stream()
                .mapToInt(list -> list.get(list.size() - 1) - list.get(0))
                .boxed()
                .sorted(Collections.reverseOrder())
                .findFirst().get();
    }
}

