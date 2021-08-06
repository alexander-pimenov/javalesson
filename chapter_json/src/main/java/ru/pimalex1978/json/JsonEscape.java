package ru.pimalex1978.json;

import com.fasterxml.jackson.databind.JsonNode;
import ru.pimalex1978.jackson.example5.Json;

import java.io.*;

public class JsonEscape {

    public static final String BACKSLASH_REGEX_TO_REPLACE = "(\\\\){2}\"";
    public static final String BACKSLASH_VALUE_TO_REPLACE_WITH = "\\\\\"";

    public static void main(String[] args) throws Exception {
//        String s = readJSON();
        String s1 = readData();
        System.out.println(s1);
        writeJSON(s1);
    }

    private static String readData() throws Exception {
        String result = get("resources/row_json.json");
        return result;
    }

    private static void writeJSON(String s) throws IOException {
        File src = new File("resources/new_json.json");
        BufferedWriter writer = new BufferedWriter((new FileWriter(src)));
        writer.write(s);
    }

    private static String readJSON() throws Exception {
        String rowJson = get("resources/row_json.json");
        String resJson = removeEscapeCharacter(rowJson);
        JsonNode node = Json.parse(resJson);
        String s = node.asText();
        return s;
    }

    private static String removeEscapeCharacter(String jsonValue) {
        return jsonValue.replaceAll(BACKSLASH_REGEX_TO_REPLACE, BACKSLASH_VALUE_TO_REPLACE_WITH);
    }

    private static String get(String srcData) throws Exception {
        File src = new File(srcData);
        StringBuilder result = new StringBuilder();
        try (BufferedReader rd = new BufferedReader(new FileReader(src))) {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        }
        return result.toString();
    }


}
