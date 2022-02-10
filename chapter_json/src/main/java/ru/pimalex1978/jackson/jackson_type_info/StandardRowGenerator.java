package ru.pimalex1978.jackson.jackson_type_info;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

/*https://github.com/DarrenForsythe/jackson-type-info/blob/master/src/main/java/com/darrenforsythe/jackson/type/info/StandardRowGenerator.java*/

@JsonTypeName(value = StandardRowGenerator.TYPE)
@Data
public class StandardRowGenerator implements RowGenerator{

    public static final String TYPE = "standard";

    private StandardConfig config;

    @Data
    public static class StandardConfig implements Config {
        private int rows;
        private int columns;
    }
}
