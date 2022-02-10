package ru.pimalex1978.jackson.jackson_type_info;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

class JsonRowGeneratorTest {

    private ObjectMapper mapper;
    private String json;

    /*почему то блок Before не отрабатывает, поэтому создание mapper есть в каждом тесте*/
    @Before
    public void setup() {
        mapper = new ObjectMapper();
        System.out.println();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
    }

    @Test
    public void testDeserStandardTest() throws IOException {
        json = StringUtils.deleteWhitespace(
                new String(Files.readAllBytes(Paths.get("src/main/resources/data/standard.json")), StandardCharsets.UTF_8)
        );
        System.out.println(json);

        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        RowGenerator generator = mapper.readValue(json, RowGenerator.class);
        assertThat(generator).hasNoNullFieldsOrProperties().isExactlyInstanceOf(StandardRowGenerator.class);
        assertThat(generator.getConfig()).hasNoNullFieldsOrProperties().isExactlyInstanceOf(StandardRowGenerator.StandardConfig.class);
        assertThat(json).isEqualTo(mapper.writeValueAsString(generator));
        System.out.println(generator);
    }

    @Test
    public void testDeserTotalsize() throws Exception {
        json = StringUtils.deleteWhitespace(
                new String(Files.readAllBytes(Paths.get("src/main/resources/data/totalsize.json")), StandardCharsets.UTF_8));

        System.out.println(json);

        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        RowGenerator generator = mapper.readValue(json, RowGenerator.class);
        assertThat(generator).hasNoNullFieldsOrProperties().isExactlyInstanceOf(TotalSizeGenerator.class);
        assertThat(generator.getConfig()).hasNoNullFieldsOrProperties().isExactlyInstanceOf(TotalSizeGenerator.TotalSizeConfig.class);
        assertThat(json).isEqualTo(mapper.writeValueAsString(generator));
        System.out.println(generator);
    }

}