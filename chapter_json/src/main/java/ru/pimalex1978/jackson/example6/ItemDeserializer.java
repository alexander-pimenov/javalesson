package ru.pimalex1978.jackson.example6;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;

/**
 * Как видите, десериализатор работает со стандартным представлением Джексона JSON - JsonNode .
 * Как только входной JSON представлен как JsonNode , теперь мы можем извлечь из него соответствующую
 * информацию и построить нашу собственную сущность Item .
 * <p>
 * Проще говоря, нам нужно зарегистрировать этот настраиваемый десериализатор и просто десериализовать
 * JSON в обычном режиме.
 */
public class ItemDeserializer extends StdDeserializer<Item> {

    public ItemDeserializer() {
        this(null);
    }

    public ItemDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Item deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        int id = (Integer) ((IntNode) node.get("id")).numberValue();
        String itemName = node.get("itemName").asText();
        int userId = (Integer) ((IntNode) node.get("createdBy")).numberValue();

        return new Item(id, itemName, new User(userId, null));
    }
}
