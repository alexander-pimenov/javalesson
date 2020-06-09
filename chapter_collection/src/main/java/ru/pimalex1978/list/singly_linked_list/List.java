package ru.pimalex1978.list.singly_linked_list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class List<E> implements Iterable<E> {

    //голова нашего списка, указатель на голову
    private Node<E> head;

    //размер списка
    private int size = 0;

    //конструктор - для инициализации нашей головы head как null
    List() {
        this.head = null;
    }

    //метод проверяющий, пустой наш список или нет
    private boolean isEmpty() {
        return this.head == null;
    }

    //метод добавляющий элементы в начало списка
    public void add(E data) {

        //создаем новый узел
        Node<E> temp = new Node<E>(data);

        //укажем ссылку на следующий элемент.
        //говорим нашей промежуточной переменной temp, указывая ссылку на next,
        //что будет равняться предыдущему значению, т.е. голове head
        temp.next = this.head;

        //а сама голова head будет равняться temp
        this.head = temp;
        this.size++; //увеличиваем размер списка
    }

    //метод добавляющий элементы в конец списка
    public void addLast(E data) {

        //создаем новый узел
        Node<E> temp = new Node<E>(data, null);
        //если список пустой, то голове присваиваем новый нод
        if (head == null) {
            head = temp;
            this.size++;
            return;
        }
        Node<E> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = temp;
        this.size++;
    }

    //метод удаления элементов из списка из начала списка
    //(как и в Стеке - удаляем из начала)
    public void remove() {
        Node<E> result = this.head;
        //если список пуст, то выкинуть эксепшн
        if (result == null) {
            throw new NoSuchElementException();
        }
        //просто переуказываем ссылку head на следующую ссылку
        head = head.next;
        this.size--;
    }

    //Метод удаления первого элемента из списка
    //и возврат его значения
    public E delete() {
        Node<E> temp = this.head;
        if (temp == null) {
            throw new NoSuchElementException();
        }
        this.head = head.next;
        this.size--;
        return temp.data;
    }

    //метод удаления по ключу
    public void removeAt(E key) {
        //нужны две переменные:
        //одна текущая
        Node<E> cur = head;
        //вторая переменная предыдущая
        Node<E> prev = head;

        //проходим по  списку пока наш ключ не будет найден
        while (cur.data != key) {
            //ставим проверку на пустоту списка
            if (isEmpty()) {
                System.out.println("List is empty.");
                return; //выход из цикла
            } else {
                //путешествуем далее: prev равно текущему элементу,
                //а текущий элемент идет дальше по нашему списку
                prev = cur;
                cur = cur.next;
            }

            //определимся является ли наш текущий элемент головой

            if (cur == head) {
                head = head.next;
            } else {
                prev.next = cur.next;
            }
        }
    }

    //метод поиска индекса элемента
    public int findId(E key) {
        Node temp = head;

        //промежуточная переменная чтоб вернуть индекс позиции key
        int c = 0;

        while (temp != null) {
            if (temp.data == key) {
                return c;
            }
            temp = temp.next;
            c++;
        }

        //если такого элемента нет вообще
        return -1;
    }

    //получение элемента по индексу
    public E get(int index) {
        Node<E> temp = this.head;
        if (temp == null){
            throw new NoSuchElementException();
        }
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    //получение размера списка
    public int getSize() {
        return this.size;
    }

    //метод переворичивающий список
    public void revert() {
        Node<E> temp = head;
        Node<E> previous = null;
        Node<E> current = null;
        while (temp != null) {
            current = temp;
            temp = temp.next;
            current.next = previous;
            previous = current;
            head = current;
        }
    }

    //метод для вывода результата в консоль
    public void print() {

        //промежуточная переменная равняется нашей голове,
        //т.е. будем проходиться от начала нашего списка и будем идти до самого низа
        Node<E> temp = head;

        //пока temp не равен null перебераем наш список
        while (temp != null) {
            System.out.println(temp.data);

            //аналог i++, т.е. увеличение на 1
            temp = temp.next;
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int cursor = 0;
            Node<E> temp = head;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                E data = temp.data;
                temp = temp.next;
                cursor++;
                return data;
            }
        };
    }
}
