package ru.pimalex;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.pimalex.CandidateFromCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CsvToJson4 {
    public static void main(String[] args) {
        File csvFile = new File("C:\\YNDX-BEAMERY\\Marketing - CSV\\2021-09-17 Registratsiia na Ia.Subbotnik po razrabotke infrastruktury - test.csv");
        Pattern pattern = Pattern.compile(",");
        try (BufferedReader in = new BufferedReader(new FileReader(csvFile));) {

            List<CandidateFromCSV> candidateFromCSVS = in.lines().skip(1).map(line -> {
                String[] x = pattern.split(line);
                return new CandidateFromCSV(List.of(x[0]), x[1], List.of(x[2]), x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10], x[11],
                        x[12], x[13], List.of(x[14]), List.of(x[15]), x[16], x[17], x[18], x[19], List.of(x[20]),
                        List.of(x[21]));
            }).collect(Collectors.toList());

            candidateFromCSVS.forEach(System.out::println);


            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(System.out, candidateFromCSVS);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //CsvMapper mapper = new CsvMapper();
    //mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
    //ObjectReader reader = mapper.readerFor(String[].class);
    //MappingIterator<String[]> values = reader.readValues("/path/to/file")

    //CsvSchema  schema = mapper.schemaFor(String[].class).withColumnSeparator('\t');
    //ObjectReader reader = mapper.readerFor(String[].class).with(schema);
}
