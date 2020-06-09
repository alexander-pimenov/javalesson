package ru.pimalex1978.stack_queue;

/**
 * Простая реализация Очереди на основе ArrayList
 * Здесь мы не добавляем, какой то новый функционал, а
 * ограничиваем функционал ArrayList, чтобы он обслуживал FIFO.
 * В каком порядке в очередь положили, в таком порядке
 * из очереди и извлекли.
 * Использовал для примера видео Sergey Arkhipov
 * https://www.youtube.com/watch?v=X07Y7pkHHJc&feature=youtu.be
 *
 * @author Alexander Pimenov (pimalex1978@ya.ru)
 * @version v1.0
 */

import java.util.ArrayList;

public class SimpleQueue<T> implements Queue<T> {
    private ArrayList<T> list = new ArrayList<>();

    @Override
    public void add(T item) {
        list.add(item); // добавляем элемент в конец очереди
    }

    @Override
    public T remove() {
        return list.remove(0); // удаляем и возвращаем элемент из начала очереди
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty(); // проверка пустой ли список
    }
}
