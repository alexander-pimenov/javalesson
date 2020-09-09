package ru.pimalex1978.class_date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * В версии Java 1.1 появился новый класс — Calendar. Он сделал  работу с датам
 * в Java несколько проще, чем она выглядела раньше.
 * Его основное удобство заключается в том, что он умеет работать с датами в
 * более удобном формате.
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
 * <p>
 * Отсчёт месяцев идёт от нуля, поэтому декабрь будет одиннадцатым месяцем.
 * Чтобы не путаться с такими случаями, проще использовать понятные константы.
 * <p>
 * Главное при работе с классом Calendar — понимать, что это именно календарь,
 * а не отдельная дата.
 * Дата — это просто несколько чисел, обозначающих конкретный промежуток времени.
 * А календарь - это целое устройство, с помощью которого можно много чего
 * с датами делать.
 * <p>
 * В календаре есть Константы — это статические поля класса Calendar с уже
 * установленным значением, которое нельзя изменить.
 */

public class TestGregorianCalendar {
    public static void main(String[] args) throws ParseException {

        // календарь на текущую дату
        Calendar c = new GregorianCalendar();
        System.out.println("Календарь, как объект с кучей полей: " + c);
        System.out.println("Календарь, получаем дату из календаря с помощью getTime(): " + c.getTime());
        System.out.println("Календарь с getWeekYear(): " + c.getWeekYear());
        //Вывод: (текущая дата и время)
        //Tue Feb 04 15:20:12 MSK 2020

        //Создадим календарь с датой 21.12.2014
        Calendar c2 = new GregorianCalendar(2014, Calendar.DECEMBER, 21); //(2014, 12, 21)

        // Можно сдвинуть дату на определённый период с помощью метода add().
        // увеличиваем дату на 1 день
        c2.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println(c2.getTime());   // Mon Dec 22 00:00:00 MSK 2014

        // уменьшаем дату на 3 день
        c2.add(Calendar.DAY_OF_YEAR, -3);
        System.out.println(c2.getTime()); //Fri Dec 19 00:00:00 MSK 2014

        // Можно сдвинуть дату на определённый период с помощью метода add().
        // Отодвинем дату на два месяца.
        c2.add(Calendar.MONTH, 2);
        c2.add(Calendar.MONTH, 4);
        System.out.println(c2.get(Calendar.MONTH)); // 1
        System.out.println(c2.getTime()); // Thu Feb 19 00:00:00 MSK 2015
        c2.add(Calendar.MONTH, 6);
        System.out.println(c2.getTime());


        // календарь на 25.01.2015
        Calendar c1 = new GregorianCalendar(2015, Calendar.JANUARY, 25);

        System.out.println("Год: " + c1.get(Calendar.YEAR));  // 2015
        System.out.println("Месяц: " + c1.get(Calendar.MONTH)); // 0

        System.out.println("Порядковый номер недели в месяце: " + c1.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Число: " + c1.get(Calendar.DAY_OF_MONTH)); // 25
        System.out.println(c1.get(Calendar.DAY_OF_WEEK_IN_MONTH)); // 4
        System.out.println(c1.get(Calendar.DAY_OF_WEEK)); // 1
        System.out.println(c1.get(Calendar.DAY_OF_YEAR)); // 25
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        /*Эти циклы показывают работу метода add()*/
        System.out.println("==============================");
        Calendar c3 = new GregorianCalendar(2015, Calendar.JANUARY, 15);
        for (int i = 0; i < 13; i++) {
            c3.add(Calendar.MONTH, 1);
            System.out.println(c3.getTime());
        }
        /*Эти циклы показывают работу метода add()*/
        System.out.println("==============================");
        Calendar c4 = new GregorianCalendar(2015, Calendar.JANUARY, 15);
        for (int i = 0; i < 13; i++) {
            c3.add(Calendar.DAY_OF_MONTH, 1);
            System.out.println(c3.getTime());
        }
        System.out.println("==============================");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        /*Специальный метод roll() может прибавлять и убавлять значения, не затрагивая
         * при этом  остальные значения.*/
        Calendar c5 = new GregorianCalendar(2017, Calendar.JANUARY, 25);
        c5.set(Calendar.HOUR, 8);
        c5.set(Calendar.MINUTE, 44);
        c5.set(Calendar.SECOND, 15);
        System.out.println(c5.getTime());
        c5.roll(Calendar.MONTH, -2);
        System.out.println(c5.getTime());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        Calendar c6 = new GregorianCalendar();
        final long timeInMillis = c6.getTimeInMillis();
        System.out.println(timeInMillis);

        System.out.println(c6.getTime()); //Wed Sep 09 16:20:56 MSK 2020 - текущая
        System.out.println(c6.getCalendarType()); //gregory

        /*Преобразование Calendar в Date:
         * Объект Calendar со всеми его полями*/
        final Calendar calendar2 = Calendar.getInstance();
        System.out.println(calendar2);
        /*Или просто дата*/
        System.out.println(calendar2.getTime());

        System.out.println("==============================");
        /*Есть разные конструкторы, поэтому удобно устанавливать нужные нам
         * параметры даты.*/
        GregorianCalendar c7 = new GregorianCalendar(2020, Calendar.AUGUST, 16, 22, 30);
        System.out.println(c7.getTime());
        System.out.println("==============================");

//      Пример простого календаря
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar(2013, 1, 28, 13, 24, 56);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);//Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR);       //12 hour clock
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);//24 hour clock
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);

        System.out.println(sdf.format(calendar.getTime()));

        System.out.println("year \t\t: " + year);
        System.out.println("month \t\t: " + month);
        System.out.println("dayOfMonth \t: " + dayOfMonth);
        System.out.println("dayOfWeek \t: " + dayOfWeek);
        System.out.println("weekOfYear \t: " + weekOfYear);
        System.out.println("weekOfMonth \t: " + weekOfMonth);

        System.out.println("hour \t\t: " + hour);
        System.out.println("hourOfDay \t: " + hourOfDay);
        System.out.println("minute \t\t: " + minute);
        System.out.println("second \t\t: " + second);
        System.out.println("millisecond \t: " + millisecond);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        /*Все поля календаря (число, месяц, минуты, секунды и т.д.) можно устанавливать
         * по отдельности с помощью метода set(). */
        /*Обновление даты Календаря:*/
        System.out.println("#1. " + sdf.format(calendar.getTime()));
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_MONTH, 25);
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 42);
        System.out.println("#2. " + sdf.format(calendar.getTime()));
        System.out.println("Без sdf: " + calendar.getTime());

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //Конвертировать дату в календарь.
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "22-01-2001 10:15:33";
        Date date = sdf2.parse(dateInString); //Mon Jan 22 10:15:33 MSK 2001
        System.out.println(date);
        Calendar calendar1 = Calendar.getInstance();
        System.out.println(calendar1.getTime()); //Wed Sep 09 18:18:57 MSK 2020
        calendar1.setTime(date);
        System.out.println(calendar1.getTime()); //Mon Jan 22 10:15:33 MSK 2001

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Пример работы с эрами
        //Битва при Каннах, в которой Ганнибал победил войско Рима.
        //Это произошло 2 августа 216 г. до н. э.
        Calendar cannes = new GregorianCalendar(216, Calendar.AUGUST, 2);
        cannes.set(Calendar.ERA, GregorianCalendar.BC);
        //Выведем дату в удобном формате:
        DateFormat df = new SimpleDateFormat("dd MMM yyy GG");
        System.out.println(df.format(cannes.getTime()));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }
}
