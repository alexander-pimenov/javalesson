package ru.pimalex1978.algorithms_in_java;

import java.util.Random;

/* Очень хорошее анимированное объяснение работы алгоритма QuickSort
 * смотри http://me.dt.in.th/page/Quicksort/
 * */

public class QuickSortExample {

    //Создаем массив array длинной ARRAY_LENGTH на 50 элементов
    private static int ARRAY_LENGTH = 50;
    private static int[] array = new int[ARRAY_LENGTH];

    //Заполнять этот массив будем рандомно
    private static Random generator = new Random();

    //Метод для инициализации массива и рандомного заполнения массива
    private static void initArray() {
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            array[i] = generator.nextInt(100);
        }
    }

    //Метод для вывода на печать массива
    private static void printArray() {
        for (int i = 0; i < ARRAY_LENGTH - 1; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[ARRAY_LENGTH - 1]);
    }

    //Метод QuickSort
    private static void quickSort() {
        int startIndex = 0;
        int endIndex = ARRAY_LENGTH - 1;
        doSort(startIndex, endIndex);
    }

    //
    private static void doSort(int start, int end) {
        if (start >= end)
            return; //завершить выполнение, если длина массива равна 0.
        //завершить выполнение, если уже нечего делить.
        int i = start, j = end;

        //Выбор случайного опорного элемента из массива.
        //Здесь высчитываем середину массива или в дальнейшем подмассива.
        int cur = i - (i - j) / 2;

        //for использовать не можем, т.к. заранее не известно кол-во итераций
        //разделить на подмассивы, который больше и меньше опорного элемента
        while (i < j) {
            while ((array[i] <= array[cur]) && i < cur) {
                i++;
            }
            while ((array[cur] <= array[j]) && j > cur) {
                j--;
            }
            if (i < j) { //меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }

        /*Выполняем рекурсию со стартового и до текущего индекса*/
        doSort(start, cur); //первая половина массива
        doSort(cur + 1, end); //последняя половина массива
    }

    public static void main(String[] args) {
        initArray();
        printArray();
        long start = System.currentTimeMillis();
        quickSort();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println("Время выполнения программы: " + executionTime);
        printArray();
    }
}

//Результат программы:
//80, 57, 99, 59, 36, 40, 57, 56, 0, 68, 55, 63, 58, 80, 33, 86, 38, 7, 60, 32, 74, 51, 79, 30, 18, 82, 62, 13, 73, 64
//0, 7, 13, 18, 30, 32, 33, 36, 38, 40, 51, 55, 56, 57, 57, 58, 59, 60, 62, 63, 64, 68, 73, 74, 79, 80, 80, 82, 86, 99