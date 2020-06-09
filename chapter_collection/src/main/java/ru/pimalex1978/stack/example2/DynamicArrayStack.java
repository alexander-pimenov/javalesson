package ru.pimalex1978.stack.example2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация стека через динамический массив
 */
public class DynamicArrayStack<E> implements Stack<E> {
    private ArrayList<E> stack;

    //Constructor
    public DynamicArrayStack() {
        this.stack = new ArrayList<>();
    }

    //Methods
    @Override
    public void push(Object elem) {
        this.stack.add((E) elem);
    }

    @Override
    public Object pop() throws NoSuchElementException {
        if (size() != 0) {
            return stack.remove(size() - 1);
        }
        throw new NoSuchElementException();
    }

    @Override
    public Object peek() throws NoSuchElementException {
        if (size() != 0)
            return stack.get(size() - 1);
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator iterator() {
        return new DynamicStackIterator();
    }

    //Inner class
    private class DynamicStackIterator implements Iterator<E> {
        private int count = 0;

        @Override
        public boolean hasNext() {
            if (count < size() - 1)
                return true;
            return false;
        }

        @Override
        public E next() {
            return stack.get(count++);
        }
    }

    public static void main(String[] args) {
        DynamicArrayStack<Integer> stack = new DynamicArrayStack();
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
