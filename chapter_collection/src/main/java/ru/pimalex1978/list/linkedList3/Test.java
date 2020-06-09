package ru.pimalex1978.list.linkedList3;

import java.util.Iterator;

/**
 * Протестируем наш LinkedContainer.
 */

public class Test {
    public static void main(String[] args) {
        //Linked<String> stringLinked = new LinkedContainer<>(); //при такой записи не сможем пользоваться циклом foreach,
        //т.к. интерфейс Linked<E> не имплиментирует в себе интерфейс Iterable<E>

        LinkedContainer<String> stringLinked = new LinkedContainer<>();
        stringLinked.addLast("abc");
        stringLinked.addLast("de");
        System.out.println(stringLinked.size());
        System.out.println(stringLinked.getElementByIndex(0));
        System.out.println(stringLinked.getElementByIndex(1));
        System.out.println("=================");

        stringLinked.addFirst("qqq");
        stringLinked.addFirst("aaa");
        System.out.println(stringLinked.size());
        System.out.println(stringLinked.getElementByIndex(0));
        System.out.println(stringLinked.getElementByIndex(1));
        System.out.println("=================");

        //Вызываем прямую итерацию, от головы к хвосту.
        for (String s : stringLinked) {
            System.out.println(s);
        }
        System.out.println("=================");

        //Будем использовать обратную итерацию, от хвоста к голове.
        Iterator<String> descIterator = stringLinked.descendingIterator();
        while (descIterator.hasNext()) {
            System.out.println(descIterator.next());
        }


    }
}
