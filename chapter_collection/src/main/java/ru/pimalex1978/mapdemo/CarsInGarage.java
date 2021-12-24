package ru.pimalex1978.mapdemo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CarsInGarage {
    public static void main(String[] args) {
        Map<String, Integer> garage = new HashMap<>();
        garage.put("Audi", 2);
        garage.put("BMW", 2);
        garage.put("Mersedes", 3);
        System.out.println(garage);
        garage.put("BMW",1);
        System.out.println(garage);
        garage.put(null, 10);
        System.out.println(garage);
        garage.put(null, null);
        System.out.println(garage.get(null));

        if(garage.containsValue(2)){
            garage.put("Zhiguli", 1);
        }
        System.out.println(garage);

        garage.keySet().forEach(e-> System.out.println(e));
        garage.values().forEach(e-> System.out.println(e));
        garage.forEach((k,v)-> System.out.println(k + " " + v));


        /*Показать разницу сохранения коллекции Set в List и копирование Set в List*/
//        var collection = new HashSet<>();
//        var collection = List.of(1,2,3); //Exception in thread "main" java.lang.UnsupportedOperationException метод add т.к. НЕИЗМЕНЯЕМАЯ коллекция
        var collection = Arrays.asList(1,2,3); //Exception in thread "main" java.lang.UnsupportedOperationException метод add
        collection.add(1);
        var list1 = List.of(collection);
        collection.add(2);
        collection.add(3);
        collection.add(4);
        var list2 = List.copyOf(collection);
        System.out.println(list1); // [[1, 2, 3, 4]]
        System.out.println(list2); // [1, 2, 3, 4]

    }
}
