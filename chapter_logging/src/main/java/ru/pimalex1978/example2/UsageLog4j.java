package ru.pimalex1978.example2;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Хорошее обучающее видео о логгировании https://www.youtube.com/watch?v=N4fmELeXw5I
 * В нем рассказывется для чего логгирование и, кратко, об его уровнях: TRACE, INFO, DEBUG, WARN, ERROR.
 * Здесь взят пример из https://javarush.ru/groups/posts/2293-zachem-nuzhno-logirovanie
 * И некоторая информация от сюда https://javarush.ru/groups/posts/2388-logirovanie-chto-kak-gde-i-chem
 * И от сюда http://skipy.ru/useful/logging.html#uls_slf
 * и от сюда https://logging.apache.org/log4j/2.x/manual/appenders.html
 * и от сюда https://github.com/romankh3/logger-lecture
 * и https://github.com/zenonwch/spring-mvc-beginner/blob/master/src/main/resources/log4j2.properties
 * <p>
 * Этот класс обращается к файлу log4j.properties для правильного вывода информации в консоль,
 * * т.е. это файл настройки вывода лога.
 */

public class UsageLog4j {
    private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
    //Результат Смотри в файле  C:\test\testlog.txt
}
