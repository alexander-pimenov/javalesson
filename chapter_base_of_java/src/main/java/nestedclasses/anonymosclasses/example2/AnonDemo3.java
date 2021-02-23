package nestedclasses.anonymosclasses.example2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Сортировка списка с использованием анонимных классов
 *  этой программе используется анонимный класс,
 *  реализующий интерфейс java.util.Comparator.
 *  Если такой тип сортировки производится только в одном
 *  месте приложения, то имеет смысл использовать анонимный класс,
 *  но если такая сортировка нужна во многих местах, то больший
 *  смысл имеет определить класс на более высоком уровне вложенности,
 *  или статический вложенный класс, и реализовать логику сортировки только один раз.
 */

public class AnonDemo3 {
    public static void main(String[] args) {

        // создать ArrayList и добавить в него
        // несколько объектов Integer

        List list = new ArrayList();
        list.add(new Integer(37));
        list.add(new Integer(-59));
        list.add(new Integer(83));

        //отсортировать список обычным способом (по возрастанию)

        Collections.sort(list);
        System.out.println(list);

        //отсортировать список по убыванию,
        //используя функцию, реализованную объектом
        //при помощи анонимного класса

        Collections.sort(list, new Comparator() {

            //переопределяем метод compare()
            @Override
            public int compare(Object o1, Object o2) {
                int a = ((Integer) o1).intValue();
                int b = ((Integer) o2).intValue();
                return a < b ? 1 : a == b ? 0 : -1;
            }
        });
        System.out.println(list);
    }
}
