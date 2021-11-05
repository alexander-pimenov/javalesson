package ru.pimalex;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.util.List;

public class CsvToJson6 {
    public static void main(String[] args) throws Exception {
        String path = "C:\\YNDX-BEAMERY\\Marketing - CSV\\2021-09-17 Registratsiia na Ia.Subbotnik po razrabotke infrastruktury - test.csv";
        File file = new File(path);
        List<CandidateFromCSV> candidateFromCSVS = readFile(file);
        List<CandidateFromCSV> candidates1 = readFileWithoutHeader(file);
        candidates1.forEach(System.out::println);
    }

    public static List<CandidateFromCSV> readFile(File csvFile) throws Exception {
        MappingIterator<CandidateFromCSV> candidateIter = new CsvMapper().readerWithTypedSchemaFor(CandidateFromCSV.class).readValues(csvFile);

        return candidateIter.readAll();
    }

    public static List<CandidateFromCSV> readFileWithoutHeader(File csvFile) throws Exception {
        CsvMapper mapper = new CsvMapper();

              CsvSchema sclema = mapper.schemaFor(CandidateFromCSV.class)
                  .withSkipFirstDataRow(true)
                  .withColumnSeparator(',').withoutQuoteChar();

              MappingIterator<CandidateFromCSV> iterator = mapper
                  .readerFor(CandidateFromCSV.class)
                  .with(sclema).readValues(csvFile);

              List<CandidateFromCSV> candidateFromCSVIter = iterator.readAll();

        return candidateFromCSVIter;
    }
}
