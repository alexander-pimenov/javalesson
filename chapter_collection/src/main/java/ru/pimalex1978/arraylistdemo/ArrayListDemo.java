package ru.pimalex1978.arraylistdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            list.add("String #" + i);
        }
        list.remove(1);
        List sublist = list.subList(10, 15);
        sublist.forEach(e -> System.out.println(e));


        String[] names = {"Alex", "Alina", "Andrey"};
        List stringList = Arrays.asList(names);
        stringList = new ArrayList(stringList);

        for (int i = 0; i < stringList.size(); i++) {
            System.out.println(stringList.get(i));
        }

        for (Object s : stringList) {
            System.out.println(s.toString());
        }

        stringList.remove(2);
        stringList.forEach(s -> System.out.println(s));
    }
}
