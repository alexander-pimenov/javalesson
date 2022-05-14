package ru.pimalex1978.jackson.jackson_type_info;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/*Класс проверки читает ваши две конфигурации в папке ресурса, записывает их в объект
и обратно в строку, сравнивающую до/после, что нет нулевых или пустых свойств и
что интерфейсы имеют правильную реализацию.*/
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
    public void deserStandardTest() throws IOException {
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
    public void deserTotalsizeTest() throws Exception {
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

    @Test
    public void deserDetailedSizeTest() throws Exception {
        json = StringUtils.deleteWhitespace(
                new String(Files.readAllBytes(Paths.get("src/main/resources/data/detailedsize.json")), StandardCharsets.UTF_8));

        System.out.println(json);

        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        RowGenerator generator = mapper.readValue(json, RowGenerator.class);
        assertThat(generator).hasNoNullFieldsOrProperties().isExactlyInstanceOf(DetailedSizeGenerator.class);
        assertThat(generator.getConfig()).hasNoNullFieldsOrProperties().isExactlyInstanceOf(DetailedSizeGenerator.DetailedSizeConfig.class);
        assertThat(json).isEqualTo(mapper.writeValueAsString(generator));
        System.out.println(generator);
        Config config = generator.getConfig();
        Field[] fields = config.getClass().getDeclaredFields();
        System.out.println(Arrays.asList(fields));
        /*for (Field field : fields){
            System.out.println(field);
//            field.setAccessible(true);
        }*/

        Class<?> aClass1 = Class.forName("ru.pimalex1978.jackson.jackson_type_info.DetailedSizeGenerator");
        System.out.println(aClass1.getName());
        System.out.println(aClass1.getSimpleName());
        int modifiers = aClass1.getModifiers();
        System.out.println(Modifier.isPublic(modifiers));
        Class<?> aClass2 = Class.forName("ru.pimalex1978.jackson.jackson_type_info.DetailedSizeGenerator$DetailedSizeConfig");
        System.out.println(aClass2.getName());
        System.out.println(aClass2.getSimpleName());

        /*Мы получили объект Field с ссылкой на наш sizeInBytes. Т.к. поле не было публичным (public) следует дать
         * доступ для работы с ним. Метод setAccessible(true) разрешает нам дальнейшую работу. Теперь поле sizeInBytes
         * полностью под нашим контролем! Получить его значение можно вызовом get(Object) у объекта Field, где Object —
         * экземпляр нашего класса config. Приводим к типу long и присваиваем нашей переменной sizeInBytesLong.*/
        Field sizeInBytes = aClass2.getDeclaredField("sizeInBytes");
        sizeInBytes.setAccessible(true);
        Object sizeInBytes1 = sizeInBytes.get(config);
        long sizeInBytesLong = sizeInBytes.getLong(config);
        System.out.println(sizeInBytes1);
        System.out.println(sizeInBytesLong);

        //можем в поле установить своё значение
        sizeInBytes.set(config, 123456L);
        System.out.println(config);
    }

}