package ru.pimalex1978.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Рассмотрим простой пример CopyOnWriteArrayListExample, в котором используем класс
 * CopyOnWriteArrayList. В примере формируется набор данных lst, на основании которого
 * создается потокобезопасная коллекция list типа CopyOnWriteArrayList. Данные коллекции
 * list с помощью итератора выводятся в консоль два раза. В первом цикле в коллекцию
 * вносятся изменения, во втором цикле данные выводятся без изменений. Результаты работы
 * примера ниже после листинга примера.
 * <p>
 * Выполнение примера
 * В первом цикле, несмотря на внесение изменений в набор, в консоли представлены
 * исходные данные.
 * Во втором цикле — измененный набор данных. Пример демонстрирует, что итератор
 * набора данных CopyOnWriteArrayList не вызвал исключения ConcurrentModificationException
 * при одновременном внесении изменений и переборе значений — это значит, что алгоритм
 * CopyOnWrite действует.
 */

public class CopyOnWriteArrayListExample {
    private List<String> list;

    public CopyOnWriteArrayListExample() {
        List<String> lst = new ArrayList<String>();
        lst.add("Java");
        lst.add("J2EE");
        lst.add("J2SE");
        lst.add("Collections");
        lst.add("Concurrent");

        list = new CopyOnWriteArrayList<>(lst);

        System.out.println("Цикл с изменением");
        printCollection(true);
        System.out.println("\nЦикл без изменения");
        printCollection(false);
    }

    private void printCollection(boolean change) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.printf(" %s %n", element);
            if (change) {
                if (element.equals("Collections")) {
                    list.add("Новая строка");
                    list.remove(element);
                }
            }
        }
    }

    public static void main(String[] args) {
        new CopyOnWriteArrayListExample();
        System.exit(0);
    }
}
