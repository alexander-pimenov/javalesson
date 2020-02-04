package ru.pimalex1978.class_date;

import java.util.Date;

public class TestDate {
    public static void main(String[] args) {

        //Простой пример использования Date для вывода даты в консоль.
        //для вывода мы можем вызывать метод toString(), а можно и без него.
        //Результат одинаковый.
        Date date = new Date();
        System.out.println(date); //Tue Feb 04 14:41:34 MSK 2020
        System.out.println(date.toString());
        //Вывыод:
        //Tue Feb 04 14:41:34 MSK 2020
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //С помощью метода getTime() можно отобразить количество миллисекунд, прошедших с 1 января 1970 года.
        long millis = date.getTime();
        System.out.println(String.valueOf(millis));
        //Вывод:
        //1580816494642
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");



    }
}
