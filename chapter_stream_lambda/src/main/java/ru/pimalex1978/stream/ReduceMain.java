package ru.pimalex1978.stream;

import java.util.Arrays;
import java.util.List;

public class ReduceMain {
    public static void main(String[] args) {
        /*9. Примеры использования Reduce функции*/
        /*Метод reduce позволяет выполнять агрегатные функции над всей коллекцией
        (такие как сумма, нахождение минимального или максимального значение и т.п.),
        он возвращает одно значение для стрима, функция получает два аргумента —
        значение полученное на прошлых шагах и текущее значение.*/
        // Он возвращает одно Optional значение

        //Исходная коллекция чисел
        List<Integer> intCollection = Arrays.asList(1, 2, 3, 4, 2, 3);

        //Получить сумму чисел или вернуть 0
        // или так:
        Integer integer = intCollection.stream().reduce(Integer::sum).orElse(0);
        System.out.println("summ = " + integer);
        //или так:
        Integer summ = intCollection.stream()
                .reduce((o1, o2) -> o1 + o2)
                .orElse(0);
        System.out.println("summ = " + summ); //summ = 15

        //Вернуть максимум или -1
        //или так:
        Integer max1 = intCollection.stream().reduce(Integer::max).orElse(-1);
        System.out.println("max1 = " + max1); //max = 4

        //или так:
        Integer max2 = intCollection.stream()
                .reduce((p1, p2) -> p1 > p2 ? p1 : p2)
                .orElse(-1);
        System.out.println("max2 = " + max2); //max2 = 4

        //Вернуть минимум или 0
        Integer min2 = intCollection.stream()
                .reduce((p1, p2) -> p1 < p2 ? p1 : p2)
                .orElse(-1);
        System.out.println("min2 = " + min2); //min2 = 1

        //Вернуть сумму нечетных чисел или 0
        Integer sumOdd = intCollection.stream()
                .filter(o -> o % 2 != 0)
                .reduce(Integer::sum) // или .reduce((p1, p2) -> p1 + p2)
                .orElse(0);
        System.out.println("sumOdd = " + sumOdd); //sumOdd = 7

        //Вернуть последний элемент или 0
        Integer lastItem = intCollection.stream()
                .reduce((p1, p2) -> p2)
                .orElse(0);
        System.out.println("lastItem = " + lastItem); //lastItem = 3

        //Старым методом, без stream API:
        /*
        Integer lastOld = null;
        for(Integer i: collection) {
            lastOld = i;
        }
        lastOld = lastOld == null? 0 : lastOld;
        System.out.println("last = " + last + " : " + lastOld); // напечатает last = 2 : 2
        */

        /*
        Работа со сложными объектами:
        // Найдем самого старшего мужчину
        int oldMan = peoples.stream().filter((p) -> p.getSex() == Sex.MAN).map(People::getAge).reduce((s1, s2) -> s1 > s2 ? s1 : s2).get();
        System.out.println("oldMan = " + oldMan); // напечатает 69

        // Найдем самого минимальный возраст человека у которого есть бука е в имени
        int younger = peoples.stream()
                .filter((p) -> p.getName().contains("е"))
                .mapToInt(People::getAge)
                .reduce((s1, s2) -> s1 < s2 ? s1 : s2)
                .orElse(0);
        System.out.println("younger = " + younger); // напечатает 23

         */

    }
}
