package ru.pimalex1978.jackson.jackson_type_info;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

/*https://github.com/DarrenForsythe/jackson-type-info/blob/master/src/main/java/com/darrenforsythe/jackson/type/info/TotalSizeGeneartor.java*/

@JsonTypeName(value = TotalSizeGenerator.TYPE)
@Data
public class TotalSizeGenerator implements RowGenerator {

    public static final String TYPE = "totalSize";
    private TotalSizeConfig config;

    @Data
    public static class TotalSizeConfig implements Config {
        private long sizeInBytes;
        private int cellDensityInBytes;
        private int columns;
    }
}
