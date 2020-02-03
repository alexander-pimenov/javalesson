package ru.pimalex1978.duplicate;

import java.util.*;

/**
 * Как считать дублированные элементы в списке Java
 * Пример Java, показывающий, как подсчитать общее количество дублированных записей
 * в списке, используя Collections.frequency и Map.
 */

public class CountDuplicatedList {
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("b");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("c");
        list.add("c");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");

        System.out.println("\nExample 1 - Count 'a' with frequency");
        System.out.println("a : " + Collections.frequency(list, "a"));

        System.out.println("\nExample 2 - Count all with frequency");
        //Определяем уникальные значения, поместив list с дубликатами в HashSet
        Set<String> uniqueSet = new HashSet<String>(list);
        for (String temp : uniqueSet) {
            System.out.println(temp + ": " + Collections.frequency(list, temp));
        }

        System.out.println("\nExample 3 - Count all with Map");
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String temp : list) {
            //При самом первом вызове count примет значение null
            //а далее, если элемент будет встречаться еще и еще, будет увеличиваться на 1
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        printMap(map);

        System.out.println("\nSorted Map");
        Map<String, Integer> treeMap = new TreeMap<String, Integer>(map);
        printMap(treeMap);

    }

    public static void printMap(Map<String, Integer> map) {

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : "
                    + entry.getValue());
        }
    }
}
