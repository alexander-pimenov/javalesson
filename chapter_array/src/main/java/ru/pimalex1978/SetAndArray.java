package ru.pimalex1978;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class SetAndArray {
    public static final String[] ENDPOINTS = {"/asd", "/qwe", "/zxc"};

    public static void main(String[] args) {
        String[] arr0 = {"/asd"};
        System.out.println("arr0 = " + Arrays.toString(arr0));

        String[] arr1 = {"/asd", "/qwe", "/zxc"};
        String s = Arrays.toString(arr1);
        System.out.println("arr1 = " + s);

        String[] arr2 = new String[]{"/asd", "/qwe", "/zxc"};
        System.out.println("arr2 = " + Arrays.toString(arr2));

        String[] arr3 = new String[3];
        arr3[0] = "/asd";
        arr3[1] = "/qwe";
        arr3[2] = "/zxc";
        System.out.println("arr3 = " + Arrays.toString(arr3));

        System.out.println("ENDPOINTS = " + Arrays.toString(ENDPOINTS));

        //преобразование Set ключей мапы в массив:
        var map = new HashMap<String, String>() {{
            put("/asd", "ASD");
            put( "/qwe", "QWE");
            put("/zxc", "ZXC");
        }};
        Set<String> stringSet = map.keySet();
        String[] stringArr = stringSet.toArray(String[]::new);
        System.out.println("stringArr = " + Arrays.toString(stringArr));
    }
}
