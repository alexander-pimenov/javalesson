package ru.pimalex1978.class_date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Для того, чтобы отображать дату и время в удобном формате можно использовать класс SimpleDataFormat
 * При создании шаблона представления даты SimpleDateFormat использовались следующие параметры :
 * dd — означает день;
 * MM — месяц;
 * yyyy — год;
 * hh — часы;
 * mm — минуты;
 * В КАЧЕСТВЕ РАЗДЕЛИТЕЛЯ МОЖНО ИСПОЛЬЗОВАТЬ ЛЮБОЙ ТЕКСТ !!!
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
 */

public class TestSimpleDateFormat {


    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat format1;
        SimpleDateFormat format2;
        SimpleDateFormat format3;
        SimpleDateFormat format4;
        SimpleDateFormat format5;

        //Задаем 1-й шаблон представления даты
        format1 = new SimpleDateFormat(
                "dd.MM.yyyy hh:mm");
        //Задаем 2-й шаблон представления даты
        format2 = new SimpleDateFormat(
                "День dd Месяц MM Год yyyy Время hh:mm");
        //Задаем 3-й шаблон представления даты
        format3 = new SimpleDateFormat(
                "День dd Месяц MM Год yyyy");
        //Задаем 4-й шаблон представления даты
        format4 = new SimpleDateFormat(
                "Время hh:mm");
        //Задаем 5-й шаблон представления даты
        format5 = new SimpleDateFormat(
                "День dd Месяц MM Год yyyy Неделя W Часовой пояс z");

        System.out.println(
                format1.format(date)  // 04.02.2020 02:47
        );
        System.out.println(
                format2.format(date)
                // День 04 Месяц 02 Год 2020 Время 02:47
        );
        System.out.println(
                format3.format(date)
                // День 04 Месяц 02 Год 2020
        );
        System.out.println(
                format4.format(date)
                // Время 02:47
        );
        System.out.println(
                format5.format(date)
                // День 04 Месяц 02 Год 2020 Неделя 1 Часовой пояс MSK
        );
    }
}
