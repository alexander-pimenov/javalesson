package ru.pimalex1978.linked_list_pimalex;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Контейнер для данных, базирующийся на двусвязном списке.
 * Класс SimpleLinkedList.
 */


public class SimpleLinkedList<E> implements Iterable<E> {
    //В этом поле храним размер нашего списка.
    private int size = 0;

    //Голова списка, нужна чтобы мы могли добавлять элементы.
    //Указатель на первый элемент. В самом начале он пуст.
    private Node<E> first;

    //Хвост списка, нужен чтобы мы могли добавлять элементы.
    //Указатель на последний элемент. В самом ачале он пуст.
    private Node<E> last;

    //ниже показаны Первый и Последний элементы списка:
    //null<-prevElement[fstNode(e=null)]nextElement-> <-prevElement[lstNode(e=null)]nextElement->null
    //ниже показаны Первый, последующий и Последний элементы списка:
    //null<-prevElement[fstNode(e=null)]nextElement-> <-prevElement[Node(e=value)]nextElement-> <-prevElement[lstNode(e=null)]nextElement->null

    //Счетчик структурных изменений нашего связного листа
    private int modCount;


    /**
     * Метод вставляет в НАЧАЛО списка данные.
     * (очень похоже на Stack - добавляем в начало.)
     */
    public void add(E data) {
        //создаем новый узел
        Node<E> newLink = new Node<>(data); //создаем новый элемент
        //укажем ссылку на следующий элемент.
        //говорим нашей промежуточной переменной newLink, указывая ссылку на next,
        //что будет равняться предыдущему значению, т.е. голове head
        newLink.next = this.first;
        //а сама голова head будет равняться newLink
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод реализующий удаления ПЕРВОГО элемента в списке
     * и возвращающий его значение.
     * (очень похоже на Stack - удаляем из начала.)
     */
    public E delete() {
        Node<E> result = this.first;
        if (result == null) {
            throw new NoSuchElementException();
        }
        //просто переуказываем ссылку first на следующую ссылку
        this.first = first.next;
        this.size--;
        return result.data;
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
            Node<E> temp = first;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
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

        E data; //значение хранящееся в текущем узле
        Node<E> next; //ссылка на следующий элемент
        Node<E> prev; //ссылка на предыдущий элемент

        /**
         * Создадим конструктор, который будет приниматьна на вход значение.
         * Т.к. при создании нового узла мы будем подавать на вход ему значение.
         *
         * @param data значение хранимого элемента в SimpleArrayList<E>.
         */

        Node(E data) {
            this.data = data;
        }

        Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public E getData() {
            return data;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }
}
