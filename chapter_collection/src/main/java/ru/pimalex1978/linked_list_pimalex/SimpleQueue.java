package ru.pimalex1978.linked_list_pimalex;

/**
 * Очередь - FIFO первый пришел первым ушел.
 * Очередь на двусвязном списке SimpleDoublyLinkedList, реализованном ранее.
 *
 * @param <E>
 */

public class SimpleQueue<E> extends SimpleDoublyLinkedList<E> {

    private SimpleDoublyLinkedList<E> linked = new SimpleDoublyLinkedList<>();

    /**
     * Метод возвращает значение и удаляет его из коллекции.
     * Т.е. удаляем из головы списка.
     *
     * @return значение первого элемента в списке.
     */
    public E poll() {
        return linked.deleteFirst();
    }

    /**
     * Метод помещает значение в коллекцию.
     * В конец списка.
     *
     * @param value помещаемое значение.
     */
    public void push(E value) {
        linked.addLast(value);
    }

    /**
     * Метод возвращает размер очереди.
     *
     * @return размер очереди
     */
    public int size() {
        return linked.getSize();
    }

    public static void main(String[] args) {

        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(10);
        queue.push(20);
        queue.push(30);
        System.out.println("размер очереди = " + queue.linked.getSize());
        queue.push(40);
        queue.push(50);
        System.out.println("размер очереди = " + queue.linked.getSize());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println("размер очереди = " + queue.linked.getSize());
        System.out.println("размер очереди = " + queue.size());

    }
}
