package ru.pimalex1978.class_loader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Как и раньше мы можем прямо в процессе работы теста модифицировать и
 * перекомпилировать класс DynamicModule, и это изменение будет учтено.
 * Но для «общения» со стационарной частью программы и для хранения счетчика
 * обращений к методу toString() теперь используется «true-static»-класс
 * TrueStaticModule. Поэтому мы не получаем исключения ClassCastException,
 * а счетчик counter корректно увеличивается на протяжении всей работы теста.
 */

public class Test2 {
    public static void main(String[] argv) throws Exception {
        ClassLoader loader;
        for (; ; ) {
            loader = new DynamicClassOverloader(
                    new String[]{"."});
            // текущий каталог "." будет единственным
            // каталогом поиска
            Class clazz = Class.forName("ru.job4j.tutorial.class_loader.DynamicModule",
                    true, loader);
            TrueStaticModule tsm;
            tsm = (TrueStaticModule) clazz.newInstance();

            System.out.println(tsm.getCounter());
            System.out.println(tsm);

            new BufferedReader(
                    new InputStreamReader(System.in)).readLine();
        }
    }
}
