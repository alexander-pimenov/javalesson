package ru.pimalex1978.example2;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
 * и https://github.com/zenonwch/spring-mvc-beginner/blob/master/src/main/resources/log4j2.properties
 * и https://urvanov.ru/2019/07/04/%d0%bb%d0%be%d0%b3%d0%b8%d1%80%d0%be%d0%b2%d0%b0%d0%bd%d0%b8%d0%b5-%d1%81-log4j-2-%d0%b2-java/
 *
 * <p>
 * Этот класс обращается к файлу log4j.properties для правильного вывода информации в консоль,
 * * т.е. это файл настройки вывода лога.
 * <p>
 * Касаемо вопроса по-поводу уровней логгирования. То негласные договоренности такие:
 * <p>
 * Уровень INFO - просто информирование о неком событии
 * Уровень DEBUG - используется при отладке
 * Уровень WARN - сообщение об ошибке или нестандартной ситуации, которая потенциально опасна
 * Уровень ERROR - сообщение об ошибке, после которой работа программы все еще возможна
 * Уровень FATAL - сообщение об ошибке, после которой нормальная работа программы невозможна.
 * Обычно после этого работа программы прекращается.
 */

public class UsageLog4j {
    // Так пишем логер используя org.apache.log4j.Logger:
    //    private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());

    // Так пишем логер используя org.slf4j.Logger:
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    private static final String FILENAME = "/file/does/not/exist";

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message. Message for debug level.");
        LOG.info("info message. Application works. Just a log message.");
        LOG.warn("warn message. Something to warn");
        LOG.error("error message. Something failed.");
        try {
            LOG.trace("trace message. Зашли в блок try-catch");

            Files.readAllBytes(Paths.get(FILENAME));
        } catch (IOException e) {
            //Используя org.slf4j.Logger мы можем использовать строку с параметром {}:
            LOG.error("Error message from catch-block. Failed to read file {}.", FILENAME, e);
//            LOG.error(e.getMessage(), e); //удобно искать ошибку в логе

            //Используя org.apache.log4j.Logger мы не сможем использовать {}
//            LOG.error("Error message from catch-block. Failed to read file.", e);
//            e.printStackTrace();
        }
    }
    //Результат Смотри в корне в файле  \logs\testLOG.txt
}
