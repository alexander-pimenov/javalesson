package ru.pimalex1978.mapdemo;

import java.util.HashMap;
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




    }
}
