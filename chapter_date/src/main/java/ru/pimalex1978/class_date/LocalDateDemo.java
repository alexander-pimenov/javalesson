package ru.pimalex1978.class_date;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/*
 * Java LocalDate to Instant and Timestamp
 * https://www.concretepage.com/java/java-8/java-localdate-to-instant-timestamp
 */
public class LocalDateDemo {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.parse("2019-05-16");

        System.out.println("---Instant---");
        Instant instant = localDate.atTime(LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(instant);

        instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        System.out.println(instant);

        instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(instant);

        instant = LocalDateTime.of(localDate, LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(instant);

        System.out.println("---Timestamp---");
        Timestamp timestamp = Timestamp.valueOf(localDate.atTime(LocalTime.MIDNIGHT));
        System.out.println(timestamp);

        timestamp = Timestamp.valueOf(LocalDateTime.of(localDate, LocalTime.MIDNIGHT));
        System.out.println(timestamp);

        System.out.println("---Timestamp to Instant---");
        instant = timestamp.toInstant();
        System.out.println(instant);



        System.out.println("\nСегодня LD: " + LocalDate.now());
        System.out.println("Сегодня LD+ZoneId: " + LocalDate.now(ZoneId.systemDefault()));
        System.out.println("Сегодня LDT: " + LocalDateTime.now());
        System.out.println("Сегодня LDT+ZoneId: " + LocalDateTime.now(ZoneId.systemDefault()));
        System.out.println("Сегодня Текущая отметка времени (Instant): " + Instant.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//        String date = "2017-05-31";
        //LocalDate localDate = LocalDate.parse("2017-05-31");
//        LocalDate localDate = LocalDate.parse(date, formatter);

        System.out.println(localDate.format(DateTimeFormatter.ofPattern("d::MMM::uuuu")));
        System.out.println(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));

        String localDateTim1 = convertToLocalDateTimeAsString(localDate);
        System.out.println(localDateTim1); //2017-05-31T00:00:00+03:00

        LocalDateTime localDateTime2 = convertToLocalDateTime_ISO_LOCAL(localDate);
        System.out.println(localDateTime2); //2017-05-31T00:00

        String localDateTime3 = convertToLocalDateTime_ISO_INSTANT(localDate);
        System.out.println(localDateTime3); //2017-05-30T21:00:00Z

        String toLocalDateTime4 = convertToLocalDateTime(localDate);
        System.out.println(toLocalDateTime4); //2017-05-31T00:00

        String localDateTime5 = convertToLocalDateTime_Via_Instant(localDate);
        System.out.println(localDateTime5); //2017-05-30T21:00:00Z

        String localDateTime6 = convertToLocalDateTimeViaAtZone(localDate);
        System.out.println(localDateTime6); //2017-05-30T21:00:00Z

        String localDateTime8 = convertToLocalDateTime_Via_Instant_atZone(localDate);
        System.out.println(localDateTime8); //2017-05-30T21:00:00Z

        String localDateTime9 = convertToLocalDateTime_Via_LDTof_atZone(localDate);
        System.out.println(localDateTime9); //2017-05-30T21:00:00Z

        String localDateTime10 = convertToLocalDateTime_Via_Timestamp(localDate);
        System.out.println(localDateTime10); //2017-05-31 00:00:00.0

        String localDateTime11 = convertToLocalDateTime_Via_Timestamp_To_Instant(localDate);
        System.out.println(localDateTime11); //2017-05-30T21:00:00Z

        LocalDateTime localDateTime7 = returnLDT(localDate);
        System.out.println(localDateTime7); //2017-05-31T00:00:25
    }

    public static String convertToLocalDateTimeAsString(LocalDate dateToConvert) {
        return ISO_OFFSET_DATE_TIME.format(dateToConvert.atStartOfDay(ZoneId.of(ZoneId.systemDefault()
                .getId())));
    }

    private static LocalDateTime convertToLocalDateTime_ISO_LOCAL(LocalDate dateToConvert) {
        return LocalDateTime.parse(ISO_LOCAL_DATE_TIME.format(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault()).toLocalDateTime()));
    }

    private static String convertToLocalDateTime_ISO_INSTANT(LocalDate dateToConvert) {
        return ISO_INSTANT.format(dateToConvert.atStartOfDay(ZoneId.systemDefault()));
    }

    private static String convertToLocalDateTime_Via_Instant(LocalDate dateToConvert) {
        Instant instant = dateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant();
        //Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return String.valueOf(instant);
    }

    private static String convertToLocalDateTime_Via_Instant_atZone(LocalDate dateToConvert) {
        Instant instant = dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return String.valueOf(instant);
    }

    private static String convertToLocalDateTimeViaAtZone(LocalDate dateToConvert) {
        Instant instant = dateToConvert.atTime(LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant();
        return String.valueOf(instant);
    }

    private static String convertToLocalDateTime_Via_LDTof_atZone(LocalDate dateToConvert) {
        Instant instant = LocalDateTime.of(dateToConvert, LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant();
        return String.valueOf(instant);
    }

    private static String convertToLocalDateTime_Via_Timestamp(LocalDate dateToConvert) {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(dateToConvert, LocalTime.MIDNIGHT));
        return String.valueOf(timestamp);
    }

    private static String convertToLocalDateTime_Via_Timestamp_To_Instant(LocalDate dateToConvert) {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(dateToConvert, LocalTime.MIDNIGHT));
        Instant instant = timestamp.toInstant();
        return String.valueOf(instant);
    }

    private static String convertToLocalDateTime(LocalDate dateToConvert) {
        Timestamp ts = Timestamp.valueOf(dateToConvert.atStartOfDay());
        LocalDateTime ldt = ts.toLocalDateTime();
        return String.valueOf(ldt);
    }

    private static LocalDateTime returnLDT(LocalDate dateToConvert) {
        int year = dateToConvert.getYear();
        Month month = dateToConvert.getMonth();
        int dayOfMonth = dateToConvert.getDayOfMonth();

        LocalDateTime result = LocalDateTime.of(year, month, dayOfMonth, 0, 0, 25);
        return result;
    }
}