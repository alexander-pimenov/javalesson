package ru.pimalex1978.class_date.work_shop_app;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Data
@Entity
public class TestEntity {

    @Id
    long id;

    //Хорошая правктика сохранять время как момент, в UTC. Очень редко (практически никогда) нужно
    //знать с каким сдвигом пришло в БД время, т.е. из какой часовой зоны оно пришло.
    //Если вдруг это нужно будет, то можно написать свой дескриптор TimestampTypeDescriptor, где использовать
    //rs.getObject(2, OffsetDateTime.class)
    //Выставляя в application.properties :
    //spring.jpa.properties.hibernate.jdbc.time_zone=UTC
    //мы приводим время автоматом к Instant. (и это безопасно, т.к. Instant читается в UTC).
    //@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE") - это нужно настраивать, т.к. всё равно сохранится в TIMESTAMP
    //хотя это бло 3 года назад, скорее всего уже сейчас в 2023 это работает нативно
    //@Column(columnDefinition = "TIMESTAMP") - это сейчас работает из коробки
    OffsetDateTime offsetDateTime;
}
