package ru.pimalex1978.jackson.example5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * https://github.com/FasterXML/jackson-modules-java8/tree/master/datetime#usage
 * <p>
 * Usage
 * Registering module: Datatype module to make Jackson recognize Java 8 Date & Time API data types (JSR-310).
 * Starting with Jackson 2.2, Modules can be automatically discovered using
 * the Service Provider Interface (SPI) feature. You can activate this by
 * instructing an ObjectMapper to find and register all Modules:
 * <p>
 * // Jackson 2.10 and later
 * ObjectMapper mapper = JsonMapper.builder()
 * .findAndAddModules()
 * .build();
 * // or, 2.x before 2.9
 * ObjectMapper mapper = new ObjectMapper();
 * mapper.findAndRegisterModules();
 * You should use this feature with caution as it has performance implications.
 * You should generally create one constant ObjectMapper instance for your entire application codebase to share, or otherwise use one of ObjectMapper's findModules methods and cache the result.
 * <p>
 * If you prefer to selectively register this module, this is done as follows, without the call to findAndRegisterModules():
 * <p>
 * // Jackson 2.10 and later:
 * ObjectMapper mapper = JsonMapper.builder()
 * .addModule(new JavaTimeModule())
 * .build();
 * // or, 2.x before 2.9
 * ObjectMapper mapper = new ObjectMapper();
 * mapper.registerModule(new JavaTimeModule());
 * After either of these, functionality is available for all normal Jackson operations.
 * <p>
 * <p>
 * Этот класс игргает роль утилитного класса.
 * С его помощью удобно выполнять сериализацию и десериализацию.
 * Примеры из уроков:
 * <p>
 * Parsing Json in Java Tutorial - Part 1, 2, 3
 * https://www.youtube.com/watch?v=Hv_a3ZBSO_g&t=264s
 * https://www.youtube.com/watch?v=zqoYDkDl2Z4
 * https://www.youtube.com/watch?v=vi1lU57U2p8
 */
public class Json {
    private static final ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
//        ObjectMapper defaultObjectMapper = new ObjectMapper();
        ObjectMapper customObjectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
                .build();
        // ------

        return customObjectMapper;
    }

    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }

    public static <A> String toJson(A clazz) throws JsonProcessingException {
        return objectMapper.writeValueAsString(clazz);
    }

    public static JsonNode toJson_v2(Object a) {
        return objectMapper.valueToTree(a);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generaString(node, false);
    }

    public static String prettyPrint(JsonNode node) throws JsonProcessingException {
        return generaString(node, true);
    }

    private static String generaString(JsonNode node, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if (pretty) {
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(node);
    }
}
