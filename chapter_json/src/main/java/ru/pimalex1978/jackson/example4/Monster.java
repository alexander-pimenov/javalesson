package ru.pimalex1978.jackson.example4;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Если нам нужно сериализовать любой из экземпляров класса Monster
 * в JSON (это классы Skeleton, Vampire), а потом десериализовать
 * его же, то нам нужно сохранять информацию о типе сериализованного
 * класса в JSON. Для этого используется аннотация
 * com.fasterxml.jackson.annotation.JsonTypeInfo.
 * Эту аннотацию нужно добавить к базовому классу иерархии,
 * в данном случае к классу Monster.
 * <p>
 * property указывает имя свойства в JSON, в которое будет записано имя типа Java.
 */
//можно использовать простые названия для типов классов с помощью property="type"
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = Skeleton.class, name = "s_k_e_l_e_t"),
//        @JsonSubTypes.Type(value = Vampire.class, name = "v_a_m_p_i_r")
//})

//можно использовать полные названия для типов классов с помощью property="@class"
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY,
        property = "@class")

public abstract class Monster {
    private int health;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
