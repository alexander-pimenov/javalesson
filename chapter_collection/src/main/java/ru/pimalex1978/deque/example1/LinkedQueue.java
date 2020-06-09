package ru.pimalex1978.deque.example1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
    private QueueElement head;
    private QueueElement tail;
    private int size;

    public LinkedQueue() {
        this.head = null;
    }

    @Override
    public void offer(E e) {
        if (this.size != 0) {
            QueueElement newElement = new QueueElement(e, tail);
            tail.next = newElement;
            tail = newElement;
        } else {
            head = new QueueElement(e, null, null);
            tail = head;
        }
        size++;
    }

    @Override
    public E remove() {
        if (head != null) {
            E element = head.value;
            head = head.next;
            this.size--;
            return element;
        }
        throw new NoSuchElementException();
    }


    @Override
    public E poll() {
        E element = head.value;
        head = head.next;
        this.size--;
        return element;
    }

    @Override
    public E element() {
        if (head == null)
            throw new NoSuchElementException();
        else
            return head.getValue();
    }

    @Override
    public E peek() {
        return head.getValue();
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
    public boolean contains(E elem) {
        QueueElement currentElement = head;
        while (currentElement != null) {
            if (currentElement.getValue().equals(elem))
                return true;
            currentElement = currentElement.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator<E> implements Iterator<E> {
        private QueueElement currentElement;

        public LinkedQueueIterator() {
            currentElement = head;
        }

        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        @Override
        public E next() {
            E value = (E) currentElement.getValue();
            currentElement = currentElement.next;
            return value;
        }
    }

    private class QueueElement {
        private E value;
        private QueueElement previous;
        private QueueElement next;

        public QueueElement(E value, QueueElement previous, QueueElement next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        public QueueElement(E value, QueueElement previous) {
            this(value, previous, null);
        }

        public QueueElement(E value) {
            this(value, null);
        }

        public E getValue() {
            return value;
        }

    }

    public static void main(String[] args) {
        LinkedQueue<Integer> integers = new LinkedQueue<>();
        integers.offer(1);
        integers.offer(2);
        integers.offer(3);
        integers.offer(4);

        System.out.println(integers.contains(5));

        System.out.println("Remove: " + integers.remove());
        System.out.println("Remove: " + integers.remove());
        System.out.println("Remove: " + integers.remove());
        System.out.println("Remove: " + integers.remove());

        for (Integer integer : integers) {
            System.out.println(integer);
        }


    }
}
