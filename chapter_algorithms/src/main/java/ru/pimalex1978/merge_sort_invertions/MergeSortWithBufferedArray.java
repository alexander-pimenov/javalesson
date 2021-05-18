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
public class MergeSortWithBufferedArray {

    //счетчик для подсчета плохих пар (инверсии массива),
    // т.е. которые нужно развернуть для сортировки
    long count = 0;

    //это массив, как временный буфер, чтобы не создавать новые массивы
    private int[] temp;
    //входной массив, берем из файла или вводим из консоли
    private int[] a;

    /**
     * Метод слияния двух отсортированных массивов.
     * Берет массивы сливает их вместе и кладет на место
     * входного массива.
     * <p>
     * Так же этот метод считает количество пар у которых A[i]>A[j]
     *
     * @param l начальная граница входного массива a[]
     * @param m средняя граница входного массива a[]
     * @param r крайняя граница входного массива a[]
     */
    private void merge(int l, int m, int r) {
        //сортируем отсортированные куски массива от a[l...m-1] и a[m...r-1]
        // -> и кладем уже отсортированный массив во временный массив temp[l...r-1]
        //а потом в a[l...r-1].
        //i - индекс первого не использованного элемента массива в левой части,
        int i = l;
        //j - индекс первого не использованного элемента массива в правой части
        int j = m;

        //k - индекс массива temp
        for (int k = l; k < r; k++) {
            //если j вышел за границы массива, т.е. дошел до конца массива и =r
            //то берем все элементы
            //во второй части условия проверим, что левая часть не закончилась
            // i<m, т.е. меньше m
            // и так же проверим что и a[i]<=a[j]
            if (j == r || (i < m && a[i] <= a[j])) {
                temp[k] = a[i];
                i++;
            } else {
                //если a[i, i + 1, ..., a.length - 1]>a[j]
                count += m - i;
                temp[k] = a[j];
                j++;
            }
        }
        System.arraycopy(temp, l, a, l, r - l);
    }


    /**
     * Метод сортировки слиянием.
     * На вход передаем границы массива,
     * и функция сортирует массив от l до r и кладет его
     * на тоже самое место.
     * <p>
     *
     * @param l начальная граница массива
     * @param r конечная граница массива
     */
    private void mergeSort(int l, int r) {
        //если массив длины =1 или пустой, то сортировать его не надо, просто выход
        if (r <= l + 1) return;

        //иначе делим кусок на две части
        //a[l...r - 1] -> a[l...m-1] и a[m...r-1]
        int m = (l + r) >> 1; //индекс где проходит деление пополам
        //Рекурсивно запустим деление массивов mergeSort от левой части
        mergeSort(l, m); //указываем границы левой части
        //Рекурсивно запустим деление массивов mergeSort от правой части
        mergeSort(m, r); //указываем границы правой части
        //соединяем отсортированные массивы
        merge(l, m, r);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        long startTime = System.currentTimeMillis();
//        new MergeSortWithBufferedArray().run1();
        new MergeSortWithBufferedArray().run2(); //читаем через BufferedReader
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
        Scanner input = new Scanner(new File("inputInversions.txt"));
//        Scanner input = new Scanner(new File(MergeSortWithBufferedArray.class.getResource("/inputInversions.txt").getFile()));
//        Scanner input = new Scanner(System.in);

        //читаем первое число из первой строки из файла - это количество чисел в массиве
        int n = input.nextInt();
        a = new int[n];
        //читаем каждое целое число из файла и сохраняем его в массиве a[]
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        //вывод имеющегося массива
//        for (int i = 0; i < n; i++) {
//            System.out.println(a[i]);
//        }

        //заводим временный массив
        temp = new int[n];

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
        mergeSort(0, n);
        for (int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }
        /*====================================*/

        //а так можно проверить, что все соседние элементы идут по возрастанию
//        for (int i = 0; i < n; i++) {
//            if (a[i] > a[i + 1]) {
//                throw new RuntimeException("Не верный порядок");
//            }
//        }

        /*====================================*/
        System.out.println("Число инверсий: " + count);
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
    private void run2() throws IOException {
        //Для более быстрого чтения используем BufferedReader вместо Scanner
        BufferedReader input = new BufferedReader(new FileReader("inputInversions.txt"));
//        BufferedReader input = new BufferedReader(new FileReader(MergeSortWithBufferedArray.class.getResource("/inputInversions.txt").getFile()));
//        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        //в первой строчке лежит одно число - количество элементов
        int n = Integer.parseInt(input.readLine());
        a = new int[n];

        //во второй строчке лежат очень много целых чисел, их
        //нужно сначала достать, т.е. читаем строчку,
        //разделить на отдельные токены,
        //разбиваем методом split по пробелу
        //и после этого каждый из них обработать
        String[] tokens = input.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(tokens[i]);
        }
        //вывод имеющегося массива
//        for (int i = 0; i < n; i++) {
//            System.out.println(a[i]);
//        }
        temp = new int[n];

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
        mergeSort(0, n);
        for (int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }
        /*====================================*/

        //а так можно проверить, что все соседние элементы идут по возрастанию
//        for (int i = 0; i < n - 1; i++) {
//            if (a[i] > a[i + 1]) {
//                throw new RuntimeException("Не верный порядок");
//            }
//        }

        /*====================================*/
        System.out.println("Число инверсий: " + count);
    }
}
