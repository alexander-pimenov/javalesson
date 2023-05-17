package ru.pimalex1978.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pimalex1978.SlowDataSrc;

import java.util.stream.IntStream;

/**
 * Если сравнивать кэши от Ehcache и Caffeine, то Caffeine побыстрее, но у Ehcache чуть больше
 * возможностей. У Caffeine нет возможности создать лисенеры на Добавление и Изменение данных.
 * И в Ehcache и в Caffeine есть одинаковые методы, но с разными названиями.
 * Если нужны лисенеры на Добавление и Изменение данных в кэше, то Ehcache подойдет больше.
 * Так же Ehcache имеет возможность хранить данные на диске, а не только в ОЗУ.
 */
public class EhcacheDemo {
    private static final Logger logger = LoggerFactory.getLogger(EhcacheDemo.class.getName());
    private final CacheManager cacheManager;
    private final Cache<Integer, Long> cache;

    public static void main(String[] args) {
        new EhcacheDemo().start();
    }

    public EhcacheDemo() {
        logger.info("Cache creating (создание кэша - вызов конструктора, в котором инициализируем кэш)");
        //создаем кэшменеджер
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);

        //указываем конфигурацию лисенеров на добавление и изменение элементов кэша
        //когда что-то в кэш добавляем или изменяем в нем, то вызовется эта лямбда функция
        var cacheEventListenerConfiguration =
                CacheEventListenerConfigurationBuilder.newEventListenerConfiguration(
                                event ->
                                        logger.info(
                                                "updated key: {}, value: {}",
                                                event.getKey(),
                                                event.getNewValue()),
                                EventType.CREATED,
                                EventType.UPDATED)
                        .ordered() //это говорит, что лисенеры будут вызываться в том порядке, в котором были добавлены в кэш
                        .synchronous(); //это говорит, что лисенеры будут вызываться синхронно - сначала отработает лисененр, а потом
        //будет выполняться кэш дальше

        //создаем сам кэш
        cache =
                cacheManager.createCache(
                        "Demo-Cache", //хадем имя кэшу
                        CacheConfigurationBuilder.newCacheConfigurationBuilder( //определяем параметры кэша
                                        Integer.class, //ключи - Integer
                                        Long.class, //значения - Long
                                        ResourcePoolsBuilder.heap(5)) //указываем размер кэша - 5 элементов, что сверх - будет вытесняться
                                .withService(cacheEventListenerConfiguration) //передаем сюда конфигурацию лисенера
                                .build());

        logger.info("Cache setup is done.");
    }

    private void start() {
        logger.info("FIRST getting...");
        //обращаемся к нашему медленному источнику данных
        //отправляем числа от 1 до 10
        //в логе видим, что передаваемое на вход в метод getValue(val) число отдается с задержкой в 1 сек
        //т.к. при заполнении кэша еще срабатывает метод SlowDataSrc.getValue(key).
        IntStream.range(1, 10).forEach(val -> logger.info("value: {}", getValue(val)));
        logger.info("SECOND getting...");
        //еще раз обращаемся к нашему медленному источнику данных
        //отправляем числа от 10 до 1
        //и в логе видим, что первые числа (5 шт - размер кэша) отдаются без задержки в 1 сек, т.к.
        // они уже содержаться в кэше и он их отдаем мгновенно, а последующие числа будут опять сохраняться в кэш,
        //и отдаваться с  задержкой, т.к. вызывается метод SlowDataSrc.getValue(key)
        IntStream.range(1, 10)
                .map(i -> 10 - i)
                .forEach(val -> logger.info("value: {}", getValue(val)));

        //выведем на печать содержимое кэша
        printAll();
        closeEhcache();
    }

    /**
     * Метод для получения данных из кэша и наполнения кэша данными.
     * Т.к. кэш это мапа, то и наполняется он как мапа с помощью ключ-значения.
     * Метод смотрит, есть ли нужное значение в кэше есть, то оно вернется, если нет, то сохранится в кэш
     * и потом вернем. Но т.к. перед сохранением в кэш вызывается метод SlowDataSrc.getValue(key), в
     * котором происходит задержка в 1 секунду, то метод отдает значение с задержкой.
     * Когда данные в кэше уже есть, в if мы уже не заходим, а сразу отдаем данные из кэша,
     * и задержки в 1 сек нет.
     * Это можно увидеть в логах.
     *
     * @param key ключ по которому в кэше лежит значение
     * @return возвращает с задержкой входное значение (так работает эхо сервер)
     */
    private long getValue(int key) {
        Long value = cache.get(key);
        if (value == null) {
            value = SlowDataSrc.getValue(key);
            cache.put(key, value);
        }
        return value;
    }

    /**
     * Печатаем содержимое кэша. Наглядно видно, что в нем есть.
     * Структура кэша - мапа.
     */
    private void printAll() {
        logger.info("content");
        for (Cache.Entry<Integer, Long> entry : cache) {
            var key = entry.getKey();
            var value = entry.getValue();
            logger.info("key:{}, value:{}", key, value);
        }
    }

    private void closeEhcache() {
        logger.info("Закрываем кэш");
        cacheManager.close();
    }
}
