package ru.pimalex1978.class_date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestDate {
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
    }
}
