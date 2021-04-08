package ru.pimalex1978.list.array_list.simple_array;

import java.util.Iterator;

/**
 * Класс, который имитирует ArrayList,
 *
 * @param <E>
 */
public class SimpleArray<E> implements Simple<E> {

    private E[] values;

    @SuppressWarnings("unchecked")
    public SimpleArray() {
        values = (E[]) new Object[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {

        /*
         * Т.к. нам нужна динамическая структура, то размер нашего контейнера
         * должен изменяться от количества элементов в нем.
         * При добавлении мы создаем новый массив, копируем в него данные из старого и
         * в конец, в оставшуюся ячейку нового массива, добавляем новый элемент (E e).
         */

        //помещаем код в try-catch, чтобы обработать даун-кастинг (E[]) new Object[temp.length + 1]
        //который может выкинуть исключение ClassCastException
        try {
            //делаем новый указатель на массив temp, на который указывал только values.
            //теперь temp хранит в себе то же, что и values
            E[] temp = values;
            //values ссылаем на новый массив, который на 1 больше старого.
            //это теперь пустой новый массив, который на 1 больше, прежнего.
            values = (E[]) new Object[temp.length + 1];
            //копируем массив в новый, с 0-го элемента и количество элементов по старому.
            System.arraycopy(temp, 0, values, 0, temp.length);
            //добавляем в поледнюю ячейку массива новый элемент (E e)
            values[values.length - 1] = e;
            return true;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        //если, что то пойдет не так, то вернем false
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(int index) {

        try {
            //делаем новый указатель на массив temp, на который указывал только values.
            //теперь temp хранит в себе то же, что и values
            E[] temp = values;
            //values ссылаем на новый массив, который на 1 меньше старого.
            //это теперь пустой новый массив, который на 1 меньше, прежнего.
            values = (E[]) new Object[temp.length - 1];
            //копируем массив в новый, с 0-го элемента до index.
            System.arraycopy(temp, 0, values, 0, index);
            //далее копируем массив в тотже новый values, пропуская элемент с index, который мы хотим удалить
            System.arraycopy(
                    temp, index + 1, //src
                    values, index,  //target
                    temp.length - index - 1); //amount (сколько копируем элементов)
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return values[index];
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public void update(int index, E e) {
        values[index] = e;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(values);
    }

    /*
    Проверку работы нашего SimpleArray проведем в main() методе, хотя по правильному было бы
    * сделать unit тесты
    */
    public static void main(String[] args) {
        Simple<String> strings = new SimpleArray<>();
        strings.add("first");
        strings.add("second");
        strings.add("third");
        strings.add("fourth");

        //работа итератора. Без итератора, мы бы не вызвали цикл foreach
        System.out.println("Проверяем работу итератора:");
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println("===========================");

        System.out.println("Проверяем Метод get(): " + strings.get(1));
        System.out.println("Проверяем Метод size(): " + strings.size());

        strings.update(0, "update");
        System.out.println("После метода update(): " + strings.get(0));

        System.out.println("Проверка метода delete():");
        strings.delete(3);
        System.out.println(strings.size());
        System.out.println(strings.get(0));
        System.out.println(strings.get(1));
        System.out.println(strings.get(2));


    }
}
