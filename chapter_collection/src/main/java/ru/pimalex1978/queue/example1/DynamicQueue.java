package ru.pimalex1978.queue.example1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicQueue<E> implements Queue<E> {
    private final int INITIAL_ARRAY_LENGTH = 3;
    private int size = 0;
    private E[] array;

    public DynamicQueue() {
        this.array = (E[]) new Object[INITIAL_ARRAY_LENGTH];
    }

    @Override
    public void offer(E e) {
        if (size() != array.length)
            array[size] = e;
        else {
            array = Arrays.copyOf(array, array.length * 2);
            array[this.size] = e;
        }
        size++;
    }

    @Override
    public E remove() {
        if (this.size == 0)
            throw new NoSuchElementException();
        E element = array[0];

        for (int i = 0; i < this.size; i++) {
            if (i == array.length)
                array = Arrays.copyOf(array, array.length * 2);
            array[i] = array[i + 1];
        }

        size--;
        return element;
    }

    @Override
    public E poll() {//Берем 0 элемент и сдвигаем все остальные элементы на 1 влево.
        E element = array[0];

        for (int i = 0; i < this.size; i++) {
            if (i == array.length)
                array = Arrays.copyOf(array, INITIAL_ARRAY_LENGTH * 2);
            array[i] = array[i + 1];
        }

        size--;
        return element;
    }

    @Override
    public E element() {
        if (size == 0)
            throw new NoSuchElementException();
        return array[0];
    }

    @Override
    public E peek() {
        return array[0];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(E elem) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(elem))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator {

        private int currentElement;

        public QueueIterator() {
            this.currentElement = 0;
        }

        @Override
        public boolean hasNext() {
            return currentElement != size;
        }

        @Override
        public Object next() {
            return array[currentElement++];
        }
    }

    public static void main(String[] args) {
        DynamicQueue<Integer> integers = new DynamicQueue<>();
        integers.offer(1);
        integers.offer(2);
        integers.offer(3);
        integers.offer(4);

        for (Integer a : integers) {
            System.out.println(a);
        }
    }
}
