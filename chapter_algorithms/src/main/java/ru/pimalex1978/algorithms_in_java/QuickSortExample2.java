package ru.pimalex1978.algorithms_in_java;

import java.util.Arrays;

public class QuickSortExample2 {

    private static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return; //завершить выполнение, если длина массива равна 0.

        if (low >= high)
            return; //завершить выполнение, если уже нечего делить.

        //выбрать опопрный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        //разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i < j) {
            while ((array[i] <= opora) && i < middle) {
                i++;
            }
            while ((opora <=array[j]) && j > middle) {
                j--;
            }

            if (i < j) { //меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        //вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, i);
        if (high > i)
            quickSort(array, j+1, high);
    }


    public static void main(String[] args) {
        int[] x = {8, 0, 4, 7, 3, 7, 10, 12, -3, 5, 8};
        System.out.println("Было:");
        System.out.println(Arrays.toString(x));

        int low = 0;
        int high = x.length - 1;

        quickSort(x, low, high);
        System.out.println("Стало:");
        System.out.println(Arrays.toString(x));
    }
}