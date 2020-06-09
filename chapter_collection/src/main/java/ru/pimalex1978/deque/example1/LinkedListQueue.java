package ru.pimalex1978.deque.example1;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListQueue<E> implements Queue<E> {
    private LinkedList<E> list;

    public LinkedListQueue() {
        this.list = new LinkedList<>();
    }

    @Override
    public E peek() {
        return list.peek();
    }

    @Override
    public E element() {
        return list.element();
    }

    @Override
    public E poll() {
        return list.poll();
    }

    @Override
    public E remove() {
        return list.remove();
    }


    @Override
    public void offer(E e) {
        list.offer(e);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(Object o) {
        return list.contains(o);
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> integers = new LinkedListQueue<>();

        integers.offer(1);
        integers.offer(2);
        integers.offer(4);
        integers.offer(5);

        for (Integer a : integers) {
            System.out.println(a);
        }
    }
}
