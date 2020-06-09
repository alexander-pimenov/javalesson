package ru.pimalex1978.list.array_list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomArrayList<E> implements Iterable<E> {
    private Object[] values;
    private int size = 0;
    private int capacity = 10;
    private int modCount = 0;

    public CustomArrayList() {
        values = new Object[capacity];
    }

    public CustomArrayList(int customCapacity) {
        values = new Object[customCapacity];
    }

    public void add(E value) {
        if (this.size >= capacity) {
            this.capacity = capacity * 3 / 2;
            this.values = Arrays.copyOf(this.values, this.capacity);
        }
        this.values[size] = value;
        size++;
        this.modCount++;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        E result = null;
        if (index < this.size) {
            result = (E) this.values[index];
        }
        return result;
    }

    public int length() {
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<E> iterator() {
        return new Iterator() {
            private final int expectedModCount = modCount;
            private int itIdx = 0;

            @Override
            public boolean hasNext() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return this.itIdx < size;
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) values[itIdx++];
            }
        };
    }
}
