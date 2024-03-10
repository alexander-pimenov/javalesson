package ru.pimalex1978.class_date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hibernate.type.OffsetDateTimeType;
import ru.pimalex1978.class_date.work_shop_app.TestEntity;
import ru.pimalex1978.utils.util.some.ConsoleColors;

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

    //посмортим, как работает Jackson ObjectMapper
    static ObjectMapper mapper = new ObjectMapper();

    static {
        //подключим модуль JavaTimeModule в этом блоке
        mapper.registerModule(new JavaTimeModule());

        // здесь отключим feature WRITE_DATES_AS_TIMESTAMPS (писать даты, как TIMESTAMPS)
        // и увидим, что Jackson выведет время в формате ISO - "2024-03-10T11:00:25.9249376Z" в UTC
        // mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // включим его в коде, что бы увидеть разницу.

    }

    public static void main(String[] args) throws JsonProcessingException {

        System.out.println(ConsoleColors.CYAN + "===пример работы со временем=(start)===" + ConsoleColors.RESET);
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
        System.out.println(ConsoleColors.CYAN + "===пример работы со временем=(end)===" + ConsoleColors.RESET);


        System.out.println(ConsoleColors.RED + "===пример работы с Jackson mapper=(start)===" + ConsoleColors.RESET);

        //Для того что бы Jackson работал с Time, то нужно подключить модуль JavaTimeModule()
        //это сделаем в static block.
        //mapper.registerModule(new JavaTimeModule());


        //создадим OffsetDateTime с указанием ZoneOffset.UTC
        OffsetDateTime time = OffsetDateTime.now(ZoneOffset.UTC);
        TestEntity entity = new TestEntity();
        entity.setOffsetDateTime(time);

        System.out.println("time in millisecond = " + time.toInstant().toEpochMilli());

        //здесь увидим, что Jackson выведет время в секундах
        String serializedDate = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
        System.out.println("serializedDate = " + serializedDate);


        // здесь отключим feature WRITE_DATES_AS_TIMESTAMPS (писать даты, как TIMESTAMPS)
        // и увидим, что Jackson выведет время в формате ISO - "2024-03-10T11:00:25.9249376Z" в UTC
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String serializedDate2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
        System.out.println("serializedDate2 = " + serializedDate2);


        //создадим OffsetDateTime без привязки к ZoneOffset.UTC
        OffsetDateTime time3 = OffsetDateTime.now();
        TestEntity entity3 = new TestEntity();
        entity3.setOffsetDateTime(time3);
        String serializedDate3 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity3);
        System.out.println("serializedDate3 = " + serializedDate3); //"2024-03-10T14:10:07.032149+03:00"


        System.out.println(ConsoleColors.RED + "===пример работы с Jackson mapper=(end)===" + ConsoleColors.RESET);

        /*
         * Как хорошая практика: принимаем даты со сдвигом - "2024-03-10T14:10:07.032149+03:00",
         * а возвращаем даты в формате UTC - "2024-03-10T11:10:07.032149Z"
         * */

        /*
         * Если нам нужно работать только с датой, то формат дата-время не нужно использовать,
         * используйте только дату.
         * LocalDate мапится на Hibernate Date - "2024-03-10".
         * */
    }
}
