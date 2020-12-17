package ru.pimalex1978.algorithms_in_java;

/*Суть QuickSort в следующем:
 * берется случайный элемент массива и берутся элементы слева и
 * спарва от него. И производится их сравнение. И так дальше по рекурсии.
 * Рекурсия - это вызов функции внутри самой себя.
 * Конечно принимая меры, чтоб не войти в бесконечный вызов функции.
 *
 * Сложность этого алгоритма O(n log n)
 * Quick Sort реализуется с помощью рекурсии.
 * Этот алгоритм относится к неустойчивым алгоритмам, т.к. во время сортировки
 * постоянно происходит перемешивание элементов.
 *
 * Очень хорошее анимированное объяснение работы алгоритма QuickSort
 * смотри http://me.dt.in.th/page/Quicksort/
 */

import java.util.Arrays;

public class QuickSortingAlgorithm {

    public static int[] array = {10, 5, 8, 1, 4, 7};

    public static void main(String[] args) {
        quickSort();
        System.out.println(Arrays.toString(array));

        //Для сортировки мы можем использовать Arrays.sort() -
        //он выполнен с помощью quickSort
//        Arrays.sort(array); //использует алгорим быстрой сортировки
//        System.out.println(Arrays.toString(array));

    }

    private static void quickSort() {
        int start = 0;
        int end = array.length - 1;
        doSort(start, end);
    }

    //отдельный метод сортировки, где используются переменные начала
    //start и конца end
    private static void doSort(int start, int end) {
        /*делаем проверку, что индекс начала, больше или равно
         * индексу конца. Т.е. если мы до этого дошли, то значит мы пришли к завершению,
         * т.е. проверили все элементы.
         * Если это не так то делаем действие.
         * Переменные start и end заведены для удобства, с ними
         * работать в цикле комфортнее.*/
        if (start >= end) return;
        int i = start; //индекс начала
        int j = end; //индекс конца
        //[currentIndex] - случайный текущий элемент из массива, т.е. его позиция.
        //Здесь реализовано вычисление середины массива (и далее подмассива)
        //Не важно как его достаем (как то рандомно i - (i - j) / 2)
        int currentIndex = i - (i - j) / 2;
        //for использовать не можем, т.к. заранее не известно кол-во итераций
        while (i < j) {
            while (i < currentIndex && (array[i] <= array[currentIndex])) {
                i++;
            }
            while (j > currentIndex && (array[currentIndex] <= array[j])) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                /*Выполняем проверки, потому что currentIndex рандомный и он
                 * не обязательно в центре массива*/
                if (i == currentIndex) {
                    currentIndex = j;
                } else if (j == currentIndex)
                    currentIndex = i;
            }
        }
        /*Выполняем рекурсию со стартового и до текущего индекса*/
        doSort(start, currentIndex); //первая половина массива
        doSort(currentIndex + 1, end); //последняя половина массива
    }
}