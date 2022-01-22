package ru.pimalex1978.jackson.json_serialize_and_json_deserialize_converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

/**
 * https://www.logicbig.com/tutorials/misc/jackson/json-serialize-deserialize-converter.html
 * <p>
 * In this example we will learn how to do custom deserialization and serialization
 * using @JsonSerialize and @JsonDeserialize annotations.
 * <p>
 * We will use @JsonSerialize#converter and @JsonDeserialize#converter attributes.
 * These attributes require com.fasterxml.jackson.databind.util.Converter class type.
 * We will, of course, also need to provide the custom converter implementations.
 * <p>
 * Example
 * In this example we are going to convert LocalDateTime to a formatted string and vice-versa.
 */
public class ExampleMain {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("-- Java object to JSON --");
        CurrencyRate cr = new CurrencyRate();
        cr.setPair("USD/JPY");
        cr.setRate(109.15);
        cr.setLastUpdated(LocalDateTime.now());
        System.out.println("Java object: " + cr);

        ObjectMapper om = new ObjectMapper();
        String s2 = om.writeValueAsString(cr);
        System.out.println("JSON string: " + s2);

        System.out.println("-- JSON to Java object --");
        CurrencyRate cr2 = om.readValue(s2, CurrencyRate.class);
        System.out.println("Java Object: " + cr2);
    }
}
