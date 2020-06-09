package ru.pimalex1978.list.singly_linked_list;

public class Node<E> {

    //ссылка на следующий элемент - указатель
    public Node<E> next;

    //хранимые данные
    E data;

    //конструктор
    public Node(E data) {
        this.data = data;
    }

    //конструктор
    public Node(E data, Node<E> next ) {
        this.next = next;
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }
}
