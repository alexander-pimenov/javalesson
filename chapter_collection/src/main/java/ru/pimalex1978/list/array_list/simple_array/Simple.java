package ru.pimalex1978.list.array_list.simple_array;

/**
 * Для того чтобы мы могли итерироваться по нашему простому
 * динамическому ArrayList, мы расширяем интерфейс Iterable.
 * Класс, который будет реализовывать этот интерфейс Simple,
 * а это будет SimpleArray, будет реализовывать и
 * представленные ниже методы и метод Iterator(),
 * который будет возвращать нам Итератор.
 *
 * @param <E>
 */

public interface Simple<E> extends Iterable<E> {
    boolean add(E e);

    void delete(int index);

    E get(int index);

    int size();

    void update(int index, E e);
}
