package ru.pimalex1978.stack.stack_as_array;

import java.util.EmptyStackException;

/**
 * пример Стек, как Массив.
 * https://gist.github.com/anonymous/ee9641d10c4234310665
 * @param <E>
 */

public class StackAsArray<E> {
    private Object[] array = new Object[10];
    private int size = 10;
    private int elementCount;
    public StackAsArray() {}

    public E push(E element){
        if (elementCount == size){
            expandArray();
        }
        addElement(element);
        return element;
    }

    public E pop(){
        checkSize();
        Object element = array[elementCount-1];
        array[elementCount-1] = null;
        elementCount--;
        return (E)element;
    }

    private void checkSize() {
        if(elementCount == 0) {
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty(){
        return elementCount == 0 ? true : false;
    }

    public int size(){
        return elementCount;
    }


    private void addElement(E element) {
        array[elementCount] = element;
        elementCount++;
    }

    private void expandArray() {
        Object[] tempArray = new Object[(int)(size*1.5)];
        for (int i = 0; i < size; i++){
            tempArray[i] = array[i];
        }
        array = tempArray;
        size = array.length;
    }
}
