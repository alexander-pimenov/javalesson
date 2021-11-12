package ru.pimalex1978.jackson.example6;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.pimalex1978.utils.util.methods.CommonUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class MainApp {
    //для примера есть нескольок файлов .json Можно поэкспериментировать с ними.
    private static final String CANDIDATE_JSON_FROM_BASE_FILE_PATH = "data/candidate_from_base.json";
    private static final String ITEM_WITH_OWNER_FILE_PATH = "data/item_with_owner.json";
    private static final String ITEM_WITH_CREATEDBY_FILE_PATH = "data/item_with_createdby.json";

    private static final Logger LOG = LogManager.getLogger(MainApp.class);

    public static void main(String[] args) throws URISyntaxException, IOException {

        MainApp app = new MainApp();

        InputStream is = app.getFileFromResourceAsStream(ITEM_WITH_CREATEDBY_FILE_PATH);

        String jsonToString = CommonUtils.inputStreamToString(is, StandardCharsets.UTF_8.toString());
        System.out.println(jsonToString);

        Item item = new ObjectMapper().readValue(jsonToString, Item.class);
        System.out.println(item);


    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    /*
        The resource URL is not working in the JAR
        If we try to access a file that is inside a JAR,
        It throws NoSuchFileException (linux), InvalidPathException (Windows)

        Resource URL Sample: file:java-io.jar!/json/file1.json
     */
    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

    // print input stream
    private static void printInputStream(InputStream is) {

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // print a file
    private static void printFile(File file) {

        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
