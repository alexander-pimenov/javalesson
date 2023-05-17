package ru.pimalex1978;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * <a href="https://www.youtube.com/watch?v=MkfBsM_6dQg">Примеры применения caffeine и ehcache в java</a>
 * Это некоторая сущность данных, которая является эхо-сервером с задержкой 1 сек.
 * Какое-то значение приходит на вход, наш источник данных ждет 1 сек и возвращает значение, которое передали.
 */
@NoArgsConstructor
public class SlowDataSrc {
    private static final Logger logger = LoggerFactory.getLogger(SlowDataSrc.class.getName());
    public static long getValue(int key) {
        try {
            logger.info("эмуляция работы, вызывается задержка в 1 секунду");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return key;
    }
}