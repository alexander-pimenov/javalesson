package ru.pimalex1978.class_date;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static net.sf.saxon.value.GDateValue.isLeapYear;

/**
 * Абстрактный класс Calendar позволяет работать с датой в рамках календаря,
 * т.е он умеет прибавлять день, при этом учитывать високосные год и прочее,
 * а также позволяет преобразовать время в миллисекундах в более удобном виде
 * - год, месяц, день, часы, минуты, секунды.
 * Единственной реализацией Calendar является класс GregorianCalendar.
 * Календарь достаточно мощный класс, который позволяет получать названия месяцев
 * и дней недели, увеличивать или уменьшать различные параметры текущей даты,
 * а также получать их. Для удобства работы с ним нужно просто разобраться с
 * типами данных, с которыми он работает:
 * <p>
 * DAY_OF_YEAR — день года (0- 365);
 * DAY_OF_MONTH — день месяца (какой по счету день в месяце 0 — 31);
 * WEEK_OF_MONTH — неделя месяца;
 * WEEK_OF_YEAR — неделя в году;
 * MONTH — номер месяца;
 * Year — номер года;
 * Calendar.ERA — эра.
 *
 * Отсчёт месяцев идёт от нуля, поэтому декабрь будет одиннадцатым месяцем.
 * Чтобы не путаться с такими случаями, проще использовать понятные константы.
 */

public class TestGregorianCalendar {
    public static void main(String[] args) {

        // календарь на текущую дату
        Calendar c = new GregorianCalendar();
        System.out.println(c.getTime());
        //Вывод:
        //Tue Feb 04 15:20:12 MSK 2020

        // календарь на 21.12.2014
        Calendar c2 = new GregorianCalendar(2014, Calendar.DECEMBER, 21); //(2014, 12, 21)

        // увеличиваем дату на 1 день
        c2.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println(c2.getTime());   // 22.12.2014
        //Вывод:
        //Mon Dec 22 00:00:00 MSK 2014

        // уменьшаем дату на 3 день
        c2.add(Calendar.DAY_OF_YEAR, -3);
        System.out.println(c2.getTime());
        //Вывод:
        //Fri Dec 19 00:00:00 MSK 2014

        // Можно сдвинуть дату на определённый период с помощью метода add ().
        // Отодвинем дату на два месяца.
        c2.add(Calendar.MONTH, 2);
        System.out.println(c2.get(Calendar.MONTH)); // 1
        System.out.println(c2.getTime());
        //Вывод:
        // Thu Feb 19 00:00:00 MSK 2015

        // календарь на 25.01.2015
        Calendar c1 = new GregorianCalendar(2015, Calendar.JANUARY, 25);

        System.out.println(c1.get(Calendar.MONTH)); // 0
        System.out.println(c1.get(Calendar.YEAR));  // 2015

        System.out.println(
                c1.get(Calendar.DAY_OF_WEEK_IN_MONTH)); // 4
        System.out.println(
                c1.get(Calendar.DAY_OF_WEEK)); // 1
        System.out.println(
                c1.get(Calendar.DAY_OF_YEAR)); // 25
        System.out.println(
                c1.get(Calendar.DAY_OF_MONTH)); // 25

    }
}
