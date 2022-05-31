package ru.pimalex1978.example_like_runnable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* Лямбда выражение описывает правило, логику метода.
 * В качестве примера, как записывать и менять лямбду, можно вспоминать list.sort(Comparator),
 * ведь в зависимости от реализации метода, мы по-разному сможем сортировать строки в списке.
 * */
public class TestLambdaComparator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hi");
        list.add("hello");
        list.add("home");
        list.add("hug");

        //сортировка по длине слов
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        System.out.println(list);

        //в обратную сторону
        list.sort(((o1, o2) -> o2.length() - o1.length()));
//        list.sort(Comparator.comparingInt(String::length)); - это совсем по новому )))

        System.out.println(list);

        //Лямбду можно записывать в переменную.
        Comparator<String> stringComparator = ((o1, o2) -> {
            if (o1.length() > o2.length()) return 1;
            else if (o1.length() < o2.length()) return -1;
            else return 0;
        });

        list.sort(stringComparator);
        System.out.println(list);

    }
}
