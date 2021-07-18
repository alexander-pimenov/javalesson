package ru.pimalex1978.jackson.example5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCaseJsonSource = "{\n" +
            "  \"first_name\": \"Vova\",\n" +
            "  \"last_name\": \"Dubrovin\",\n" +
            "  \"middle_name\": \"Ivanovich\",\n" +
            "  \"birthday\": \"1975-11-11\",\n" +
            "  \"login\": \"vovadub\",\n" +
            "  \"femida_id\": 99544,\n" +
            "  \"gender\": \"male\"\n" +
            "}";

    private String dayScenario1 = "{\n" +
            "  \"date\": \"2010-12-25\",\n" +
            "  \"name\": \"Christmas Day\"\n" +
            "}";

    private String authorBookScenario = "{\n" +
            "  \"authorName\": \"Rui\",\n" +
            "  \"books\": [\n" +
            "    {\n" +
            "      \"title\": \"tittle1\",\n" +
            "      \"inPrint\": true,\n" +
            "      \"publishDate\": \"2019-12-25\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"tittle2\",\n" +
            "      \"inPrint\": false,\n" +
            "      \"publishDate\": \"2020-05-15\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Test
    void parse() throws JsonProcessingException {
        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        assertEquals(node.get("first_name").asText(), "Vova");
    }

    @Test
    void fromJson() throws JsonProcessingException {
        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        Candidate candidate = Json.fromJson(node, Candidate.class);
        System.out.println(candidate);

        assertEquals(candidate.getFirstName(), "Vova");
    }

    @Test
    void toJson() throws JsonProcessingException {
        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        Candidate candidate = Json.fromJson(node, Candidate.class);
        candidate.setBirthday(LocalDate.parse("2000-11-01"));
        String strCandidate = Json.toJson(candidate);

        System.out.println(strCandidate); //видим, что дата развернулась "2000-11-01" => "01-11-2000"

        assertEquals(candidate.getFirstName(), "Vova");
        assertEquals(candidate.getBirthday(), LocalDate.of(2000, Month.NOVEMBER, 1));
    }

    @Test
    void toJson_v2() {
        Candidate candidate = new Candidate();
        candidate.setFirstName("Bob");

        JsonNode node = Json.toJson_v2(candidate);
        System.out.println(node);

        assertEquals(node.get("firstName").asText(), "Bob");
    }

    @Test
    void stringify() throws JsonProcessingException {
        Candidate candidate = new Candidate();
        candidate.setFirstName("Bob");
        candidate.setBirthday(LocalDate.parse("1999-10-05"));

        JsonNode node = Json.toJson_v2(candidate);
        System.out.println(Json.stringify(node));

        System.out.println(Json.prettyPrint(node));

    }

    @Test
    void dayTestScenario1() throws JsonProcessingException {
        JsonNode node = Json.parse(dayScenario1);
        DayPOJO pojo = Json.fromJson(node, DayPOJO.class);
        System.out.println(" DATE : " + pojo.getDate());

        assertEquals("2010-12-25", pojo.getDate().toString());
    }

    @Test
    void authorBookScenario1() throws JsonProcessingException {
        JsonNode node = Json.parse(authorBookScenario);
        AuthorPOJO pojo = Json.fromJson(node, AuthorPOJO.class);
        System.out.println(" AuthorName : " + pojo.getAuthorName());

        for (BookPOJO book : pojo.getBooks()) {
            System.out.println(" Book : " + book.getTitle());
            System.out.println(" Is in print? - " + book.isInPrint());
            System.out.println(" Date : " + book.getPublishDate());
        }
    }
}