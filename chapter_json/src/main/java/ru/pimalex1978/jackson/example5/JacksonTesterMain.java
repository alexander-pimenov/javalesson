package ru.pimalex1978.jackson.example5;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class JacksonTesterMain {

    public static void main(String[] args) {
//        String jsonString = "{\"name\":\"Mahesh\",\"age\": \"21\"}"; //имеем строку json
        JacksonTesterMain tester = new JacksonTesterMain();
//        ObjectMapper mapper = JsonMapper.builder()
//                .findAndAddModules()
//                .build();
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
//        ObjectMapper mapper = new ObjectMapper();
        try {
            Student student = new Student();
            student.setAge(10);
            student.setName("Махеш");

            //запишем из объекта json на диск
            tester.writeJSON(student);

            //прочитаем json с диска
            Student readJSON = tester.readJSON();

            System.out.println(readJSON.toString());


            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
            System.out.println(jsonString);

            Candidate candidate = tester.readJsonCandidateFromTemplate();
            System.out.println(candidate);

            String candidateJson = "{\"first_name\":\"Vova\",\"last_name\":\"Dubrovin\",\"middle_name\":\"Ivanovich\",\"birthday\":\"1975-11-11\",\"login\":\"vovadub\",\"femida_id\":99544,\"gender\":\"male\"}";

            JsonNode node = Json.parse(candidateJson);
            System.out.println(node.get("first_name").asText());
            System.out.println(node.get("birthday").asText());

        } catch (JsonParseException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeJSON(Student student) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("student.json"), student); //будет записан в корень проекта
    }

    private Student readJSON() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("student.json"), Student.class); //читает из корня проекта
    }

    private Candidate readJsonCandidateFromTemplate() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        String candidateJson = "{\"first_name\":\"Vova\",\"last_name\":\"Dubrovin\",\"middle_name\":\"Ivanovich\",\"birthday\":\"1975-11-11\",\"login\":\"vovadub\",\"femida_id\":99544,\"gender\":\"male\"}";

        mapper.readValue(candidateJson, Candidate.class);
        return mapper.readValue(candidateJson, Candidate.class);
    }
}
