package ru.pimalex1978.stack.example1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicStack<E> implements Stack<E> {
    private final int INITIAL_ARRAY_LENGTH = 3;
    private int size = 0;
    private E[] array;

    public DynamicStack() {
        this.array = (E[]) new Object[INITIAL_ARRAY_LENGTH];
    }

    @Override
    public void push(E elem) {
        if (size != array.length) {
            array[this.size] = elem;
        } else {
            array = Arrays.copyOf(array, array.length * 2);
            array[this.size] = elem;
        }
        size++;
    }

    @Override
    public E pop() {
        if (this.size != 0) {
            E elem = array[size - 1];
            size--;
            return elem;
        } else
            throw new NoSuchElementException();
    }

    @Override
    public E peek() {
        if (this.size != 0)
            return array[size - 1];
        else
            throw new NoSuchElementException();
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
    public Iterator<E> iterator() {
        return new DynamicStackIterator();
    }

    private class DynamicStackIterator implements Iterator<E> {

        private int currentPosition;

        public DynamicStackIterator() {
            this.currentPosition = size;
        }

        @Override
        public boolean hasNext() {
            return currentPosition > 0;
        }

        @Override
        public E next() {
            currentPosition--;
            return array[currentPosition];
        }
    }

    public static void main(String[] args) {
        DynamicStack<Integer> a = new DynamicStack<>();
        a.push(1);
        a.push(2);
        a.push(3);
        a.push(4);

        Iterator<Integer> iterator = a.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
