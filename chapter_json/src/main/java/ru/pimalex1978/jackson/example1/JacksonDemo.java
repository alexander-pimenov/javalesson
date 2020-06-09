package ru.pimalex1978.jackson.example1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Сконвертировать Java-объект в JSON примерно так же просто,
 * как и сериализовать его.
 * Для этого есть специальный класс ObjectMapper
 * (com.fasterxml.jackson.databind.ObjectMapper).
 * <p>
 * Библиотеку Jackson подключаем в pom.xml
 * (com.fasterxml.jackson.core)
 */

public class JacksonDemo {
    public static void main(String[] args) throws IOException {
        //создание объекта для сериализации в JSON, заполняем объект Cat данными
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.age = 5;
        cat.weight = 4;

        //писать результат сериализации будем во Writer(StringWriter)
        StringWriter writer = new StringWriter();

        //это объект Jackson, который выполняет сериализацию
        ObjectMapper mapper = new ObjectMapper();

        //сама сериализация: 1-куда, 2-что
        mapper.writeValue(writer, cat);

        //преобразовываем все записанное во StringWriter в строку
        String result = writer.toString();
        System.out.println(result);

//        //процесс десериализации
//        //String jsonString = "{\"name\":\"Murka\", \"age\":5, \"weight\":4}";
//        String jsonString = "{\"alias\":\"Murka\", \"age\":5, \"weight\":4}"; // но weight будет равен 0, т.к. над полем weight стоит @JsonIgnore
//
//        StringReader reader = new StringReader(jsonString);
//
//        ObjectMapper mapper1 = new ObjectMapper();
//
//        //выходе получаем готовый Java-объект со всеми данными
//        Cat cat1 = mapper1.readValue(reader, Cat.class);
//
//        System.out.println(cat1.name + " " + " " + cat1.age + " " + " " + cat1.weight);
    }
}

//Результат
//{"name":"Murka","age":5,"weight":4}