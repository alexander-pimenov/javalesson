package ru.pimalex1978.jackson.example3;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Jackson десериализация полиморфных типов.
 *
 */

public class JacksonDemo3 {
    public static void main(String[] args) throws IOException {
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.age = 5;

        Dog dog = new Dog();
        dog.name = "Killer";
        dog.age = 8;
        dog.owner = "Bill Jeferson";

        House house = new House();
        house.pets.add(dog);
        house.pets.add(cat);

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, house);
        System.out.println(writer.toString());
        //{"pets":[{"type":"dog","name":"Killer","age":8,"owner":"Bill Jeferson"},{"type":"cat","name":"Murka","age":5}]}

        //можно записать иначе, одной строкой, как для отдельного объекта cat так и house:
        String stringCat = new ObjectMapper().writeValueAsString(cat);
        System.out.println(stringCat);
        //{"type":"cat","name":"Murka","age":5}

        String stringHouse = new ObjectMapper().writeValueAsString(house);
        System.out.println(stringHouse);
        //{"pets":[{"type":"dog","name":"Killer","age":8,"owner":"Bill Jeferson"},{"type":"cat","name":"Murka","age":5}]}
    }
}
