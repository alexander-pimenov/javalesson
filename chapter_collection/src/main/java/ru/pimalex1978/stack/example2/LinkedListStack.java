package ru.pimalex1978.stack.example2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> stack;

    //Constructor
    public LinkedListStack() {
        this.stack = new LinkedList<>();
    }

    //Methods
    @Override
    public void push(E elem) {
        stack.push(elem);
    }

    @Override
    public E pop() {
        return stack.pop();
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
    public Iterator<E> iterator() {
        return stack.iterator();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
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
