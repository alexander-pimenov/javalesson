package ru.pimalex1978.class_date.work_shop_app;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.any23.plugin.Author;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * Благодаря настройке logging.level.sql=debug мы можем видеть sql запросы к БД.
 * И мы видим, что создается таблица, у которой колонка offset_date_time с типом timestamp,
 * но этот тип не хранит информации о зоне (о сдвиге). Т.е. это тип без сдвига.
 * create table test_entity (id bigint not null, offset_date_time timestamp, primary key (id))
 * В этом случае hibernate приводит время к системной timezone текущей jvm.
 * А когда читает hibernate эту дату, то он и отдает в текущей timezone.
 * Это конечно большая проблема, т.к. сервера могут быть в разных timezone, но
 * работать могут с одной БД, и даты будут сохраняться с текущими timezone.
 * Решение: самый простой вариант, это хранить дату в UTC (использовать Instant)
 * <br>
 * spring.jpa.properties.hibernate.jdbc.time_zone=UTC
 * - эта настройка говорит hibernate любые даты, которые будут в наших энтити
 * приводит к зоне UTC и так их сохранять в БД - и это рекомендуемый способ сохранения дат в БД.
 */
@SpringBootTest
@Transactional
public class TestEntityTest {

    //т.к. для простоты решили не создавать слой репозитория,
    // а будем работать через EntityManager
    @PersistenceContext
    EntityManager entityManager;

    //используем его что бы увидеть, что сохраняется в БД
    @Autowired
    JdbcTemplate jdbcTemplate;

    ObjectMapper mapper = new ObjectMapper();


    @DisplayName("проверка отображения времени в БД")
    @Test
    public void testOffsetDateTest() {
        //1-й case
        TestEntity entity1 = new TestEntity();
        entity1.setId(1);
        entity1.setOffsetDateTime(OffsetDateTime.now());
        System.out.println("entity1 = " + entity1);

        //сохраним нашу сущность
        entityManager.persist(entity1);
        entityManager.flush();
        entityManager.clear();

        //достанем данные из БД, и посмотрим, что дежит во вотрой колонке в дате:
        jdbcTemplate.query("select * from test_entity", rs -> {
            String stringDateFromDB = rs.getString(2);
            System.out.println("stringDateFromDB = " + stringDateFromDB);
        });


        //достанем нашу сущность
        TestEntity fromDb = entityManager.find(TestEntity.class, 1L);
        System.out.println("fromDb1 = " + fromDb);

        //2-й case
        TestEntity entity2 = new TestEntity();
        entity2.setId(2);
        entity2.setOffsetDateTime(OffsetDateTime.now(ZoneId.of("+08:00")));
        System.out.println("entity2 = " + entity2);

        //сохраним нашу сущность
        entityManager.persist(entity2);
        entityManager.flush();
        entityManager.clear();

        //достанем данные из БД, и посмотрим, что дежит во вотрой колонке в дате:
        jdbcTemplate.query("select * from test_entity", rs -> {
            String stringDateFromDB = rs.getString(2);
            System.out.println("stringDateFromDB = " + stringDateFromDB);
        });

        //достанем нашу сущность
        TestEntity fromDb2 = entityManager.find(TestEntity.class, 2L);
        //и увидим, что всё равно время сохранилось в текущей timezone, т.е. сдвиг не сохранился.
        System.out.println("fromDb2 = " + fromDb2);

    }

    @Test
    void testJackson() {

    }
}