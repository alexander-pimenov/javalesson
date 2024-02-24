package ru.pimalex1978.class_date.work_shop_app;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Data
@Entity
public class TestEntity {

    @Id
    long id;

    OffsetDateTime offsetDateTime;
}
