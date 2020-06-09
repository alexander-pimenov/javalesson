package ru.pimalex1978.list.linkedList3;

/**
 * В этот интерфейс пропишем все методы, которые нам нужны
 * в нашем ссылочном контейнере LinkedContainer<E>
 */

public interface Linked<E> {


    //1. Вставка элемента в конец списка.
    void addLast(E e);

    //2. Вставка элемента в начало списка.
    void addFirst(E e);

    //3. Возвращение размера списка, счетчик для количества элементов.
    int size();

    //4. Возвращение элемента по индексу.
    E getElementByIndex(int counter);
}
