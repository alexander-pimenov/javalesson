package ru.pimalex1978.search_algorithms.linear_search;

public class LinearSearch {

    public static void main(String[] args) {
        int[] arr = new int[]{89, 57, 91, 47, 95, 3, 27, 22, 67, 99};

        int index = linerSearch(arr, 67);
        print(67, index);


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
     * Реализация метода линейного поиска.
     * При нахождении элемента возвращается его позиция в структуре данных.
     * Если элемент не найден, возвращаем -1.
     */
    public static int linerSearch(int[] arr, int elementToSearch) {

        for (int index = 0; index < arr.length; index++) {
            if (arr[index] == elementToSearch) {
                return index;
            }
        }
        return -1;
    }
}
