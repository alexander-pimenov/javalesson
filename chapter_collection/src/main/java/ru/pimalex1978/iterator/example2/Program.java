package ru.pimalex1978.iterator.example2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Примеры работы Iterator и ListIterator
 */
public class Program {
    public static void main(String[] args) {

        ArrayList<String> states = new ArrayList<String>();
        states.add("Germany");
        states.add("France");
        states.add("Italy");
        states.add("Spain");
        System.out.println("Размер списка: " + states.size());

        Iterator<String> iter = states.iterator();
        while (iter.hasNext()) {

            System.out.println(iter.next());
//            iter.remove(); //можно посмотреть на работу удаления элементов
        }

        System.out.println("Размер списка: " + states.size());
        System.out.println("====================");

        ArrayList<String> states2 = new ArrayList<String>();
        states2.add("Germany");
        states2.add("France");
        states2.add("Italy");
        states2.add("Spain");
        states2.add("Poland");
        states2.add("Bulgary");
        states2.add("Ukraine");
        states2.add("Russian");
        states2.add("Italy");
        states2.add("Chili");
        states2.add("China");
        states2.add("India");
        states2.add("Australia");
        states2.add("Brasilia");
        states2.add("Kongo");
        states2.add("Angola");
        System.out.println("Список states2: " + states2);

        ListIterator<String> listIter = states2.listIterator();

        while (listIter.hasNext()) {

            System.out.println(listIter.next());
        }
        // сейчас текущий элемент - Испания
        System.out.println("Последний элемент в списке: " + states2.get(states2.size() - 1));

        // изменим значение этого элемента
        listIter.set("Португалия");
        // пройдемся по элементам в обратном порядке
        while (listIter.hasPrevious()) {

            System.out.println(listIter.previous());
        }
        listIter.add("USA"); // добавляет в то мето где стоит в данный момент. Сейчас стоит в начале
        states2.add("Canada"); // добавляет в конец списка

        System.out.println("Список states2: " + states2);
        states2.remove(0);
        states2.remove(0);
        states2.remove(0);
        System.out.println("Список states2: " + states2);
    }
}
