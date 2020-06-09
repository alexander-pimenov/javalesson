package ru.pimalex1978.stack.example1;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListStack<E> implements Stack<E> {
    LinkedList<E> list;

    public LinkedListStack() {
        this.list = new LinkedList<>();
    }

    @Override
    public void push(E elem) {
        this.list.push(elem);
    }

    @Override
    public E pop() {
        return this.list.pop();
    }

    @Override
    public E peek() {
        return this.list.peek();
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.push("1");
        linkedList.push("2");
        linkedList.push("3");

        System.out.println(linkedList.peek());
    }
}
