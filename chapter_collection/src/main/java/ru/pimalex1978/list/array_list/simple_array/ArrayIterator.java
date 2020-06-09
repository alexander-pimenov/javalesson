package ru.pimalex1978.list.array_list.simple_array;

import java.util.Iterator;

/**
 * Класс имитирующий итератор.
 * Поютому он имплиментирует интерфейс Iterator
 * и реализует два его метода: hasNext(), next().
 *
 * @param <E>
 */

public class ArrayIterator<E> implements Iterator<E> {

    private int index = 0;
    private E[] values;

    ArrayIterator(E[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    @Override
    public E next() {
        return values[index++];
    }
}
