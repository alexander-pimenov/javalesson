package ru.pimalex1978.list.linkedList3;

import java.util.Iterator;

/**
 * Интерфейс для итерирования в обратном порядке.
 * Возвращает итератор для элементов в нашем списке в обратном последовательном порядке.
 * Элементы будут возвращены в порядке от последнего (хвост) до первого (голова).
 * <p>
 * Можно конечно имплиментировать интерфейс Deque<E>, в которомлежит такой же метод Iterator<E> descendingIterator();,
 * но тогда нужно реализовывать еще нескольок других методов интерфейса Deque<E>.
 * А нам это не нужно. Поэтому сделали свой интерфейс  interface DescendingIterator<E> с методом
 * Iterator<E> descendingIterator().
 */

public interface DescendingIterator<E> {
    Iterator<E> descendingIterator();
}
