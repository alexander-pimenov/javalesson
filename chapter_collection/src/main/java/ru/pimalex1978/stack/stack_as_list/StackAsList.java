package ru.pimalex1978.stack.stack_as_list;

import java.util.EmptyStackException;

/**
 * пример Стек, как Список.
 * https://gist.github.com/anonymous/ee9641d10c4234310665
 *
 * @param <E>
 */


public class StackAsList<E> {
    private Node<E> head;
    private int size;

    private class Node<E> {
        public Node<E> next;
        public E element;

        public Node(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }
    }

    public StackAsList() {
    }

    public E push(E element) {
        Node<E> tempObj = new Node<E>(this.head, element);
        head = tempObj;
        size++;
        return element;
    }

    public E pop() {
        checkSize();
        E obj = head.element;
        head = head.next;
        size--;
        return obj;
    }

    private void checkSize() {
        if (size == 0) {
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public int size() {
        return size;
    }

}
