package ru.pimalex1978.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Хорошее обучающее видео о логгировании https://www.youtube.com/watch?v=N4fmELeXw5I
 * Выпуск 72. Логирование - важный инструмент для разработчика.
 * Здесь использована связка slf4j и log4j (в pom.xml есть три соответствующие зависимости)
 * В нем рассказывется для чего логгирование и, кратко, об его уровнях: TRACE, INFO, DEBUG, WARN, ERROR.
 * Здесь взят пример из https://javarush.ru/groups/posts/2293-zachem-nuzhno-logirovanie
 * И некоторая информация от сюда https://javarush.ru/groups/posts/2388-logirovanie-chto-kak-gde-i-chem
 * И от сюда http://skipy.ru/useful/logging.html#uls_slf
 * и от сюда https://logging.apache.org/log4j/2.x/manual/appenders.html
 * и от сюда https://github.com/romankh3/logger-lecture
 * и от сюда https://github.com/zenonwch/spring-mvc-beginner/blob/master/src/main/resources/log4j2.properties
 * и https://urvanov.ru/2019/07/08/%D0%BB%D0%BE%D0%B3%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-%D1%81-slf4j-%D0%B8-logback/
 */

public class TestClassLogging {
    /*Имя класса .getName() специально передается для того, чтобы знать, откуда идет логирование. */
    private static final Logger LOG = LoggerFactory.getLogger(TestClassLogging.class.getName());
    private static final String FILENAME = "/file/does/not/exist";

    public static void main(String[] args) {

        LOG.info("Начало работы программы!!!");
        LOG.trace("Начало работы программы. Сообщение из TRACE !");
        LOG.debug("Начало работы программы. Сообщение из DEBUG !");
        long dateTime = new Date().getTime();
        System.out.println(dateTime);

        try {
            LOG.trace("Зашли в блок try-catch.");

            //обычная проверка на четное или нечетное
            if (dateTime % 2 == 1) {
                LOG.warn("Внимание! программа пытается разделить одно число на другое");
                System.out.println(12 / 0);
            }

            LOG.warn("Внимание! Начинаем читать файл по указанному пути.");
            Files.readAllBytes(Paths.get(FILENAME));

        } catch (ArithmeticException x) {
            //("Ошибка! ArithmeticException. Произошло деление на ноль!", x) - здесь x это стектрейс
            //по нему удобно искать ошибки в программе.
            LOG.error("Ошибка! Произошло деление на ноль!", x);
//            LOGGER.error(x.getMessage(), x); //удобно искать ошибку в логе
        } catch (IOException e) {
            LOG.error("Error message from catch-block IOException. Failed to read file {}.", FILENAME, e);
            //LOG.error(e.getMessage(), e); //удобно искать ошибку в логе
        }
    }
}
