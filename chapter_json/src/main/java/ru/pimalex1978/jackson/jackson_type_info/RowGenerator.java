package ru.pimalex1978.jackson.jackson_type_info;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/*https://overcoder.net/q/1478412/%D0%BA%D0%B0%D0%BA-%D1%8F-%D0%BC%D0%BE%D0%B3%D1%83-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D1%8C-jsontypeinfo-%D0%B8-jsonsubtypes-%D0%B4%D0%BB%D1%8F-%D1%81%D0%BE%D0%B7%D0%B4%D0%B0%D0%BD%D0%B8%D1%8F-%D1%8D%D0%BA%D0%B7%D0%B5%D0%BC%D0%BF%D0%BB%D1%8F%D1%80%D0%BE%D0%B2
https://github.com/DarrenForsythe/jackson-type-info/blob/master/src/main/java/com/darrenforsythe/jackson/type/info/RowGenerator.java*/

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = RowGenerator.PROPERTY, defaultImpl = StandardRowGenerator.class)
@JsonSubTypes(value = {@Type(StandardRowGenerator.class), @Type(TotalSizeGenerator.class)})
@JsonRootName(value = "data")
public interface RowGenerator {
    String PROPERTY = "type";

    Config getConfig();
}
