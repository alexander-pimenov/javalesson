package ru.pimalex1978.queue.example2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Queue<E> {
    LinkedList<E> stack;

    //Constructor
    public LinkedListQueue() {
        stack = (LinkedList<E>) new LinkedList<E>();
    }

    @Override
    public void offer(E o) {
        stack.offer(o);
    }

    @Override
    public E remove() throws IndexOutOfBoundsException {
        return stack.remove(0);
    }

    @Override
    public E poll() {
        return stack.poll();
    }

    @Override
    public E element() throws NoSuchElementException {
        return stack.element();
    }

    @Override
    public E peek() {
        return stack.peek();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public boolean contains(E elem) {
        return stack.contains(elem);
    }

    @Override
    public Iterator iterator() {
        return stack.iterator();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> stack = new LinkedListQueue<>();
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
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Stack is empty");
        }
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
