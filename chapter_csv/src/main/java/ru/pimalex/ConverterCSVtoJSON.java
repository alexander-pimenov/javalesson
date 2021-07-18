package ru.pimalex;

import java.io.*;
import java.util.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.*;

public class ConverterCSVtoJSON {
    public static void main(String[] args) {
        File input = new File("C:\\Temp\\testdatabase\\users_phones_list.csv");
//        File input = new File("input.csv");
        try {
            CsvSchema csv = CsvSchema.emptySchema().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            MappingIterator<Map<?, ?>> mappingIterator =  csvMapper.reader().forType(Map.class).with(csv).readValues(input);
            List<Map<?, ?>> list = mappingIterator.readAll();
            System.out.println(list);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
