package ru.pimalex1978.jackson.json_serialize_and_json_deserialize_converter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CurrencyRate {

    private String pair;
    private double rate;
    @JsonSerialize(converter = LocalDateTimeToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalDatetimeConverter.class)
    private LocalDateTime lastUpdated;
}
