package ru.pimalex1978.stream.onCats;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {


        Cat catOne = new Cat("Vaska", 2, "White");
        Cat catTwo = new Cat("Murka", 3, "Red");
        Cat catTree = new Cat("Bonifaciy", 5, "Black");
        Cat catFour = new Cat("Kuza", 1, "Gray");
        Cat catFive = new Cat("Umka", 7, "Black & White");

        List<Cat> catList = new ArrayList<>();
        catList.add(catOne);
        catList.add(catTree);
        catList.add(catFour);
        catList.add(catTwo);
        catList.add(catFive);

        printList(catList);

        System.out.println("===============");

        //используем stream, сортируем котов, кто старше 3 лет
        catList.stream()
                .filter(cat -> cat.getAge() > 3)
                .forEach(System.out::println);

        //или так:
        List<Cat> catByAge = catList.stream()
                .filter(cat -> cat.getAge() > 3)
                .collect(Collectors.toList());
        //System.out.println(catByAge);
        printList(catByAge);

        //или так:
        ArrayList<Cat> catByAge2 = catList.stream()
                .filter(n -> n.getAge() > 3)
                .collect(Collectors.toCollection(ArrayList::new));
        //System.out.println(catByAge2);
        printList(catByAge2);

        //Добавили метод map: напоминаю, он трансформирует один тип в другой
        //в примере был тип Cat, а получился String
        ArrayList<String> catByAgeName = catList.stream()
                .filter(n -> n.getAge() > 3)
                .map(n -> n.getName())
                .collect(Collectors.toCollection(ArrayList::new));
        //System.out.println(catByAge2);
        printList(catByAgeName);

        //Добавили метод sorted: напоминаю, он использует Comparator
        //зададим условие сравнивания: (a, b) -> a.length() - b.length()
        //или так - Comparator.comparingInt(String::length)
        ArrayList<String> catByAgeNameSort = catList.stream()
                .filter(n -> n.getAge() > 3)
                .map(n -> n.getName())
                .sorted((a, b) -> a.length() - b.length())
                .collect(Collectors.toCollection(ArrayList::new));
        //System.out.println(catByAge2);
        printList(catByAgeNameSort);


    }

    public static void printList(List<?> list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }
}
