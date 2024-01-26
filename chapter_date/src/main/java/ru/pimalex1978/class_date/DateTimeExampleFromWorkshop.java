package ru.pimalex1978.class_date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/*Spring workshop #5 (Date Time) от Александра Белова.
 * https://youtu.be/MVybQL6q1qk
 * */
public class DateTimeExampleFromWorkshop {
    public static void main(String[] args) {

        Instant instant = Instant.now();
        System.out.println("instant = " + instant); //2024-01-17T17:44:18.827205100Z

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println("offsetDateTime = " + offsetDateTime); //2024-01-17T20:44:18.827205100+03:00
        var instantFromOffsetDateTime = OffsetDateTime.now().toInstant();
        System.out.println("instantFromOffsetDateTime = " + instantFromOffsetDateTime); //2024-01-17T17:44:18.827205100Z
        OffsetDateTime offsetDateTimeUtc = OffsetDateTime.now(ZoneOffset.UTC);
        System.out.println("offsetDateTimeUtc = " + offsetDateTimeUtc); //2024-01-17T17:44:18.827205100Z

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("zonedDateTime = " + zonedDateTime); //2024-01-17T20:44:18.827205100+03:00[Europe/Moscow]
        //в 2014-10-26 02:00:00 в Moscow был перевод часов на час назад, и можем увидеть это в сдвиге.
        // Сдвиг и зона дают полную информацию о времени. Один сдвиг не даст этой полной информации, т.к. в одно времени например в час
        // тридцать ночи 01:30 используя только сдвиг часы были два раза.
        ZonedDateTime zonedDateTimeOf1 = ZonedDateTime.of(2014, 10, 26, 1, 0, 0, 0, ZoneId.of("Europe/Moscow"));
        System.out.println("zonedDateTimeOf1 = " + zonedDateTimeOf1); //2014-10-26T01:00+04:00[Europe/Moscow]
        ZonedDateTime zonedDateTimeOf3 = ZonedDateTime.of(2014, 10, 26, 3, 0, 0, 0, ZoneId.of("Europe/Moscow"));
        System.out.println("zonedDateTimeOf3 = " + zonedDateTimeOf3); //2014-10-26T03:00+03:00[Europe/Moscow]

        ZonedDateTime zonedDateTimeWithEarlier = ZonedDateTime.of(2014, 10, 26, 1, 30, 0, 0, ZoneId.of("Europe/Moscow"))
                .withEarlierOffsetAtOverlap();
        System.out.println("zonedDateTimeWithEarlier = " + zonedDateTimeWithEarlier); //2014-10-26T01:30+04:00[Europe/Moscow]

        ZonedDateTime zonedDateTimeWithLater = ZonedDateTime.of(2014, 10, 26, 1, 30, 0, 0, ZoneId.of("Europe/Moscow"))
                .withLaterOffsetAtOverlap();
        System.out.println("zonedDateTimeWithLater = " + zonedDateTimeWithLater); //2014-10-26T01:30+03:00[Europe/Moscow]

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime); //2024-01-17T20:44:18.827205100

        //https://youtu.be/MVybQL6q1qk?t=2018



    }
}
