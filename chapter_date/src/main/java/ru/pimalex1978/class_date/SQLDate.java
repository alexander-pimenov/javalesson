package ru.pimalex1978.class_date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * Этот класс показывает, что при переводе из java.util.Date
 * в java.sql.Date используя метод getTime() мы получаем только
 * дату, и не получаем отдлельно год или месяц (при этом имеем только 1970-01-01)
 *
 * utilDate: Thu Aug 20 19:34:48 MSK 2020
 * sqlDate: 2020-08-20
 * //sqlDateYear: 1970-01-01
 * //sqlDateMonth: 1970-01-01
 * */
public class SQLDate {
    public static void main(String[] args) {

        System.out.println("====Перобразование даты java.util.Date в java.sql.Date====");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//        java.sql.Date sqlDateYear = new java.sql.Date(utilDate.getYear()); //не использовать! не верно работает!
//        java.sql.Date sqlDateMonth = new java.sql.Date(utilDate.getMonth()); //не использовать! не верно работает!
        System.out.println("utilDate: " + utilDate);
        System.out.println("sqlDate: " + sqlDate);
//        System.out.println("sqlDateYear: " + sqlDateYear);
//        System.out.println("sqlDateMonth: " + sqlDateMonth);


        System.out.println("====Перобразование даты java.util.Date в java.sql.Date через Calendar====");
        java.util.Calendar cal = Calendar.getInstance();
        java.util.Date utilDate2 = new java.util.Date(); // your util date
        cal.setTime(utilDate2);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        java.sql.Date sqlDate2 = new java.sql.Date(cal.getTime().getTime()); // your sql date
        System.out.println("utilDate2:" + utilDate2); // вывод your util date
        System.out.println("sqlDate2:" + sqlDate2); // вывод your sql date


        System.out.println("====Перобразование даты java.util.Date по паттерну yyyy-MM-dd в java.sql.Date====");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date4 = sdf.parse("2020-12-25");
            System.out.println("date4: " + date4);
            System.out.println("sqlDate4: " + new java.sql.Date(date4.getTime()));

            final String formatDate = sdf.format(date4);
            System.out.println("date4: " + formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
