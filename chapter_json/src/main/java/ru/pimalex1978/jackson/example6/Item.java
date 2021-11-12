package ru.pimalex1978.jackson.example6;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Мы можем зарегистрировать десериализатор непосредственно в классе
 * с помощью @JsonDeserialize(using = ItemDeserializer.class)
 * сначала будет отрабатывать лесериализотор, а потом уже если будут несмапленные
 * поля, то они будут игнорироваться благодаря @JsonIgnoreProperties(ignoreUnknown = true)
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = ItemDeserializer.class)
public class Item {
    public int id;
    public String itemName;
    public User owner;

    @Builder
    public Item(int id, String itemName, User owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }
}
