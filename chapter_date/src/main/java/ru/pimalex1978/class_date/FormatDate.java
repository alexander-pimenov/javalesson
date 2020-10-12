package ru.pimalex1978.class_date;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatDate {
    public static void main(String[] args) {
        Date date = new Date();
        //Pattern
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        //Вывод по формату
        System.out.println("Time in 12 Hour format - " + sdf.format(date));
        System.out.println("============================================");

        LocalDateTime localDateTime = LocalDateTime.now();
        //Pattern
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("hh:mm:ss a");
        System.out.println("Time in 12 Hour format - " + localDateTime.format(pattern));
        System.out.println("============================================");

        LocalTime time = LocalTime.now();
        // Pattern
        DateTimeFormatter pattern2 = DateTimeFormatter.ofPattern("hh:mm:ss a");
        System.out.println("Time in 12 Hour format - " + time.format(pattern2));
    }
}
