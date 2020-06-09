package ru.pimalex1978.list.array_list;

import java.util.*;

/**
 * Необходимо создать динамический контейнер с методами:
 * <p>
 * 1) add(E value);
 * <p>
 * 2) E get(int index);
 * <p>
 * 3) реализовать интерфейс Iterable<E>.
 * <p>
 * Внутри контейнер должен базироваться на массиве (Object[] container).
 * <p>
 * Контейнер должен быть динамическим, т.е. при полном заполнении увеличиваться.
 * <p>
 * Итератор должен реализовывать fail-fast поведение, т.е. если с момента создания
 * итератора коллекция подверглась структурному изменению, итератор должен кидать
 * ConcurrentModificationException.
 * <p>
 * Это достигается через введение счетчика изменений - modCount. Каждая операция,
 * которая структурно модифицирует коллекцию должна инкрементировать этот счетчик.
 * В свою очередь итератор запоминает значение этого счетчика на момент своего создания
 * (expectedModCount), а затем на каждой итерации сравнивает сохраненное значение,
 * с текущим значением поля modCount, если они отличаются, то генерируется исключение.
 * <p>
 * Использовать стандартные коллекции JDK (ArrayList, LinkedList и т.д.) запрещено.
 */

public class DynamicSimpleArrayList<E> implements Iterable<E> {

    //наш контейнер базируется на массиве объектов Object[]
    private Object[] container;
    //поле, которое хранит текущий размер контейнера
    private int size;

    /**
     * Этот счетчик показывает количество раз, когда этот список был структурно изменен.
     * Структурные изменения - это изменения, которые изменяют размер списка или иным образом нарушают
     * его таким образом, что выполняемые итерации могут давать неверные результаты.
     *      
     * Это поле используется реализацией итератора и списка итераторов, возвращаемых методами iterator и listIterator.
     * Если значение этого поля неожиданно изменится, итератор (или итератор списка) сгенерирует исключение
     * ConcurrentModificationException в ответ на операции next, remove, previous, set или add.
     * Это обеспечивает поведение без сбоев, а не недетерминированное поведение при одновременной модификации
     * во время итерации.
     *      
     * Использование этого поля подклассами не является обязательным. Если подкласс хочет предоставить
     * отказоустойчивые итераторы (и списочные итераторы), он просто должен увеличить это поле в своих
     * методах add (int, E) и remove (int) (и любых других методах, которые он переопределяет что приводит
     * к структурным изменениям в списке). Один вызов add (int, E) или remove (int) должен добавить не более
     * одного в это поле, иначе итераторы (и итераторы списка) сгенерируют поддельные ConcurrentModificationExceptions.
     * Если реализация не желает предоставлять отказоустойчивые итераторы, это поле можно игнорировать.
     */
    //счетчик структурных изменений нашего контейнера
    private int modCount;
    //начальная емкость по умолчанию
    private static final int DEFAULT_CAPACITY = 10;

//    public DynamicSimpleArrayList() {
//        this(DEFAULT_CAPACITY);
//    }

    public DynamicSimpleArrayList() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    public DynamicSimpleArrayList(int initialCapacity) {
        this.container = new Object[initialCapacity];
        modCount = 0;
    }

    /**
     * Вычисляет новый размер массива
     */
    private int newCapacity() {
        int oldCapacity = container.length;
        return oldCapacity + (oldCapacity >> 1);
    }

    /**
     * Увеличиваем массив
     *
     * @return новый увеличенный массив
     */
    private Object[] grow() {
        container = Arrays.copyOf(container, newCapacity());
        return container;
    }

    /**
     * Добавляем объект в коллекцию
     *
     * @param value объект для добавления
     */
    public void add(E value) {
        modCount++;
        add(value, container, size);
    }

    /**
     * Добавляет объект в коллекцию
     *
     * @param e           объект для добавления.
     * @param elementData коллекция в которую будет добавлен объект
     * @param s           текущий размер коллекции
     */
    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = e;
        size = s + 1;
    }

    /**
     * Получаем объект из колелкции
     *
     * @param index индекс объекта
     * @return объект по заданному индексу
     */
    public E get(int index) {
        Objects.checkIndex(index, size);
        //noinspection unchecked
        return (E) container[index];
    }

    /**
     * Возвращает итератор для коллекции
     *
     * @return итератор для коллекции
     */
    public Iterator<E> iterator() {
        //noinspection unchecked
        return new DynamicSimpleArrayList.Itr();
    }

    /**
     * Итератор коллекции с проверкой модификации коллекции при обходе.
     */
    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        final int expectedModCount = modCount;

        // prevent creating a synthetic constructor
        Itr() {
        }

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] elementData = DynamicSimpleArrayList.this.container;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            return (E) elementData[i];
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

}
