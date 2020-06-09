package ru.pimalex1978.stack.example1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E> {
    private int size;
    private StackElement currentStackElement;

    public LinkedStack() {
        this.currentStackElement = null;
        this.size = 0;
    }

    @Override
    public void push(E elem) {
        currentStackElement = new StackElement(elem, currentStackElement);
        size++;
    }

    @Override
    public E pop() {
        if (size-- > 0) {
            E element = currentStackElement.getValue();
            currentStackElement = currentStackElement.getPrevStackElement();
            return element;
        } else
            throw new NoSuchElementException();
    }

    @Override
    public E peek() {
        if (size == 0)
            throw new NoSuchElementException();
        else
            return currentStackElement.getValue();
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
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    public class StackIterator implements Iterator<E> {

        private StackElement currentIterateElement;

        public StackIterator() {
            this.currentIterateElement = currentStackElement;
        }

        @Override
        public boolean hasNext() {
            return currentIterateElement != null;
        }

        @Override
        public E next() {
            E elem = currentIterateElement.getValue();
            currentIterateElement = currentIterateElement.previousStackElement;
            return elem;
        }
    }

    private class StackElement {
        private E value;
        private StackElement previousStackElement;

        private StackElement(E value, StackElement previousStackElement) {
            this.value = value;
            this.previousStackElement = previousStackElement;
        }

        private StackElement getPrevStackElement() {
            return previousStackElement;
        }

        private E getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        LinkedStack<String> strings = new LinkedStack<>();
        strings.push("1");
        strings.push("2");
        strings.push("3");
        strings.push("4");

        String b = strings.pop();

        System.out.println("removed: " + b);

        strings.push("4");

        for (String a : strings) {
            System.out.println(a);
        }


    }
}
