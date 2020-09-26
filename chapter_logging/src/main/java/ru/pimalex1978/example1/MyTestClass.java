package ru.pimalex1978.example1;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Хорошее обучающее видео о логгировании https://www.youtube.com/watch?v=N4fmELeXw5I
 * В нем рассказывется для чего логгирование и, кратко, об его уровнях: TRACE, INFO, DEBUG, WARN, ERROR.
 * Здесь взят пример из https://javarush.ru/groups/posts/2293-zachem-nuzhno-logirovanie
 * И некоторая информация от сюда https://javarush.ru/groups/posts/2388-logirovanie-chto-kak-gde-i-chem
 * И от сюда http://skipy.ru/useful/logging.html#uls_slf
 * и от сюда https://logging.apache.org/log4j/2.x/manual/appenders.html
 * и от сюда https://github.com/romankh3/logger-lecture
 *  * и https://github.com/zenonwch/spring-mvc-beginner/blob/master/src/main/resources/log4j2.properties
 */

public class MyTestClass {
    /*Имя класса .getName() специально передается для того, чтобы знать, откуда идет логирование. */
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
//            LOGGER.error(x.getMessage(), x); //удобно искать ошибку в логе
        }

    }
}
