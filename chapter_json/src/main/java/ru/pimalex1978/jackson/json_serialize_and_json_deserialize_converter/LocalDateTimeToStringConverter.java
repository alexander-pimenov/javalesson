package ru.pimalex1978.jackson.json_serialize_and_json_deserialize_converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/*StdConverter<LocalDateTime, String> -> <IN, OUT>
 * Метод converter вернет нам строковое значение из LocalDateTime.*/
public class LocalDateTimeToStringConverter extends StdConverter<LocalDateTime, String> {

    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

    @Override
    public String convert(LocalDateTime value) {
        return value.format(DATE_FORMATTER);
    }
}
