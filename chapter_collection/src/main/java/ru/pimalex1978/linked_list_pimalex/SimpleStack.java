package ru.pimalex1978.linked_list_pimalex;

/**
 * Стек - LIFO последний пришел первым ушел.
 *
 * @param <E>
 */

public class SimpleStack<E> extends SimpleDoublyLinkedList<E> {


    private SimpleDoublyLinkedList<E> linked = new SimpleDoublyLinkedList<>();

    public SimpleStack() {
    }

    public SimpleStack(SimpleDoublyLinkedList<E> linked) {
        this.linked = linked;
    }

    /**
     * Метод возвращает значение и удаляет его из коллекции.
     * Удаляет из начала списка.
     *
     * @return значение первого элемента в списке.
     */
    public E poll() {
        return linked.deleteFirst();
    }

    /**
     * Метод помещает значение в коллекцию.
     * В начало списка.
     *
     * @param value помещаемое значение.
     */
    public void push(E value) {
        linked.addFirst(value);
    }

    /**
     * Метод возвращает размер стека
     *
     * @return размер стека
     */
    public int size() {
        return linked.getSize();
    }


    /**
     * Использовал для тестирования
     *
     * @param args по умолчанию
     */
    public static void main(String[] args) {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println("0 -> " + stack.linked.get(0));
        System.out.println("1 -> " + stack.linked.get(1));
        System.out.println("2 -> " + stack.linked.get(2));
        System.out.println("3 -> " + stack.linked.get(3));

        SimpleDoublyLinkedList<Integer> linkedList = new SimpleDoublyLinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        System.out.println(linkedList.getSize());
        SimpleStack<Integer> stack2 = new SimpleStack<>(linkedList);
        System.out.println(stack2.size());
        System.out.println(stack2.linked.get(0));
        System.out.println(stack2.linked.get(1));
        System.out.println(stack2.linked.get(2));


        System.out.println("================");

        System.out.println(stack.linked.get(0));
        System.out.println(stack.linked.get(1));
        System.out.println(stack.linked.get(2));
        System.out.println(stack.linked.get(3));
        System.out.println("size " + stack.size());
        System.out.println("size " + stack.linked.getSize());

        System.out.println("==2-ва удаления==");
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println("size " + stack.size());
        System.out.println("size " + stack.linked.getSize());

        System.out.println("==после 2-х удалений");
        System.out.println(stack.linked.get(0));
        System.out.println(stack.linked.get(1));
        System.out.println("size " + stack.size());
        System.out.println("size " + stack.getSize());


    }
}
