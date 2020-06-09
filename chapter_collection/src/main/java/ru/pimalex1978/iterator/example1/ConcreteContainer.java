package ru.pimalex1978.iterator.example1;

/**
 * interface Iterator для последующей реализации методов обхода коллекции.
 */
interface Iterator {
    boolean hasNext(); //"можно ли взять элемент"

    Object next(); //возвращает элемент
}

/**
 * interface Container интерфейс нужен, чтобы генерировать Интерфейс Итератора.
 */

interface Container {
    Iterator getIterator(); //способ получения (возвращения) итератора
}

/**
 * class ConcreteContainer, здесь размещатся конкретная коллекция,
 * которую нужно обойти с помощью Итератора.
 * В ней есть метод, который создает Конкретный итератор (под эту коллекцию),
 * т.е. возвращает экземпляр конкретного итератора, поэтому implements Aggregate.
 * Конкретный Итератор под эту коллекцию будет реализован как
 * внутренний класс, т.к. он "интересен" только этой коллекции.
 */

public class ConcreteContainer implements Container {

    //массив задач
    String[] tasks = {"Посторить дом", "Родить сына", "Посадить дерево"};

    //метод, возвращающий Конкретный итератор TaskIterator
    @Override
    public Iterator getIterator() {
        return new TaskIterator();
    }

    //Конкретный итератор для этой коллекции опишем внутренним классом
    //Он реализует методы общего интерфейса Iterator
    private class TaskIterator implements Iterator {

        //поле индекс. Оно нужно, чтоб при создании Итератора
        //индекс смотрел на первый элемент массива.
        int index = 0;

        //Проверка на то, что можно читать следующий элемент.
        @Override
        public boolean hasNext() {
            return index < tasks.length;
        }

        //Этот метод должен нам вернуть элемент массива.
        //Здесь мы возвращаем элемент по индексу.
        @Override
        public Object next() {
            //Послк возвращения элемента tasks[index] нужно обязательно
            //инкрементировать индекс, чтбы он сдвинулся на слудующий элемент.
            return tasks[index++];
        }
    }
}
