package ru.pimalex1978.stack.example2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация стека через связный список
 */

public class ConnectedListStack<E> implements Stack<E> {
    private int size;
    private Node next;

    //Constructor
    public ConnectedListStack() {
        this.size = 0;
        this.next = null;
    }

    //Methods
    @Override
    public void push(E value) {
        this.size++;
        this.next = new Node(value, next);
    }

    @Override
    public E pop() throws NoSuchElementException {
        if (size-- > 0) {
            E node = next.getValue();
            next = next.getPreviousNode();
            return node;
        } else
            throw new NoSuchElementException();
    }

    @Override
    public E peek() throws NoSuchElementException {
        if (size != 0)
            return next.getValue();
        else throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    //Inner class
    private class StackIterator implements Iterator<E> {
        private Node node;

        //Constructor
        public StackIterator() {
            this.node = next;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            E value = node.getValue();
            node = node.previousNode;
            return value;
        }
    }

    private class Node {
        private E value;
        private Node previousNode;

        private Node(E value, Node previousNode) {
            this.value = value;
            this.previousNode = previousNode;
        }

        private Node getPreviousNode() {
            return previousNode;
        }

        private E getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        ConnectedListStack<Integer> stack = new ConnectedListStack<>();
        System.out.println("Is stack empty? " + stack.isEmpty());
        stack.push(6);
        stack.push(9);
        System.out.println("Is stack empty? " + stack.isEmpty());
        System.out.println("Stack size = " + stack.size());

        System.out.println("Iteration");
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());

        System.out.println("Main methods");
        try {
            System.out.println(stack.peek());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
        } catch (NoSuchElementException e) {
            System.out.println("Stack is empty");
        }
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
