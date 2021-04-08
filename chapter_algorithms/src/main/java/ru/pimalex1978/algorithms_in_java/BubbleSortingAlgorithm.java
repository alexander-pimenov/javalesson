package ru.pimalex1978.algorithms_in_java;

/*Сортировка Пузырьком имеет квадратичную сложность O(n^2),
 * т.е. она самая длинная, но при этом самая понятная.
 * Она имеет такое название, т.к. элемент в ней какбы всплывает наверх,
 * т.е. передвигантся по массиву и встает на свою позицию.
 *
 * Берем элемент в конце массива. И проходим массив от конца до начала.
 * Перебирая каждый элемент и меняем местами когда это нужно.
 * Здесь будет два цикла.
 *
 * Эту сортировку применяют лишь для того чтобы понимать принцип сортирову.
 * Т.е. принцип перестановки и смены порядка элементов между собой.
 *
 * Это не оптимальный по времени алгоритм: O(n^2).
 * Quick Sort намного быстрее.*/

import java.util.Arrays;

public class BubbleSortingAlgorithm {

    private static int[] array = {10, 5, 8, 1, 4, 7};

    public static void main(String[] args) {

        bubbleSort(array); //[1, 4, 5, 7, 8, 10]
    }

    private static void bubbleSort(int[] arr) {
        /*Берем элемент в конце массива. И проходим массив от конца до начала.*/
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
}