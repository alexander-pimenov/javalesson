package ru.pimalex1978.duplicate.algorithms_in_java;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayDemo {
    public static void main(String[] args) {

        System.out.println("==Объявление и инициализация массива==");
        int n = 10;
        double[] a = new double[n]; //Объявление и инициализация в одной строке
        System.out.println(Arrays.toString(a));
        System.out.println("===================");

        double[] b; //Объявление
        b = new double[n]; //Инициализация
        System.out.println(Arrays.toString(b));
        System.out.println("===================");
        for (int i = 0; i < n; i++) {
            b[i] = 0.0 + i;
            System.out.println(Arrays.toString(b));
        }
        System.out.println("===================");
        System.out.println("==Создание пустого массива==");
        double[] c = new double[0];
        System.out.println(Arrays.toString(c)); //пустой массив

        System.out.println("===================");
        System.out.println("==Поиск максимального занчения в массиве==");
        int[] d = {1, 3, 4, 9, 7, 2, 1, 5};
        int max = d[0]; //Принимаем за max 1-й элемент массива
        for (int i = 0; i < d.length; i++) {
            if (d[i] > max) max = d[i];
        }
        System.out.println("max = " + max);

        System.out.println("===================");
        System.out.println("==Вычисление среднего значения массива==");
        int[] dd = {1, 3, 4, 9, 7, 2, 1, 5};
        double sum = 0.0;
        for (int i = 0; i < dd.length; i++) {
            sum += dd[i];
        }
        double average = sum / dd.length;
        System.out.println(" Среднее значение в массиве = " + average);

        System.out.println("===================");
        System.out.println("==Перестановка элементов массива в обратном прядке==");
        double[] ddd = {1.0, 3.0, 4.0, 9.0, 7.0, 2.0, 1.0, 5.0, 6.0};
        int p = ddd.length;
        for (int i = 0; i < p / 2; i++) {
            double temp = ddd[i];
            ddd[i] = ddd[p - 1 - i];
            ddd[p - 1 - i] = temp;
        }
        System.out.println(Arrays.toString(ddd));
        System.out.println("===================");
        System.out.println("===Пример работы сортировки имен в алф порядке==");
        System.out.println("==Вариант 1==");

        List<String> names = new ArrayList<>();
        names.add("Nick");
        names.add("Dick");
        names.add("Halck");
        names.add("Adam");
        names.add("Eva");
        names.add("Ramsy");
        List<String> collect = names.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("==Вариант 2==");
        names.sort(Comparator.comparing(String::toString));
        System.out.println(names);
        System.out.println("==Вариант 3==");
        Collections.sort(names);
        System.out.println(names);
        System.out.println("==Вариант 4==");
        names.sort(String::compareTo);
        System.out.println(names);
        System.out.println("===================");


    }


}
