package ru.pimalex1978.merge_sort_invertions;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Число инверсий.
 * https://stepik.org/lesson/17691/step/2?unit=4501
 * Чтобы искать число инверсий нужно модифицировать сортировку слиянием.
 * Центральное место сортировки слиянием - это процедура слияния, которая
 * сливает два отсортированных массива в один отсортированный.
 * <p>
 * Необходимо посчитать число пар индексов 1<i<j<n, для которых A[i]>A[j].
 * (Такая пара элементов называется инверсией массива. Количество инверсий
 * в массиве является в некотором смысле его мерой неупорядоченности:
 * например, в упорядоченном по неубыванию массиве инверсий нет вообще,
 * а в массиве, упорядоченном по убыванию, инверсию образуют каждые два
 * элемента.)
 */
public class MergeSortInversions {

    //счетчик для подсчета плохих пар (инверсии массива),
    // т.е. которые нужно развернуть для сортировки
    long count = 0;

    /**
     * Метод слияния двух отсортированных массивов.
     * <p>
     * Так же этот метод считает количество пар у которых A[i]>A[j]
     *
     * @param a входной массив a[]
     * @param b входной массив b[]
     * @return обобщенный массив
     */
    private int[] merge(int[] a, int[] b) {
        /*берем по элементу из массивов a[] и b[] и сравниваем между собой*/
        int i = 0; //для индексации массива a[]
        int j = 0; //для индексации массива b[]

        //складываем всё в результирующий массив
        int[] res = new int[a.length + b.length];
        for (int k = 0; k < res.length; k++) {
            //если j массива b[] вышел за границы массива, то берем все элементы
            //из массива a[], так же проверим что и i не выходит за границы массива
            if (j == b.length || (i < a.length && a[i] <= b[j])) {
                res[k] = a[i];
                i++;
            } else {
                //если a[i, i + 1, ..., a.length - 1]>b[j]
                count += a.length - i;
                res[k] = b[j];
                j++;
            }
        }
//        System.out.println("-=" + Arrays.toString(res) + "=-"); //для интересного вывода
        return res;
    }


    /**
     * Метод сортировки слиянием.
     * Берет массив, разбивает его пополам, сортирует рекурсивно каждую из
     * частей, после чего сливает полученные части вместе.
     * Реализация не аккуратная, т.к. создается куча массивов и тратится
     * куча памяти, но всё понятно и просто.
     * <p>
     *
     * @param a входной массив
     * @return результирующий отсортированный массив
     */
    private int[] mergeSort(int[] a) {
        //если массив длины =1, то сортировать его не надо
        if (a.length == 1) return a;

        int n = a.length; //длина массива
        int m = n / 2; //индекс где проходит деление пополам
        //если массив больше длины 1, то разобъем его на два.
        //на левую
        int[] left = new int[m];
        //и правую
        int[] right = new int[n - m];
        //копируем элементы массива a[] в left[] и right[] части
        System.arraycopy(a, 0, left, 0, m);
        System.arraycopy(a, m, right, 0, n - m);
        //Рекурсивно запустим деление массивов
        left = mergeSort(left);
        right = mergeSort(right);
        //выведем соединенные и отсортированные массивы
        return merge(left, right);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        long startTime = System.currentTimeMillis();
        new MergeSortInversions().run1();
//        new MergeSortInversions().run2(); //тестовый вызов
//        new MergeSortInversions().run3(); //читаем через BufferedReader
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    /**
     * Метод в котором исходный файл читаем с помощью Scanner
     * и вызываем методы сортировки.
     * Scanner работает медленнее, чем BufferedReader.
     * Поэтому в методе run3() для примера используем BufferedReader.
     * <p>
     * В методе представлены две сортировки ( класса Arrays и наша сортировка
     * слиянием), чтобы сравнить их скорости работы.
     *
     * @throws FileNotFoundException исключение
     */
    private void run1() throws FileNotFoundException {
//        Scanner input = new Scanner(new File("inputInversions.txt"));
        Scanner input = new Scanner(new File(MergeSortInversions.class.getResource("/inputInversions.txt").getFile()));

        //читаем первое число из первой строки из файла - это количество чисел в массиве
        int n = input.nextInt();
        int[] a = new int[n];
        //читаем каждое целое число из файла и сохраняем его в массиве a[]
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        //вывод имеющегося массива
//        for (int i = 0; i < n; i++) {
//            System.out.println(a[i]);
//        }

        /*====================================*/
        //сортировка методом из класса Arrays
//        Arrays.sort(a);
//        for (int i = 0; i < n; i++) {
//            System.out.println(a[i]);
//        }
        /*====================================*/

        System.out.println("--------");

        /*====================================*/
        //сортировка нашим методом
        int[] sortedA = mergeSort(a);
        for (int i = 0; i < n; i++) {
            System.out.println(sortedA[i]);
        }
        /*====================================*/

        //а так можно проверить, что все соседние элементы идут по возрастанию
//        for (int i = 0; i < n; i++) {
//            if (sortedA[i] > sortedA[i + 1]) {
//                throw new RuntimeException("Не верный порядок");
//            }
//        }

        /*====================================*/
        System.out.println("Число инверсий: " + count);
    }

    /**
     * Метод для проверки правильности работы метода merge() -
     * слияние двух отсортированных массивов.
     * Он сливает два массива в правильном порядке.
     */
    private void run2() {
        System.out.println(Arrays.toString(
                merge(new int[]{2, 5, 14, 26, 33},
                        new int[]{1, 3, 4, 5, 7, 8, 35})));
    }

    /**
     * Метод в котором исходный файл читаем с помощью BufferedReader
     * и вызываем методы сортировки.
     * <p>
     * В методе представлены две сортировки ( класса Arrays и наша сортировка
     * слиянием), чтобы сравнить их скорости работы.
     *
     * @throws IOException исключение
     */
    private void run3() throws IOException {
        //Для более быстрого чтения используем BufferedReader вместо Scanner
        BufferedReader input = new BufferedReader(new FileReader("inputInversions.txt"));
//        BufferedReader input = new BufferedReader(new FileReader(MergeSortInversions.class.getResource("/inputInversions.txt").getFile()));


        //в первой строчке лежит одно число - количество элементов
        int n = Integer.parseInt(input.readLine());

        //во второй строчке лежат очень много целых чисел, их
        //нужно сначала достать, т.е. читаем строчку,
        //разделить на отдельные токены,
        //разбиваем методом split по пробелу
        //и после этого каждый из них обработать
        String[] tokens = input.readLine().split(" ");

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(tokens[i]);
        }
        //вывод имеющегося массива
//        for (int i = 0; i < n; i++) {
//            System.out.println(a[i]);
//        }

        /*====================================*/
        //сортировка методом из класса Arrays
//        Arrays.sort(a);
//        for (int i = 0; i < n; i++) {
//            System.out.println(a[i]);
//        }
        /*====================================*/

        System.out.println("--------");

        /*====================================*/
        //сортировка нашим методом
        int[] sortedA = mergeSort(a);
        for (int i = 0; i < n; i++) {
            System.out.println(sortedA[i]);
        }
        /*====================================*/

        //а так можно проверить, что все соседние элементы идут по возрастанию
//        for (int i = 0; i < n - 1; i++) {
//            if (sortedA[i] > sortedA[i + 1]) {
//                throw new RuntimeException("Не верный порядок");
//            }
//        }

        /*====================================*/
        System.out.println("Число инверсий: " + count);
    }
}
