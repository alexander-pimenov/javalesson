package ru.pimalex1978.class_date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
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
 * <p>
 * Символы форматирования строки:
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
        System.out.println("Текущая дата + время, как оно есть: " + date);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Задаем 1-й шаблон представления даты
        SimpleDateFormat sdf1;
        sdf1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        System.out.println("Формат dd.MM.yyyy hh:mm -> " + sdf1.format(date));  // 04.02.2020 02:47
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
        //Используем метод parse()
        //Создаем новый объект SimpleDateFormat с шаблоном, который совпадает
        //с тем, что у нас в строке (иначе распарсить не получится).
        String dateStr = "25-06-1985 15:17:45";

        SimpleDateFormat sdf7;
        sdf7 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        final Date parseDate;
        try {
            parseDate = sdf7.parse(dateStr);
            System.out.println(parseDate); //Tue Jun 25 15:17:45 MSD 1985 - тип Date
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Задаем 8-й шаблон представления даты для преобразования строки в дату
        //Используем метод parse()
        String strDate = "Sat, April 4, 2020";
        //Создаем новый объект SimpleDateFormat с шаблоном, который совпадает
        //с тем, что у нас в строке (иначе распарсить не получится).
        //Если же мы Locale опустим, то он будет использовать значение Locale по
        //умолчанию, которое не всегда является английским.
        SimpleDateFormat sdf8 = new SimpleDateFormat("EEE, MMMM d, yyyy", Locale.ENGLISH);
        try {
            Date date8 = sdf8.parse(strDate);
            System.out.println("1) полный вариант даты - " + date8);
            System.out.println("2) вариант даты по строковому формату - " + sdf8.format(date8));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //только такое расположение yyy-MM-dd по дефолту сможет быть распарсино
        String stringDate = "2016-08-20";

        //default, ISO__LOCAL__DATE -
        LocalDate localDate1 = LocalDate.parse(stringDate);

        System.out.println("LocalDate (ISO): " + localDate1);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        //Если раположение yyy MM dd отличается от дефолтного yyy-MM-dd,
        //то необходимо задать formatter с символами форматирования строки
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d-MMM-yyyy", Locale.ENGLISH);

        String stringDate2 = "16-Aug-2016";

        LocalDate localDate2 = LocalDate.parse(stringDate2, formatter2);

        System.out.println("ISO__LOCAL__DATE: " + localDate2); //default, print ISO__LOCAL__DATE

        System.out.println("Форматированный в строку LocalDate: " + formatter2.format(localDate2));

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("d/MM/yyyy");

        String stringDate3 = "16/08/2016";

        LocalDate localDate3 = LocalDate.parse(stringDate3, formatter3);

        System.out.println("LocalDate : " + localDate3);

        System.out.println("Форматированный в строку LocalDate: " + formatter3.format(localDate3));

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Для правильного распарсивания, чтобы не было ошибок, нужно указывать Local.
        //Лучше ставить Locale.ENGLISH
        //Если его не поставить, то будет взята местная локаль, которая может не соответствовать паттерну.

        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("E, MMM d yyyy", Locale.ENGLISH);

        String stringDate4 = "Tue, Aug 16 2016";

        LocalDate localDate4 = LocalDate.parse(stringDate4, formatter4);

        System.out.println("LocalDate: " + localDate4);

//      String format = formatter2.format(localDate4);
        System.out.println("Форматированный в строку LocalDate: " + formatter2.format(localDate4));

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Преобразование из стоки в LocalDateTime и обратно:
        DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm:ss a", Locale.ENGLISH);

        String stringDate5 = "Tuesday, Aug 16, 2016 12:10:56 PM";

        LocalDateTime localDateTime5 = LocalDateTime.parse(stringDate5, formatter5);

        System.out.println("LocalDateTime: " + localDateTime5);
        System.out.println("LocalDate из LocalDateTime: " + localDateTime5.toLocalDate());
        System.out.println("LocalTime из LocalDateTime: " + localDateTime5.toLocalTime());

        System.out.println("Форматированный в строку LocalDateTime: " + formatter5.format(localDateTime5));

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // Суффикс «Z» означает UTC, вы можете напрямую преобразовать его в java.time.instant,
        // а затем отобразить его с часовым поясом.
        // Запусти программу посмотри на вывод. Интересно, время в разнх зонах разное.
        //Не изменяемые зоны из класса ZoneId:
        //This maps as follows:
        //     * <ul>
        //     * <li>EST - -05:00</li>
        //     * <li>HST - -10:00</li>
        //     * <li>MST - -07:00</li>
        //     * <li>ACT - Australia/Darwin</li>
        //     * <li>AET - Australia/Sydney</li>
        //     * <li>AGT - America/Argentina/Buenos_Aires</li>
        //     * <li>ART - Africa/Cairo</li>
        //     * <li>AST - America/Anchorage</li>
        //     * <li>BET - America/Sao_Paulo</li>
        //     * <li>BST - Asia/Dhaka</li>
        //     * <li>CAT - Africa/Harare</li>
        //     * <li>CNT - America/St_Johns</li>
        //     * <li>CST - America/Chicago</li>
        //     * <li>CTT - Asia/Shanghai</li>
        //     * <li>EAT - Africa/Addis_Ababa</li>
        //     * <li>ECT - Europe/Paris</li>
        //     * <li>IET - America/Indiana/Indianapolis</li>
        //     * <li>IST - Asia/Kolkata</li>
        //     * <li>JST - Asia/Tokyo</li>
        //     * <li>MIT - Pacific/Apia</li>
        //     * <li>NET - Asia/Yerevan</li>
        //     * <li>NST - Pacific/Auckland</li>
        //     * <li>PLT - Asia/Karachi</li>
        //     * <li>PNT - America/Phoenix</li>
        //     * <li>PRT - America/Puerto_Rico</li>
        //     * <li>PST - America/Los_Angeles</li>
        //     * <li>SST - Pacific/Guadalcanal</li>
        //     * <li>VST - Asia/Ho_Chi_Minh</li>
        //     * </ul>
        //     * The map is unmodifiable.
        String dateInString = "2016-08-16T15:23:01Z";

        Instant instant = Instant.parse(dateInString);

        System.out.println("Instant : " + instant);

        //get date time only
        LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));

        //get localdate
        System.out.println("LocalDate : " + result.toLocalDate());

        //get date time + timezone
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Tokyo"));
        System.out.println("ZonedDateTime (Asia/Tokyo): " + zonedDateTime);

        //get date time + timezone
        ZonedDateTime zonedDateTime2 = instant.atZone(ZoneId.of("Europe/Athens"));
        System.out.println("ZonedDateTime (Europe/Athens): " + zonedDateTime2);

        //get date time + timezone
        ZonedDateTime zonedDateTime3 = instant.atZone(ZoneId.of("Pacific/Auckland"));
        System.out.println("ZonedDateTime (Pacific/Auckland): " + zonedDateTime3);

        //get date time + timezone
        ZonedDateTime zonedDateTime4 = instant.atZone(ZoneId.systemDefault());
        System.out.println("ZonedDateTime (System Default): " + zonedDateTime4);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        String stringDate6 = "2016-08-16T10:15:30+08:00";
        ZonedDateTime result6 = ZonedDateTime.parse(stringDate6, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("ZonedDateTime : " + result6);
        System.out.println("TimeZone из ZonedDateTime: " + result6.getZone());
        System.out.println("LocalDate из ZonedDateTime: " + result6.toLocalDate());

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //java.time.Instant - Мгновенная точка на временной шкале.
        //  * Этот класс моделирует единственную мгновенную точку на временной шкале.
        //  * Это может быть использовано для записи отметок времени событий в приложении.
        Instant instant1 = Instant.now();
        ZonedDateTime zonedDateTime1 = instant1.atZone(ZoneId.systemDefault());
        System.out.println("ZonedDateTime (now): " + zonedDateTime1);
        System.out.println("LocalTime from ZonedDateTime (now): " + zonedDateTime1.toLocalTime());
        System.out.println("LocalDate from ZonedDateTime (now): " + zonedDateTime1.toLocalDate());
        System.out.println("LocalDateTime from ZonedDateTime (now): " + zonedDateTime1.toLocalDateTime());

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
