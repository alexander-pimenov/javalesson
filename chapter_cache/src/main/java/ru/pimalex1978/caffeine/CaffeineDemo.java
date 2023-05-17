package ru.pimalex1978.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pimalex1978.SlowDataSrc;

import java.time.Duration;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Если сравнивать кэши от Ehcache и Caffeine, то Caffeine побыстрее, но у Ehcache чуть больше
 * возможностей. У Caffeine нет возможности создать лисенеры на Добавление и Изменение данных.
 * Так же с Caffeine можно работать, как с мапой. Вызвать cache.asMap() и нам вернется ConcurrentMap<K, V>
 * И в Ehcache и в Caffeine есть одинаковые методы, но с разными названиями.
 * Если нужны лисенеры на Добавление и Изменение данных в кэше, то Ehcache подойдет больше.
 * Так же Ehcache имеет возможность хранить данные на диске, а не только в ОЗУ.
 */
public class CaffeineDemo {
    private static final Logger logger = LoggerFactory.getLogger(CaffeineDemo.class);
    private final Cache<Integer, Long> cache;

    public static void main(String[] args) {
        new CaffeineDemo().start();
    }

    public CaffeineDemo() {
        logger.info("Cache creating (создание кэша - вызов конструктора, в котором инициализируем кэш)");
        //создаем кэш
        cache =
                Caffeine.newBuilder()
                        .maximumSize(5) //указываем размер кэша - 5 элементов, что сверх - будет вытесняться
                        .expireAfterWrite(Duration.ofMinutes(60)) //время жизни объектов в кэше, потом они удалятся
                        .removalListener( //можно задать лисенер в случае удаления ключа
                                //если значение из кэша удалим, то вызовется эта лямбда
                                (Integer key, Long value, RemovalCause cause) ->
                                        logger.info(
                                                "Key:{} was removed, value:{}, cause:{}",
                                                key,
                                                value,
                                                cause))
                        .build();
        logger.info("Cache setup is done");
    }

    private void start() {
        logger.info("FIRST getting...");
        IntStream.range(1, 10).forEach(val -> logger.info("value: {}", getValue(val)));
        logger.info("SECOND getting...");
        IntStream.range(1, 10)
                .map(i -> 10 - i)
                .forEach(val -> logger.info("value: {}", getValue(val)));

        printAll();
    }

    private void printAll() {
        logger.info("content");
        //С Caffeine можно работать, как с мапой. Вызвать cache.asMap() и нам вернется ConcurrentMap<K, V>
        //for (var entry : cache.asMap().entrySet()) {
        for (Map.Entry<Integer, Long> entry : cache.asMap().entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            logger.info("key:{}, value:{}", key, value);
        }
    }

    /**
     * Метод для получения данных из кэша и наполнения кэша данными.
     * У Caffeine есть метод 'V get(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);'
     * который либо отдаст данные, если они есть в кэше, либо каким то образом сохранит эти
     * данные в кэш. Т.е. по сути Caffeine сам выполнит логику 'cache.put(key, value);'
     * <p>
     * Это можно увидеть в логах.
     *
     * @param key ключ по которому в кэше лежит значение
     * @return возвращает с задержкой входное значение (так работает эхо сервер)
     */
    private long getValue(int key) {
        return cache.get(key, SlowDataSrc::getValue);
    }
}
