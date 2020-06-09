package ru.pimalex1978.list.linkedList1;

/**
 * В этом классе поверяем работу нашего односвязного списка.
 */

public class Test {
    public static void main(String[] args) {
        System.out.println("Создаем и инициализируем список тремя элементами:");
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(10);

        System.out.println(myLinkedList);
        System.out.println("Получаем с помощью get() элементы списка:");
        System.out.println(myLinkedList.get(0));
        System.out.println(myLinkedList.get(1));
        System.out.println(myLinkedList.get(2));
        //System.out.println(myLinkedList.get(5));

        System.out.println("Удаляем элемент с индексом 0:");
        myLinkedList.remove(0);
        System.out.println("Оставшиеся элементы в списке:");

        System.out.println(myLinkedList);
        System.out.println("Удаляем элемент с индексом 1:");
        myLinkedList.remove(1);
        System.out.println("Оставшиеся элементы в списке:");
        System.out.println(myLinkedList);
        System.out.println("Удаляем элемент с индексом 0:");
        myLinkedList.remove(0);
        System.out.println("Оставшиеся элементы в списке:");
        System.out.println(myLinkedList);
        System.out.println("Элементов в списке нет, но мы пытаемся выполнить remove():");
        myLinkedList.remove(0);

        //myLinkedList.remove(4);

    }
}
