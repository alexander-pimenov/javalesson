package ru.pimalex1978.class_date;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
 * Java LocalDate to Instant and Timestamp
 * https://www.concretepage.com/java/java-8/java-localdate-to-instant-timestamp
 * https://qastack.ru/programming/22463062/how-to-parse-format-dates-with-localdatetime-java-8
 */
public class LocalDateDemo {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.parse("2019-05-16");
        System.out.println("Распарсили строковое значение даты 2019-05-16, преобразовав его в LocalDate");

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


        System.out.println("\nСегодня LocalDate.now(): " + LocalDate.now());
        System.out.println("Сегодня LocalDate.now()+ZoneId: " + LocalDate.now(ZoneId.systemDefault()));
        System.out.println("Сегодня LocalDateTime.now(): " + LocalDateTime.now());
        System.out.println("Сегодня LocalDateTime.now()+ZoneId: " + LocalDateTime.now(ZoneId.systemDefault()));
        System.out.println("Сегодня Текущая отметка времени (Instant.now()): " + Instant.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//        String date = "2017-05-31";
        //LocalDate localDate = LocalDate.parse("2017-05-31");
//        LocalDate localDate = LocalDate.parse(date, formatter);

        System.out.println("LocalDate форматируем по формату d::MMM::uuuu: " + localDate.format(DateTimeFormatter.ofPattern("d::MMM::uuuu")));
        System.out.println("LocalDate форматируем по формату BASIC_ISO_DATE: " + localDate.format(DateTimeFormatter.BASIC_ISO_DATE));

        String localDateTim1 = convertToLocalDateTimeAsString(localDate);
        System.out.println("DateTimeFormatter форматирует по формату ISO_OFFSET_DATE_TIME: " + localDateTim1); //2017-05-31T00:00:00+03:00

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

        //"2019-07-18T15:41:58.263267+03:00"
        String dateToConvert = "2019-07-18T15:41:58.263267+03:00";
//        LocalDateTime localDateTime = convertLocalDateTimeAsStringToLocalDateTime(dateToConvert);
//        System.out.println("Значение полученно из строкового значения: " + localDateTime);


        DateTimeFormatter ISO_INSTANT_WITH_THREE_MILLIS = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 3, true)
                .appendPattern("'Z'")
                .toFormatter();
        DateTimeFormatter ISO_INSTANT_WITH_SIX_MILLIS = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 6, true)
                .appendPattern("'Z'")
                .toFormatter();
        DateTimeFormatter FORMATTER_WITH_OFFSET_X = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 3, true)
                .appendPattern("X")
                .toFormatter();
        DateTimeFormatter FORMATTER_WITH_T_LITERAL_AND_OFFSET_X = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 3, true)
                .appendPattern("X")
                .toFormatter();
        DateTimeFormatter FORMATTER_WITH_T_LITERAL_AND_OFFSET_X_6MILLIS = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 6, true)
                .appendPattern("X")
                .toFormatter();


        String format = ISO_INSTANT_WITH_THREE_MILLIS.format(OffsetDateTime.now(Clock.systemUTC()).minusMinutes(1));
        System.out.println("1 " + format);
        String format1 = ISO_INSTANT_WITH_SIX_MILLIS.format(OffsetDateTime.now(Clock.systemUTC()).minusMinutes(1));
        System.out.println("2 " + format1);
        String format2 = FORMATTER_WITH_T_LITERAL_AND_OFFSET_X_6MILLIS.format(OffsetDateTime.now(Clock.systemUTC()).minusMinutes(1));
        System.out.println("3 " + format2);

        //"2019-07-18T15:41:58.263267+03:00"
        String dateAsString = "2019-07-18T15:41:58.263267+03:00";
        LocalDateTime localDateTime = LocalDateTime.parse(dateAsString, ISO_OFFSET_DATE_TIME);
        System.out.println("4 " + localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.parse(dateAsString, ISO_ZONED_DATE_TIME);
        System.out.println("5 " + localDateTime1);

        //Преобразуйте OffsetDateTime в Instant с помощью OffsetDateTime#toInstant.
        // Instant представляет собой мгновенную точку на временной шкале.
        // Он не зависит от часового пояса и, следовательно, всегда в формате UTC.
        OffsetDateTime odt = OffsetDateTime.parse("2020-12-20T00:00:00.000Z");
        System.out.println("6 " + odt);
        Instant instant1 = odt.toInstant();
        System.out.println("7 " + instant1);

        //Разбор (парсинг) даты и времени
        String str = "1986-04-08 12:30";
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, timeFormatter);
        System.out.println("8 " + dateTime);

        //Форматирование даты и времени
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.of(1986, Month.APRIL, 8, 12, 30);
        String formattedDateTime = dateTime1.format(formatter1); // "1986-04-08 12:30"
        System.out.println("9 " + formattedDateTime);

        // Обратите внимание, что есть некоторые часто используемые форматы даты/времени, предопределенные
        // как константы в DateTimeFormatter.
        // Например: использование DateTimeFormatter.ISO_DATE_TIME для форматирования LocalDateTime
        // экземпляра сверху приведет к строке "1986-04-08T12:30:00".
        // В parse() и format() методы доступны для всех дата/время, связанные с объектами (например, LocalDate
        // или ZonedDateTime)

        String anotherDate = "04 Aug 2015";
        //DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MMM uuuu"); - можно этот вариант
        DateTimeFormatter dTF = new DateTimeFormatterBuilder() // можно вариант с билдером
                // case insensitive to parse JAN and FEB
                .parseCaseInsensitive()
                // add pattern
                .appendPattern("dd MMM yyyy")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);

        LocalDate lds = LocalDate.parse(anotherDate, dTF);
        System.out.println("10 " + anotherDate + " parses to " + lds);

        String strDate = "2015-08-04";
        LocalDate aLD = LocalDate.parse(strDate);
        DateTimeFormatter dTF1 = DateTimeFormatter.ofPattern("dd MMM uuuu");
        System.out.println("11 " + aLD + " formats as " + dTF1.format(aLD));

