package ru.pimalex1978.jackson.example1;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * К объектам, которые сериализуются/десериализуются в JSON есть несколько требований:
 * <p>
 * 1) поля должны быть видимые: или public или иметь getter’ы и setter’ы;
 * <p>
 * 2) должен быть конструктор по умолчанию (без параметров).
 *
 * @JsonAutoDetect - Ставится перед классом.
 * Помечает класс как готовый к сериализациив JSON.
 */

@JsonAutoDetect
public class Cat {
    @JsonProperty("alias") //Ставится перед свойством или getter’ом или setter’ом. Позволяет задать другое имя поля при сериализации.
    public String name;
    public int age;
    @JsonIgnore // Ставится перед полем. Поле игнорируется при сериализации.
    public int weight;
    public Cat[] cats = new Cat[0];

    public Cat() {
    }

}
