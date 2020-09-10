package ru.pimalex1978.class_date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Для того, чтобы отображать дату и время в удобном формате
 * можно использовать класс SimpleDataFormat.
 * При создании шаблона представления даты SimpleDateFormat
 * использовались следующие параметры :
 * dd — означает день;
 * MM — месяц (номер месяца);
 * MMM — месяц (сокращенное название месяца в соответствии с локализацией);
 * MMMM — месяц (полное название месяца в соответствии с локализацией);
 * yyyy — год;
 * hh — часы;
 * HH - 2 цифры для часов в 24-часовом формате;
 * mm — минуты;
 * В КАЧЕСТВЕ РАЗДЕЛИТЕЛЯ МОЖНО ИСПОЛЬЗОВАТЬ ЛЮБОЙ ТЕКСТ !!!
 * Символы форматирования строки
 * A - AM или PM
 * d - день месяца (1-31)
 * D - день в году (1-366)
 * H - часы в формате AM/PM (1-12)
 * K - часы в формате суток (1-24)
 * M - минуты (0-59)
 * S - секунды (0-59)
 * W - неделя в году (1-53)
 * y - год
 * z - часовой пояс
 * GG - эра
 */

public class TestSimpleDateFormat {


    public static void main(String[] args) {
        //Текущая дата + время, как оно есть:
        Date date = new Date();
        System.out.println(date);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Задаем 1-й шаблон представления даты
        SimpleDateFormat sdf1;
        sdf1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        System.out.println(sdf1.format(date));  // 04.02.2020 02:47
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Задаем 2-й шаблон представления даты
        SimpleDateFormat sdf2;
        sdf2 = new SimpleDateFormat("День dd Месяц MM Год yyyy Время hh:mm");
        System.out.println(sdf2.format(date)); // День 04 Месяц 02 Год 2020 Время 02:47
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Задаем 3-й шаблон представления даты
        SimpleDateFormat sdf3;
        sdf3 = new SimpleDateFormat("День dd Месяц MM Год yyyy");
        System.out.println(sdf3.format(date)); // День 04 Месяц 02 Год 2020
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Задаем 4-й шаблон представления даты
        SimpleDateFormat sdf4;
        sdf4 = new SimpleDateFormat("Время hh:mm");
        System.out.println(sdf4.format(date)); // Время 02:47
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Задаем 5-й шаблон представления даты
        SimpleDateFormat sdf5;
        sdf5 = new SimpleDateFormat("День dd Месяц MM Год yyyy Неделя W Часовой пояс z");
        System.out.println(sdf5.format(date)); // День 04 Месяц 02 Год 2020 Неделя 1 Часовой пояс MSK
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Задаем 6-й шаблон представления даты
        SimpleDateFormat sdf6;
        sdf6 = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(sdf6.format(date)); //09/09/2020
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //Задаем 7-й шаблон представления даты для преобразования строки в дату
        SimpleDateFormat sdf7;
        sdf7 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String dateStr = "25-06-1985 15:17:45";

        final Date parseDate;
        try {
            parseDate = sdf7.parse(dateStr);
            System.out.println(parseDate); //Tue Jun 25 15:17:45 MSD 1985 - тип Date
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //Задаем 8-й шаблон представления даты для преобразования строки в дату
        String strDate = "Sat, April 4, 2020";
        //Создаем новый объект SimpleDateFormat с шаблоном, который совпадает
        //с тем, что у нас в строке (иначе распарсить не получится).
        //Если же мы Locale опустим, то он будет использовать значение Locale по
        //умолчанию, которое не всегда является английским.
        SimpleDateFormat sdf8 = new SimpleDateFormat("EEE, MMMM d, yyyy", Locale.ENGLISH);
        try {
            Date date8 = sdf8.parse(strDate);
            System.out.println(date8);
            System.out.println(sdf8.format(date8));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        /*Преобразование Calendar в Date:*/
        final Calendar calendar = Calendar.getInstance(); //текущая дата
        /*Объект Calendar со всеми его полями*/
        System.out.println(calendar);
        /*Или просто дата*/
        System.out.println(calendar.getTime());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Получить дату и время по шаблону, используя Calendar
        //Если Locale.ENGLISH удалить из параметра то будет использоваться местная Locale, т.е. русские названия
        SimpleDateFormat sdf9 = new SimpleDateFormat("yyyy MMM dd HH:mm", Locale.ENGLISH);
        Calendar calendar1 = new GregorianCalendar(1998, Calendar.SEPTEMBER, 25);
        System.out.println(sdf9.format(calendar1.getTime())); //1998 Sep 25 00:00
        System.out.println(calendar1.getTime()); //Fri Sep 25 00:00:00 MSD 1998
        System.out.println(date); //текущее время+дата Wed Sep 09 17:04:55 MSK 2020
        //Текущая дата+время через Calendar
        final Calendar instance = Calendar.getInstance();
        System.out.println(instance.getTime()); //текущее время+дата Wed Sep 09 17:04:55 MSK 2020
        System.out.println(sdf9.format(instance.getTime())); //2020 Sep 09 19:55
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Пример простого календаря
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        Calendar calendar3 = new GregorianCalendar(2013, 1, 28, 13, 24, 56);

        int year = calendar3.get(Calendar.YEAR);
        int month = calendar3.get(Calendar.MONTH);//Jan = 0, dec = 11
        int dayOfMonth = calendar3.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar3.get(Calendar.DAY_OF_WEEK);
        int weekOfYear = calendar3.get(Calendar.WEEK_OF_YEAR);
        int weekOfMonth = calendar3.get(Calendar.WEEK_OF_MONTH);

        int hour = calendar3.get(Calendar.HOUR);       //12 hour clock
        int hourOfDay = calendar3.get(Calendar.HOUR_OF_DAY);//24 hour clock
        int minute = calendar3.get(Calendar.MINUTE);
        int second = calendar3.get(Calendar.SECOND);
        int millisecond = calendar3.get(Calendar.MILLISECOND);

        System.out.println(sdf.format(calendar3.getTime()));

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

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

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
