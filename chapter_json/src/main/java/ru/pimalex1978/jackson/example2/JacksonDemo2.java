package ru.pimalex1978.jackson.example2;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.pimalex1978.jackson.example2.Cat;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * Обрати внимание на результат сериализации.
 * <p>
 * Мы не сможем провести десериализацию этих данных обратно в Java-объекты,
 * т.к. они фактически неразличимы.
 */

public class JacksonDemo2 {
    public static void main(String[] args) throws IOException {

        //конвертация объекта в Json
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.age = 5;

        Dog dog = new Dog();
        dog.name = "Killer";
        dog.age = 8;
        dog.owner = "Bill Jeferson";

        ArrayList<Pet> pets = new ArrayList<>();
        pets.add(cat);
        pets.add(dog);

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, pets);
        System.out.println(writer.toString()); //[{"name":"Murka","age":5},{"name":"Killer","age":8,"owner":"Bill Jeferson"}]


    }
}
