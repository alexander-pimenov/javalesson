package ru.pimalex1978.jackson.jackson_type_info;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

/*https://github.com/DarrenForsythe/jackson-type-info/blob/master/src/main/java/com/darrenforsythe/jackson/type/info/TotalSizeGeneartor.java*/

@JsonTypeName(value = DetailedSizeGenerator.TYPE)
@Data
public class DetailedSizeGenerator implements RowGenerator {

    public static final String TYPE = "detailedSize";
    private DetailedSizeConfig config;

    @Data
    public static class DetailedSizeConfig implements Config {
        private long sizeInBytes;
        private int cellDensityInBytes;
        private int rows;
        private int columns;
    }
}
