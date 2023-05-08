package ru.pimalex1978.stack_queue;

import java.util.ArrayList;

/**
 * Простая реализация Стека на основе ArrayList
 * Здесь мы не добавляем, какой-то новый функционал, а
 * ограничиваем функционал ArrayList, чтобы он обслуживал FILO.
 * В прямом порядке в стек положили, а извлекли в обратнои
 * порядке.
 * Использовал для примера видео Sergey Arkhipov
 * <a href="https://www.youtube.com/watch?v=X07Y7pkHHJc&feature=youtu.be">Ссылка на источник</a>
 *
 * @author Alexander Pimenov (pimalex1978@ya.ru)
 * @version v1.0
 */

public class SimpleStack<T> implements Stack<T> {

    private ArrayList<T> list = new ArrayList<>();

    @Override
    public void push(T item) {
        list.add(0, item); //вставляем элемент только в начало списка
    }

    @Override
    public T pop() {
        return list.remove(0); //удаляем и возвращаем элемент, только из начала списка
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
