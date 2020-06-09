package ru.pimalex1978.search_algorithms.binary_search;

import java.util.Arrays;

/**
 * Реализация бинарного поиска, итеративный подход.
 * Массив должен быть предварительно отсортирован!!!!
 */

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{89, 57, 91, 47, 95, 3, 27, 22, 67, 99};

        Arrays.sort(arr);

        int index = binarySearch(arr, 67);
        print(67, index);
        System.out.println("===================");
        long start1 = System.currentTimeMillis();
        int ind = binarySearch(new int[]{3, 22, 27, 47, 57, 67, 89, 91, 95, 99}, 67);
        long end1 = System.currentTimeMillis();
        System.out.println("Время выполнения итеративного бинарного поиска = " + (end1 - start1));
        print(67, ind);
        System.out.println("===================");

        long start = System.currentTimeMillis();
        int idx = recursiveBinarySearch(new int[]{3, 22, 27, 47, 57, 67, 89, 91, 95, 99}, 0, 10, 67);
        long end = System.currentTimeMillis();
        System.out.println("Время выполнения рекурсивного бинарного поиска = " + (end - start));
        print(67, idx);

    }

    /**
     * Метод для вывода результата.
     */
    public static void print(int elementToSearch, int index) {
        if (index == -1) {
            System.out.println(elementToSearch + " not found.");
        } else {
            System.out.println(elementToSearch + " found at index: " + index);
        }
    }

    /**
     * Бинарный поиск, итеративный подход.
     *
     * @param arr             массив, в котором ищем элемент
     * @param elementToSearch элемент, который ищем
     * @return индекс искомого элемента
     */

    public static int binarySearch(int[] arr, int elementToSearch) {
        int firstIndex = 0;
        int lastIndex = arr.length - 1;

        //условие прекращения (элемент не представлен)
        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            //если средний элемент - целевой элемент, вернуть его индекс
            if (arr[middleIndex] == elementToSearch) {
                return middleIndex;
            }

            //если средний элемент меньше
            //направляем наш индекс в middle+1, убирая первую часть из рассмотрения
            else if (arr[middleIndex] < elementToSearch) {
                firstIndex = middleIndex + 1;
            }

            // если средний элемент больше
            // направляем наш индекс в middle-1, убирая вторую часть из рассмотрения
            else if (arr[middleIndex] > elementToSearch) {
                lastIndex = middleIndex - 1;
            }
        }
        return -1;
    }

    public static int recursiveBinarySearch(int[] arr, int firstElement, int lastElement, int elementToSearch) {

        //условие прекращения
        if (lastElement >= firstElement) {
            int mid = firstElement + (lastElement - firstElement) / 2;

            //если средний элемент - целевой элемент, вернуть его индекс
            if (arr[mid] == elementToSearch) {
                return mid;
            }

            // если средний элемент больше целевого
            // вызываем метод рекурсивно по суженным данным
            if (arr[mid] > elementToSearch) {
                return recursiveBinarySearch(arr, firstElement, mid - 1, elementToSearch);
            }

            // также, вызываем метод рекурсивно по суженным данным
            return recursiveBinarySearch(arr, mid + 1, lastElement, elementToSearch);
        }
        return -1;

    }
}
