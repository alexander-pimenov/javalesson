package ru.pimalex1978.list.array_list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements Iterable<E> {
    private Object[] elementData;

    private int size = 0;
    private int modCount = 0;

    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Default constructor.
     */
    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructor with capacity.
     */
    public MyArrayList(int capacity) {
        this.elementData = new Object[capacity];
    }

    /**
     * Adds value to the end of the array and grow it if needed.
     */
    public void add(E value) {
        if (size == this.elementData.length) {
            this.elementData = Arrays.copyOf(this.elementData, this.elementData.length * 3 / 2);
        }
        this.elementData[size] = value;
        size++;
        modCount++;
    }

    /**
     * Gets element by index.
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) this.elementData[index];
    }

    /**
     * Gets this array size.
     */
    public int size() {
        return this.size;
    }

    /**
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int position = 0;
            private int expectedModCount = MyArrayList.this.modCount;

            @Override
            public boolean hasNext() {
                if (this.expectedModCount != MyArrayList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                return MyArrayList.this.size > this.position;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                if (this.expectedModCount != MyArrayList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) MyArrayList.this.elementData[this.position++];
            }
        };
    }
}
