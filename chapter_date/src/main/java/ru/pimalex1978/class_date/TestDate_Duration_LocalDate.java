package ru.pimalex1978.class_date;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class TestDate_Duration_LocalDate {
    public static void main(String[] args) throws InterruptedException {

        //Простой пример использования Date для вывода даты в консоль.
        //для вывода мы можем вызывать метод toString(), а можно и без него.
        //Результат одинаковый.
        Date date1 = new Date();
        System.out.println(date1); //Tue Feb 04 14:41:34 MSK 2020
        System.out.println(date1.toString()); //Tue Feb 04 14:41:34 MSK 2020
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //С помощью метода getTime() можно отобразить количество миллисекунд, прошедших с 1 января 1970 года.
        long millis = date1.getTime();
        System.out.println(millis);
        //Вывод:
        //1580816494642
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        /*Сравнение дат
         * Специальные методы класса Date: before(), after() и equals().
         * Метод before() проверяет, была ли наша дата раньше той,
         * которую мы передаем в качестве аргумента: */

        Thread.sleep(2000);
        Date date2 = new Date();
        System.out.println(date1.before(date2)); //true

        /*Метод after() проверяет была ли наша дата позже той, которую
         * мы передаем в качестве аргумента*/
        System.out.println(date1.after(date2)); //false

        /*Метод equals() считает равными даты только в том случае, если
         * совпадают вплоть до миллисекунды*/
        System.out.println(date1.equals(date2));

        /*Метод getHours(), который возвращает количество часов из объекта Date.
         * Он deprecated, т.к. есть более удобная альтернатива
         * в его улучшенной, расширенной версии — класс Calendar.*/
        System.out.println(date2.getHours());

        Calendar calendar = new GregorianCalendar();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(calendar.getTime());
        System.out.println(hour);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        /*Вычислить дни между двумя датами
         * Если нужны логические календарные дни,
         * используйте метод DAYS.between() из java.time.temporal.ChronoUnit:*/
        LocalDate dateBefore;
        dateBefore = LocalDate.of(2020, 5, 10);
        LocalDate dateAfter;
        dateAfter = LocalDate.of(2020, 9, 22);
        long daysBetween = DAYS.between(dateBefore, dateAfter);
        //или так можно вызывать
        // ChronoUnit.DAYS считает дни, которые завершили 24 часа.:
        long daysBetweenChrono = ChronoUnit.DAYS.between(dateAfter, dateBefore); //так будет со знаком "-"
        //или так с использованием Duration
        final long durationDays = Duration.between(dateBefore.atStartOfDay(), dateAfter.atStartOfDay()).toDays();
        //Можно также вычислить количество часов между датами:
        final long durationHours = Duration.between(dateBefore.atStartOfDay(), dateAfter.atStartOfDay()).toHours();
        System.out.println("dateBefore : " + dateBefore);
        System.out.println("dateAfter : " + dateAfter);
        System.out.println("daysBetween : " + daysBetween);
        System.out.println("daysBetweenChrono : " + daysBetweenChrono);
        System.out.println("durationDays : " + durationDays + " дн.");
        System.out.println("durationHours : " + durationHours + " часов");

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        /*Если хотите буквальные 24-часовые дни (продолжительность),
        вместо этого вы можете использовать класс Duration :*/

        final long numberOfDays = Duration.between(dateBefore.atStartOfDay(), dateAfter.atStartOfDay()).toDays();
        System.out.println("dateBefore - dateAfter = " + numberOfDays);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        LocalDate today = LocalDate.now();
        System.out.println("today : " + today);
        LocalDate yesterday = today.minusDays(1);
        System.out.println("yesterday : " + yesterday);

        // Duration oneDay = Duration.between(today, yesterday); // throws an exception
        final long numberOfDays2 = Duration.between(today.atStartOfDay(), yesterday.atStartOfDay()).toDays();// another option
        System.out.println("today - yesterday = " + numberOfDays2);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        /*Получить количество дней до Рождества с текущего дня*/
        System.out.println("Количество дней до Рождества с текущего дня : "
                + ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(Year.now().getValue(), Month.DECEMBER, 25)));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Как получить текущее время по местному времени с точностью до миллисекунд используя Date Time API?
        final LocalDateTime localDateTime1 = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println(localDateTime1);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Как получить текущее время с точностью до миллисекунд используя Date Time API?
        final Instant instant1 = new Date().toInstant();
        System.out.println(instant1);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Как получить вторую субботу текущего месяца используя Date Time API?
        final LocalDate saturday = LocalDate
                .of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1)
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                .with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println(saturday);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Как получить следующий вторник используя Date Time API?

        final LocalDate tuesday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        System.out.println(tuesday);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Как добавить 1 неделю, 1 месяц, 1 год, 10 лет к текущей дате с использованием Date Time API?

        LocalDate.now().plusWeeks(1);
        LocalDate.now().plusMonths(1);
        LocalDate.now().plusYears(1);
        final LocalDate plus = LocalDate.now().plus(1, ChronoUnit.DECADES);
        System.out.println(plus);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }


}
