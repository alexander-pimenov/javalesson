package ru.pimalex1978.queue.example2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ConnectedListQueue<E> implements Queue<E> {
    private QueueElement head;
    private QueueElement tail;
    private int size;

    public ConnectedListQueue() {
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
        ConnectedListQueue<Integer> stack = new ConnectedListQueue<>();
        System.out.println("Is stack empty? " + stack.isEmpty());
        stack.offer(6);
        stack.offer(9);
        System.out.println("Is stack empty? " + stack.isEmpty());
        System.out.println("Stack size = " + stack.size());

        System.out.println("Iteration");
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());

        System.out.println("Main methods");
        try {
            System.out.println(stack.peek());
            System.out.println(stack.remove());
            System.out.println(stack.remove());
            System.out.println(stack.remove());
        } catch (NoSuchElementException e) {
            System.out.println("Stack is empty");
        }
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
