package ru.pimalex1978.algorithms_in_java;

import java.util.Arrays;

public class SortingAlgorithms {

    public String stripHtmlTags(String html) {
        String newHtml = html.replaceAll("\\<[^>]*>", "");
        return newHtml;
    }

    //Сортировка Вставками
    public int[] insertionSort(int[] array) {
        int key;
        for (int i = 1; i < array.length; i++) {
            key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }

    //Сортировка Выбором
    public int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int min_i = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    min_i = j;
                }
            }
            if (i != min_i) {
                int tmp = array[i];
                array[i] = min;
                array[min_i] = tmp;
            }
        }
        return array;
    }

    //Сортировка Шелла
    public int[] shellSorting(int[] array) {
        int h = 1;
        int n = array.length;
        while (h < n / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < array.length; i++) {
                for (int j = i; j >= h && array[j] > array[j - h]; j -= h) {
                    int temp = array[j];
                    array[j] = array[j - h];
                    array[j - h] = temp;
                }
            }
            h = h / 3;
        }
        return array;
    }

    //Сортировка Пузырьком
    public int[] bubbleSorting(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] a = {3, 5, 6, 3, 76, 4};

        System.out.println("Сортировка Шелла:");
        int[] b = new SortingAlgorithms().shellSorting(a);
        for (int i1 : b) {
            System.out.print(i1 + " ");
        }

        System.out.println();

        System.out.println("Сортировка Вставками:");
        int[] c = new SortingAlgorithms().insertionSort(a);
        System.out.println(Arrays.toString(c));

        System.out.println("Сортировка Пузырьком:");

        c = new SortingAlgorithms().bubbleSorting(a);
        System.out.println(Arrays.toString(c));

        System.out.println("Сортировка Выбором:");

        c = new SortingAlgorithms().selectionSort(a);
        System.out.println(Arrays.toString(c));
    }
}
