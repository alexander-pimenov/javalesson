package ru.pimalex1978.arraylistdemo;

import java.util.ArrayList;
import java.util.List;

public class ArrayListMixedTypeDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("Tester");
        list.add(1);
        list.add("Dad");
        list.add(15);
        for (Object s : list) {
            if (s instanceof String) {
                System.out.println(((String) s).charAt(0));
            }
            if (s instanceof Integer) {
                System.out.println(s);
            }
        }
    }
}
