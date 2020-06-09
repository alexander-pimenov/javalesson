package ru.pimalex1978.stack.example1;

/**
 * Пример этих интерфейсов и классов взят по адресу
 * https://gist.github.com/anonymous/6a64bd70922bd4b5fd59
 */

public interface Stack<E> extends Iterable<E> {
    /**
     * Pushes an element onto the stack
     *
     * @param elem the element to push
     */
    void push(E elem);

    /**
     * Retrieves and removes the first element of this stack
     *
     * @return the head of this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    E pop();

    /**
     * Retrieves, but does not remove, the first element of this stack
     *
     * @return the head of this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    E peek();

    /**
     * Returns the number of elements in this stack.  If this stack contains
     * more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this stack
     */
    int size();

    /**
     * Returns <tt>true</tt> if this stack contains no elements.
     *
     * @return <tt>true</tt> if this stack contains no elements
     */
    boolean isEmpty();
}
