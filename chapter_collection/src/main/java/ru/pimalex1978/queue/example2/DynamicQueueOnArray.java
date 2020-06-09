package ru.pimalex1978.queue.example2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class DynamicQueueOnArray<E> implements Queue<E> {
    private E[] stack;
    private int count = 0;

    //Constructor
    public DynamicQueueOnArray() {
        this.stack = (E[]) new Object[10];
    }

    //Methods
    @Override
    public void offer(E e) {
        if (count < size()) {
            stack[count] = e;
            count++;
        } else {
            int i = size();
            i += size() / 4;
            if (i <= Integer.MAX_VALUE) {
                Arrays.copyOf(stack, i);
                stack[count] = e;
                count++;
            } else {
                Arrays.copyOf(stack, Integer.MAX_VALUE);
                stack[count] = e;
                count++;
            }
        }
    }

    @Override
    public E remove() throws NoSuchElementException, ArrayIndexOutOfBoundsException, IllegalArgumentException, NullPointerException {
        if (size() == 0)
            throw new NoSuchElementException();
        E element = stack[0];
        stack = Arrays.copyOfRange(stack, 1, size());
        count--;
        return element;
    }

    @Override
    public E poll() throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NullPointerException {
        if (size() == 0)
            return null;
        E element = stack[0];
        stack = Arrays.copyOfRange(stack, 2, size());
        count--;
        return element;
    }

    @Override
    public E element() throws NoSuchElementException {
        if (size() != 0)
            return stack[0];
        else throw new NoSuchElementException();
    }

    @Override
    public E peek() {
        if (size() != 0)
            return stack[0];
        return null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean contains(E elem) {
        for (int i = 0; i < size(); i++) {
            if (stack[i].equals(elem))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new DinamicQueueOnArrayIterator();
    }

    //Inner class
    private class DinamicQueueOnArrayIterator<E> implements Iterator<E> {
        private int iterator = 0;

        //Methods
        @Override
        public boolean hasNext() {
            return iterator != size();
        }

        @Override
        public E next() {
            return (E) stack[iterator++];
        }
    }

    public static void main(String[] args) {
        DynamicQueueOnArray<Integer> stack = new DynamicQueueOnArray<>();
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
            System.out.println(stack.remove());
        } catch (NoSuchElementException e) {
            System.out.println("Stack is empty");
        }
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
