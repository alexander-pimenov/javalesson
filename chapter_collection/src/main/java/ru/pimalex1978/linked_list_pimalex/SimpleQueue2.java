package ru.pimalex1978.linked_list_pimalex;

/**
 * Простая очередь SimpleQueue на двух стеках SimpleStack,
 * реализованных ранее.
 *
 * @param <E>
 */

public class SimpleQueue2<E> extends SimpleStack<E> {

    private SimpleStack<E> firstStack;
    private SimpleStack<E> secondStack;
    private int size;

    public SimpleQueue2() {
        this.firstStack = new SimpleStack<>();
        this.secondStack = new SimpleStack<>();
    }

    /**
     * Метод добавляет элемент в очередь
     * (Используется первый стек)
     *
     * @param value добавляемый элемент
     */
    public void push(E value) {
        firstStack.push(value);
        size++;
    }

    /**
     * Метод берет элемент из очереди по FIFO
     *
     * @return первый элемент из очереди
     */
    public E poll() {
        if (secondStack.size() == 0) {
            while (firstStack.size() != 0) {
                secondStack.push(firstStack.poll());
            }
        }
        E result = secondStack.poll();
        size--;
        return result;
    }

    /**
     * Метод возвращает размер очереди
     *
     * @return размер очереди
     */
    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        SimpleQueue2<Integer> simpleQueue2 = new SimpleQueue2<>();
        simpleQueue2.push(10);
        simpleQueue2.push(20);
        simpleQueue2.push(30);
        System.out.println("size of queue = " + simpleQueue2.size());
        simpleQueue2.push(40);
        simpleQueue2.push(50);
        System.out.println("size of queue = " + simpleQueue2.size());
        System.out.println("=====delete items=====");
        System.out.println(simpleQueue2.poll());
        System.out.println("size of queue = " + simpleQueue2.size());
        System.out.println(simpleQueue2.poll());
        System.out.println("size of queue = " + simpleQueue2.size());
        System.out.println("=====add items=====");
        simpleQueue2.push(60);
        simpleQueue2.push(70);
        System.out.println("size of queue = " + simpleQueue2.size());
        System.out.println("=====delete items=====");
        System.out.println(simpleQueue2.poll());
        System.out.println(simpleQueue2.poll());
        System.out.println(simpleQueue2.poll());
        System.out.println(simpleQueue2.poll());
        System.out.println("size of queue = " + simpleQueue2.size());
        System.out.println("=====add items=====");
        simpleQueue2.push(80);
        simpleQueue2.push(90);
        System.out.println("size of queue = " + simpleQueue2.size());
        System.out.println("=====delete items=====");
        System.out.println(simpleQueue2.poll());
        System.out.println(simpleQueue2.poll());

        System.out.println("size of queue = " + simpleQueue2.size());

    }

}
