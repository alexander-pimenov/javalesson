package ru.pimalex;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.emptySchema;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

//не корректно работает, не разбирался почему
public class CsvToPojo {
    public static void main(String[] args) {
        File csvFile = new File("C:\\YNDX-BEAMERY\\Marketing - CSV\\2021-09-17 Registratsiia na Ia.Subbotnik po razrabotke infrastruktury - test.csv");
        try (final BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            // consume the first few lines here
            reader.readLine();
            reader.readLine();

            final MappingIterator<CandidateFromCSV> readValues =
                    new CsvMapper()
                            .readerFor(CandidateFromCSV.class)
                            .with(emptySchema()
                                    .withHeader()
                                    .withNullValue(""))
                            .readValues(reader);

            final List<CandidateFromCSV> records = readValues.readAll();
            System.out.println(records);
        } catch (Exception e) {
            System.out.println(("Failed to read detail section of transactionItem file."));
        }
    }
}
