package ru.pimalex1978.jackson.example3;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Jackson десериализация полиморфных типов.
 * <p>
 * С помощью аннотаций мы указываем, что JSON-представление будет
 * содержать специальное поле type, которое будет хранить значение cat,
 * для класса Cat и значение dog, для класса Dog.
 * Этой информации достаточно, чтобы выполнить корректную десериализацию
 * объекта в Json: при десериализации по значению поля type будет определяться
 * тип объекта, который надо создать.
 * <p>
 * Эта аннотация @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
 * добавляется к базовому классу иерархии, т.е. Pet.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cat.class, name = "cat"),
        @JsonSubTypes.Type(value = Dog.class, name = "dog")
})
class Pet {

    public String name;
}

class Cat extends Pet {

    public int age;
}

class Dog extends Pet {

    public int age;
    public String owner;

}

class House {
    public List<Pet> pets = new ArrayList<>();
}