//        Преобразовать LocalDateTime в строку часового пояса ISO8601
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(ZoneOffset.UTC); //вы можете использовать другую зону
        String iso8601 = zdt.toString();
        System.out.println("12 " + iso8601);

//        Преобразовать из строки ISO8601 обратно в LocalDateTime
        String iso8601_ = "2016-02-14T18:32:04.150Z";
        ZonedDateTime zdt_ = ZonedDateTime.parse(iso8601_);
        LocalDateTime ldt_ = zdt.toLocalDateTime();
        System.out.println("13 " + ldt_);

        //        ПОЛУЧИТЕ ТЕКУЩЕЕ ВРЕМЯ UTC В ТРЕБУЕМОМ ФОРМАТЕ
        // Current UTC time
        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);

        // GET LocalDateTime
        LocalDateTime localDateTime4 = utc.toLocalDateTime();
        System.out.println("*************" + localDateTime4);

        // formated UTC time
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println(" formats as " + dateTimeFormatter.format(localDateTime4));

        //GET UTC time for current date
        Date now = new Date();
        LocalDateTime utcDateTimeForCurrentDateTime = Instant.ofEpochMilli(now.getTime()).atZone(ZoneId.of("UTC")).toLocalDateTime();
        DateTimeFormatter dTF2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println(" formats as " + dTF2.format(utcDateTimeForCurrentDateTime));

        //найдем возраст человека
        System.out.println("years = " + findAgeOfPersonInYear(new Person("Bob", LocalDate.parse("1980-10-01"))));

        System.out.println("========================================");
        getRequiredTime(12, 30);

    }

    public static String convertToLocalDateTimeAsString(LocalDate dateToConvert) {
        return ISO_OFFSET_DATE_TIME.format(dateToConvert.atStartOfDay(ZoneId.of(ZoneId.systemDefault()
                .getId())));
    }

    public static LocalDateTime convertLocalDateTimeAsStringToLocalDateTime(String dateToString) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.ENGLISH);
        LocalDateTime date = LocalDateTime.parse(dateToString, formatter);
//        LocalDateTime localDateTime = LocalDateTime.parse(dateToConvert);
        return date;
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

    public static int findAgeOfPersonInYear(Person person) {
        LocalDate birthDate = person.getBirthDate();
        LocalDate currentDate = LocalDate.now();
        Period between = Period.between(birthDate, currentDate);
        return between.getYears();
    }

    /**
     * Метод выводящий даты с задамым шагом дней.
     *
     * @param amountSteps количество шагов (если 12 шагов то это эквивалентно 12 месяцам)
     * @param afterDays   через сколько дней
     * @return список дат в строковом представлении
     */
    public static List<LocalDate> getRequiredTime(long amountSteps, long afterDays) {
        //startDate → "24.06.2024"; - возможно стартовую дату можно также передавать в аргументе функции
        LocalDate startDate = LocalDate.of(2024, Month.JUNE, 24);
        System.out.println("Стартовая дата: " + startDate);
        System.out.println("Будет выполнено " + amountSteps + " шагов");
        LocalDate newDate;
        ArrayList<LocalDate> localDates = new ArrayList<>();
        long anchor = amountSteps;
        while (amountSteps > 0) {
            System.out.println("шаг: " + ((anchor + 1) - amountSteps));
            newDate = startDate;
            newDate = newDate.plus(afterDays, ChronoUnit.DAYS);
            localDates.add(newDate);
            System.out.println(newDate);
            amountSteps = amountSteps - 1;
            startDate = newDate;
        }
        System.out.println(localDates);
        return localDates;
    }
}