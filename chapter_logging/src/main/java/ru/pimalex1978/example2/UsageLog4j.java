package ru.pimalex1978.example2;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/*Этот класс обращается к файлу log4j.properties для правильного вывода информации в консоль,
 * т.е. это файл настройки вывода лога.*/
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
