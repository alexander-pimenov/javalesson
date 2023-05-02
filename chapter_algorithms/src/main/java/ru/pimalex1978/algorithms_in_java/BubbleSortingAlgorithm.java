package ru.pimalex1978.algorithms_in_java;

/* Сортировка Пузырьком имеет квадратичную сложность O(n^2),
 * т.е. она самая длинная, но при этом самая понятная.
 * Она имеет такое название, т.к. элемент в ней как бы всплывает наверх,
 * т.е. передвигается по массиву и встает на свою позицию.
 *
 * Берем элемент в конце массива. И проходим массив от конца до начала.
 * (хотя можно и в обычном направлении, см. bubbleSort2)
 * Перебирая каждый элемент и меняем местами когда это нужно.
 * Здесь будет два цикла.
 *
 * Эту сортировку применяют лишь, для того чтобы понимать принцип сортировки.
 * Т.е. принцип перестановки и смены порядка элементов между собой.
 *
 * Это не оптимальный по времени алгоритм: O(n^2).
 * Quick Sort намного быстрее.*/

import java.util.Arrays;

public class BubbleSortingAlgorithm {

    private static final int[] array = {10, 5, 8, 1, 4, 7, 2};
    private static final int[] array2 = {91, 15, 48, 11, 24, 67, 12};
    private static final int[] array3 = {19, 11, 81, 10, 24, 73, 2};

    public static void main(String[] args) {

        bubbleSort(array); //[1, 2, 4, 5, 7, 8, 10]

        int[] sort2 = bubbleSort2(array2);
        System.out.println(Arrays.toString(sort2)); //[11, 12, 15, 24, 48, 67, 91]

        int[] sort3 = bubbleSort2(array3);
        System.out.println(Arrays.toString(sort3)); //[2, 10, 11, 19, 24, 73, 81]
    }

    //out - внешний and inner - внутренний
    private static void bubbleSort(int[] arr) {
        /*Берем элемент в конце массива. И проходим массив от КОНЦА до НАЧАЛА.*/
        for (int out = arr.length - 1; out >= 0; out--) {
            /*В этом вложенном цикле мы идем от 0 до i, т.е. до текущего шага
             * цикла.*/
            for (int inner = 0; inner < out; inner++) {
                /*Если условие arr[j]>arr[j+1] выполняется, то
                 * мы используем перестановку элементов*/
                if (arr[inner] > arr[inner + 1]) {
                    int temp = arr[inner];
                    arr[inner] = arr[inner + 1];
                    arr[inner + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //out - внешний and inner - внутренний
    private static int[] bubbleSort2(int[] arr) {
        /*Берем элемент в конце массива. И проходим массив от КОНЦА до НАЧАЛА.*/
        int length = arr.length;
        for (int out = length - 1; out >= 0; out--) {
            /*В этом вложенном цикле мы идем от 0 до i, т.е. до текущего шага
             * цикла.*/
            for (int inner = 0; inner < out; inner++) {
                /*Если условие arr[j]>arr[j+1] выполняется, то
                 * мы используем перестановку элементов*/
                if (arr[inner] > arr[inner + 1]) {
                    int temp = arr[inner];
                    arr[inner] = arr[inner + 1];
                    arr[inner + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] bubbleSort3(int[] arr) {
        int length = arr.length;
        for (int out = 0; out < length - 1; out++) {
            for (int inner = 0; inner < length - out - 1; inner++) {
                if (arr[inner] > arr[inner + 1]) {
                    int temp = arr[inner];
                    arr[inner] = arr[inner + 1];
                    arr[inner + 1] = temp;
                }
            }
        }
        return arr;
    }
}