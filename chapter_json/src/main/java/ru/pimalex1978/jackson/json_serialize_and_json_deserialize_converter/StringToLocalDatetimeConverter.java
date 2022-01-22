package ru.pimalex1978.jackson.json_serialize_and_json_deserialize_converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;

/*StdConverter<LocalDateTime, String> -> <IN, OUT>
 * Метод converter окнвертирует из строкового значения и вернет LocalDateTime значение.*/
public class StringToLocalDatetimeConverter extends StdConverter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String value) {
        return LocalDateTime.parse(value, LocalDateTimeToStringConverter.DATE_FORMATTER);
    }
}
