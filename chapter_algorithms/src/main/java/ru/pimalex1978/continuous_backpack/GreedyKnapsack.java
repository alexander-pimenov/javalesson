package ru.pimalex1978.continuous_backpack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Задача на программирование: непрерывный рюкзак
 */
public class GreedyKnapsack {
    /**
     * Класс описывающий предмет,
     * который будем помещать в рюкзак.
     * Сделаем их сравниваемыми по соотношению стоимости к весу:
     * r=cost/weight
     */
    class Item implements Comparable<Item> {
        int cost; //стоимость
        int weight; //вес

        public Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        /**
         * Метод реализующий сравнение предметов по убыванию
         * их отношений стоимости к весу.
         *
         * @param o объект предмет другой
         * @return целое число с разным знаком
         */
        @Override
        public int compareTo(Item o) {
            //запишем отношения r = cost / weight
//            double r1 = (double) cost / weight; //наш
//            double r2 = (double) o.cost / o.weight; //другой Item
            //сравним два числа типа Double, выводя сначала большие, т.е. ро убыванию
//            return -Double.compare(r1, r2);

            //запишем отношения r = cost / weight, но чтоб не сравнивать вещественные
            //числа и не приводить к double получим соотношение целых чисел
            //по правилу сравнения дробей сравним числитель первой дроби * на знаменатель второй
            //и числитель второй дроби * на знаменатель первой
            long r1 = (long) cost * o.weight;
            long r2 = (long) o.cost * weight;
            return -Long.compare(r1, r2);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        new GreedyKnapsack().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() throws FileNotFoundException {
        //данные будем считывать из файла лежащего в ресурсах
        //используем Scanner
        //Или из консоли также вводя данные как они представлены в файле System.in

            Scanner input = new Scanner(new File(GreedyKnapsack.class.getResource("/input.txt").getFile()));
//            Scanner input = new Scanner(System.in);
            //первая строка из файла - это количество предметов и вместимость рюкзака
            int n = input.nextInt(); //3
            int W = input.nextInt(); //50
            //создаем массив на n предметов
            Item[] items = new Item[n];
            //в цикле заполняем предметы
            for (int i = 0; i < n; i++) {
                //начиная со второй строки в файле
                //читаем первую цифру - это стоимость
                //вторую - это вес предмета
                items[i] = new Item(input.nextInt(), input.nextInt());
            }

            //выведем предметы
            for (int i = 0; i < n; i++) {
                System.out.println(items[i]);
            }

            //Согласно теории сначала отсортируем наш массив по удельному весу,
            // т.е. отношению стоимости к весу: cost/weight

//            Arrays.sort(items, new Comparator<Item>() {
//                @Override
//                public int compare(Item o1, Item o2) {
//                    //запишем отношения
//                    double r1 = (double) o1.cost / o1.weight;
//                    double r2 = (double) o2.cost / o2.weight;
//                    //сравним два числа типа Double, выводя сначала большие, т.е. по убыванию
//                    return -Double.compare(r1, r2);
//                }
//            });

            //Предметы можем сортировать и без компаратора, т.к. уже реализован,
            //как надо интерфейс Comparable в классе Item
            //по убыванию
            Arrays.sort(items);

            //выведем предметы
            for (int i = 0; i < n; i++) {
                System.out.println(items[i]);
            }

            /*----------------Алгоритм заполнения рюкзака-----------------*/
            //переменная для суммы
            double result = 0;
            //перебираем уже отсортированные предметы
            for (Item item : items) {
                //если предмет можно положить в рюкзак, то
                if (item.weight <= W) {
                    //увеличиваем стоимость в рюкзаке
                    result += item.cost;
                    //вместимость рюкзака уменьшается
                    W -= item.weight;
                } else {
                    //если нельзя положить, т.е. не помещается, то
                    //отрежем кусочек, чтобы влез
                    result += (double) item.cost * W / item.weight;
                    break;
                }
            }
            //выводим ответ: стоимость предметов в рюкзаке
            System.out.println(result);
            /*------------------------------------------------------------*/

    }
}
