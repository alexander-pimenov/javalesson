package ru.pimalex1978.stream;

import java.util.Collections;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

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
        int[] A = new int[7];
        A[0] = 4;
        A[1] = 6;
        A[2] = 2;
        A[3] = 2;
        A[4] = 6;
        A[5] = 6;
        A[6] = 1;
        System.out.println("solution1 : " + solution1(A));
        System.out.println();
        System.out.println("solution2 : " + solution2(A));
    }

    private static int solution1(int[] A) {
        int result = 0;
        int i = 0;
        for (int X : A) {
            i++;
            int j = 0;
            for (int Y : A) {
                j++;
                if (X == Y)
                    result = Math.max(result, Math.abs(i - j));
            }
        }
        return result;
    }

    private static int solution2(int... a) {
        class Point {
            private int index, value;

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

