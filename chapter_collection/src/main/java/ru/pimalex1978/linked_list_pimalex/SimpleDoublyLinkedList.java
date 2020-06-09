package ru.pimalex1978.linked_list_pimalex;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleDoublyLinkedList<E> implements Iterable<E> {

    //В этом поле храним размер нашего списка.
    private int size;

    //Голова списка, нужна чтобы мы могли добавлять элементы.
    //Указатель на первый элемент (узел). В самом начале он пуст.
    private Node<E> first;

    //Хвост списка, нужен чтобы мы могли добавлять элементы.
    //Указатель на последний элемент. В самом начале он пуст.
    private Node<E> last;

    //Счетчик структурных изменений нашего связного листа.
    private int modCount;

    /**
     * Конструктор по умолчанию.
     */
    public SimpleDoublyLinkedList() {
    }

    /**
     * Метод вставляет в НАЧАЛО списка данные.
     * (очень похоже на Stack - добавляем в начало.)
     */
    public void addFirst(E data) {
        Node<E> newLink = new Node<>(data); //создаем новый элемент
        //укажем ссылку на следующий элемент.
        //говорим нашей промежуточной переменной newLink, указывая ссылку на next,
        //что будет равняться предыдущему значению, т.е. голове head
        newLink.next = this.first;
        //а сама голова head будет равняться newLink
        this.first = newLink;
        this.size++;
        modCount++;
    }

    /**
     * Метод вставляет в КОНЕЦ списка данные.
     */
    public void addLast(E data) {
        Node<E> prev = last;
        Node<E> newLink = new Node<>(prev, data, null); //создаем новый элемент
        last = newLink;
        if (prev == null) {
            first = newLink;
        } else {
            prev.next = newLink;
        }
        this.size++;
        modCount++;
    }


    /**
     * Метод реализующий удаления ПЕРВОГО элемента в списке
     * и возвращающий его значение.
     * (очень похоже на Stack - удаляем из начала.)
     */
    public E deleteFirst() {
        Node<E> result = this.first;
        if (result == null) {
            throw new NoSuchElementException();
        }
        //просто переуказываем ссылку first на следующую ссылку
        this.first = first.next;
        this.size--;
        modCount++;
        return result.data;
    }

    /**
     * Метод реализующий удаления ПОСЛЕДНЕГО элемента в списке
     * и возвращающий его значение.
     */
    public E deleteLast() {
        Node<E> result = this.last;
        if (result == null) {
            throw new NoSuchElementException();
        }
        final E element = result.data;
        Node<E> prev = result.prev;
        result.data = null;
        result.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }

        this.size--;
        modCount++;
        return element;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        if (result == null) {
            throw new NoSuchElementException();
        }
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++) {
            result = result.next; //при каждом проходе, мы перезаписываем result
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Метод получения Итератора.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int cursor = 0;
            //Счетчик изменений в нашем связном списке.
            private int expectedModCount = SimpleDoublyLinkedList.this.modCount;

            Node<E> temp = first;

            @Override
            public boolean hasNext() {
                if (this.expectedModCount != SimpleDoublyLinkedList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public E next() {
                if (this.expectedModCount != SimpleDoublyLinkedList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                E value = temp.getData();
                temp = temp.next;
                cursor++;
                return value;
            }
        };
    }

    /**
     * Класс предназначенный для хранения данных.
     * Приватный статический вложенный класс.
     * Он хранит в себе указатель на следующий узел и само значение объекта,
     * которое подставляется при создании узла в конструкторе.
     */
    private static class Node<E> {

        E data; // данные хранящиеся в текущем узле
        Node<E> next; // указатель (ссылка) на следующий элемент
        Node<E> prev; // указатель (ссылка) на предыдущий элемент

        /**
         * Создадим конструктор, который будет приниматьна на вход значение.
         * Т.к. при создании нового узла мы будем подавать на вход ему значение.
         *
         * @param data значение хранимого элемента в SimpleDoublyLinkedList<E>.
         */

        Node(E data) {
            this.data = data;
        }

        public Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        @Override
        public String toString() {
            return String.format("Value is %s", data);
        }
    }

}


//    //        @Override
//    //        public Iterator<E> iterator() {
//    //            return new ArrayItr<>(a);
//    //        }
//    //    }
//    //
//    //    private static class ArrayItr<E> implements Iterator<E> {
//    //        private int cursor;
//    //        private final E[] a;
//    //
//    //        ArrayItr(E[] a) {
//    //            this.a = a;
//    //        }
//    //
//    //        @Override
//    //        public boolean hasNext() {
//    //            return cursor < a.length;
//    //        }
//    //
//    //        @Override
//    //        public E next() {
//    //            int i = cursor;
//    //            if (i >= a.length) {
//    //                throw new NoSuchElementException();
//    //            }
//    //            cursor = i + 1;
//    //            return a[i];
//    //        }
//    //    }


    /*Получение итератора по образу LinkedList

        public ListIterator<E> listIterator(int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }
    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

//AbstractList
protected transient int modCount = 0;

    public Iterator<E> iterator() {
        return new Itr();
    }

    //    private class Itr implements Iterator<E> {
    //        /**
    //         * Index of element to be returned by subsequent call to next.
    //         */
//        int cursor = 0;
//
//        /**
//         * Index of element returned by most recent call to next or
//         * previous.  Reset to -1 if this element is deleted by a call
//         * to remove.
//         */
//        int lastRet = -1;
//
//        /**
//         * The modCount value that the iterator believes that the backing
//         * List should have.  If this expectation is violated, the iterator
//         * has detected concurrent modification.
//         */
//        int expectedModCount = modCount;
//
//        public boolean hasNext() {
//            return cursor != size();
//        }
//
//        public E next() {
//            checkForComodification();
//            try {
//                int i = cursor;
//                E next = get(i);
//                lastRet = i;
//                cursor = i + 1;
//                return next;
//            } catch (IndexOutOfBoundsException e) {
//                checkForComodification();
//                throw new NoSuchElementException();
//            }
//        }
//
//        public void remove() {
//            if (lastRet < 0)
//                throw new IllegalStateException();
//            checkForComodification();
//
//            try {
//                AbstractList.this.remove(lastRet);
//                if (lastRet < cursor)
//                    cursor--;
//                lastRet = -1;
//                expectedModCount = modCount;
//            } catch (IndexOutOfBoundsException e) {
//                throw new ConcurrentModificationException();
//            }
//        }
//
//        final void checkForComodification() {
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//        }
//    }


//        private void checkPositionIndex(int index) {
//        if (!isPositionIndex(index))
//            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//    }
//


/*        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }


        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

        private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }



    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

            public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

        /**
     * Unlinks non-null first node f.
     */
//private E unlinkFirst(LinkedList.Node<E> f) {
//    // assert f == first && f != null;
//    final E element = f.item;
//    final LinkedList.Node<E> next = f.next;
//    f.item = null;
//    f.next = null; // help GC
//    first = next;
//    if (next == null)
//        last = null;
//    else
//        next.prev = null;
//    size--;
//    modCount++;
//    return element;
//}
