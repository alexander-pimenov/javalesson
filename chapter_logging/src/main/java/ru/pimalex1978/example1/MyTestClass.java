package ru.pimalex1978.example1;

/**
 * Хорошее обучающее видео о логгировании https://www.youtube.com/watch?v=N4fmELeXw5I
 * Здесь взят пример из https://javarush.ru/groups/posts/2293-zachem-nuzhno-logirovanie
 */

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class MyTestClass {
    public static final Logger LOGGER = LoggerFactory.getLogger(MyTestClass.class.getName());

    public static void main(String[] args) {

        LOGGER.info("Начало работы программы!!!");

        try {
            LOGGER.warn("Внимание! программа пытается разделить одно число на другое");
            System.out.println(12 / 0);

        } catch (ArithmeticException x) {
            //("Ошибка! Произошло деление на ноль!", x) - здесь x это стектрейс
            //по нему удобно искать ошибки в программе.
            //Смотри файл C:\test\testlog.txt
            LOGGER.error("Ошибка! Произошло деление на ноль!", x);
        }
    }
}
