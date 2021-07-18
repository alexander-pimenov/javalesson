package ru.pimalex1978.class_date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

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
 * !!! В КАЧЕСТВЕ РАЗДЕЛИТЕЛЯ МОЖНО ИСПОЛЬЗОВАТЬ ЛЮБОЙ ТЕКСТ !!!
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
 * E - день недели (сокращение) - Вт
 * EEEE - день недели (полностью) - пятница
 */
public class FormatDate {
    public static void main(String[] args) {

        ////////////////////////////////////////////////////////////////////////
        Date dateDATE = new Date();
        //Pattern
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        System.out.println("Date без форматирования: " + dateDATE);
        //Вывод по формату
        System.out.println("Time in 12 Hour format - " + sdf.format(dateDATE));
        ////////////////////////////////////////////////////////////////////////
        System.out.println("============================================");

        ////////////////////////////////////////////////////////////////////////
        Date dateFromStr = new Date(2020, Calendar.APRIL, 10);
        System.out.println("Дата из строки: " + dateFromStr);

        ////////////////////////////////////////////////////////////////////////
        System.out.println("============================================");

        ////////////////////////////////////////////////////////////////////////
        LocalDateTime localDateTime = LocalDateTime.now();
        //Pattern
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("hh:mm:ss a");
        DateTimeFormatter pattern2 = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a");
        DateTimeFormatter pattern3 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        System.out.println("LocalDateTime без форматирования: " + localDateTime);
        System.out.println("Time in 12 Hour format - " + localDateTime.format(pattern));
        System.out.println("Time format (2) - " + localDateTime.format(pattern2));
        System.out.println("Time format (3) - " + localDateTime.format(pattern3));
        ////////////////////////////////////////////////////////////////////////

        System.out.println("============================================");

        ////////////////////////////////////////////////////////////////////////

        LocalDateTime a = LocalDateTime.of(2012, 6, 30, 12, 0, 0);
        System.out.println("LocalDateTime: " + a);

        ////////////////////////////////////////////////////////////////////////

        System.out.println("============================================");

        ////////////////////////////////////////////////////////////////////////
        /*Класс LocalTime дает только время БЕЗ даты !!!*/
        LocalTime time = LocalTime.now();
        // Pattern
        DateTimeFormatter patternDTF = DateTimeFormatter.ofPattern("hh:mm:ss a");
        System.out.println("LocalTime без форматирования: " + time);
        System.out.println("Time in 12 Hour format - " + time.format(patternDTF));
        ////////////////////////////////////////////////////////////////////////

        System.out.println("============================================");

        ////////////////////////////////////////////////////////////////////////
        /*Класс LocalDate дает только дату без времени !!!*/
        LocalDate date = LocalDate.now();
        // Pattern
        DateTimeFormatter patternDF = DateTimeFormatter.ofPattern("dd--MM--yyyy");
        System.out.println("LocalDate без форматирования: " + date);
        System.out.println("Date in format - " + date.format(patternDF));
        Instant instant = Instant.now();
        ////////////////////////////////////////////////////////////////////////

        System.out.println("============================================");

        ////////////////////////////////////////////////////////////////////////
        String x1 = "1086073200000"; //1609693356,1609653505,1609679356
        String x2 = "1609693356"; //1609693356,1609653505,1609679356
        long foo = Long.parseLong(x2);

        Date date2 = new Date(foo);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Вывод new Date(long): " + date2);
        System.out.println("Время преобразованное из миллисекунд: " + formatter.format(date2));
        ////////////////////////////////////////////////////////////////////////

        System.out.println("============================================");

        ////////////////////////////////////////////////////////////////////////
        ZonedDateTime parse = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
        System.out.println(parse);

        ////////////////////////////////////////////////////////////////////////

        System.out.println("============================================");

        ////////////////////////////////////////////////////////////////////////
        OffsetTime timeOf = OffsetTime.now();
        ZoneOffset offset = ZoneOffset.UTC;
        System.out.println(offset);
// changes offset, while keeping the same point on the timeline
        OffsetTime sameTimeDifferentOffset = timeOf.withOffsetSameInstant(offset);
        System.out.println(sameTimeDifferentOffset);
// changes the offset, and updates the point on the timeline
        OffsetTime changeTimeWithNewOffset = timeOf.withOffsetSameLocal(offset);
// Can also create new object with altered fields as before
        changeTimeWithNewOffset
                .withHour(3)
                .plusSeconds(2);
        System.out.println(changeTimeWithNewOffset);
        ////////////////////////////////////////////////////////////////////////

        System.out.println("============================================");

        ////////////////////////////////////////////////////////////////////////
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime);

    }
}
