package ru.pimalex1978.algorithms_in_java;
/**
 * BubbleSort
 *
 */
public class BubbleSort {
    /**
     * BubbleSort a array.
     *
     * @param arr - array.
     * @return sorted array.
     */
    public int[] sort(int[] arr) {
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