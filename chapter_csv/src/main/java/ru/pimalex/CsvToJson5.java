package ru.pimalex;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/*
 * https://techcave.ru/posts/99-rabotaem-s-failami-csv-v-java-s-ispolzovaniem-biblioteki-opencsv.html
 */

public class CsvToJson5 {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        String path = "C:\\YNDX-BEAMERY\\Marketing - CSV\\2021-09-17 Registratsiia na Ia.Subbotnik po razrabotke infrastruktury - test.csv";
        extractedByOneLine(path);
//        extractedByFullLine(path);
//        csvMapperToJavaBean(path);
    }

    private static void extractedByOneLine(String path) throws IOException {
        //Build reader instance
        //Read data.csv
        //Default seperator is comma
        //Default quote character is double quote
        //Start reading from line number 2 (line numbers start from zero)
        CSVReader reader = new CSVReader(new FileReader(path), ',', '"', 1);
        //Read CSV line by line and use the string array as you want
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                //Verifying the read data here
                System.out.println(Arrays.toString(nextLine));
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void csvMapperToJavaBean(String path) throws IOException {
//        String csvFilename = "data.csv";
        CsvToBean csv = new CsvToBean();
        CSVReader csvReader = new CSVReader(new FileReader(path));
        //Set column mapping strategy
        List list = csv.parse(setColumMapping(), csvReader);
        for (Object object : list) {
            CandidateFromCSV candidateFromCSV = (CandidateFromCSV) object;
            System.out.println(candidateFromCSV);
        }
    }

    //Email,First Name,Middle Name,Last Name,Birthdate,Company,Role,Address,City,Country,
// PostalCode,School,Field of study,Grade,Skills,Links,I agree to give to Yandex Company my survey,
// I want receive information about vacancies,UID,Yandex login,Profile Tags,Global Tags
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ColumnPositionMappingStrategy setColumMapping() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(CandidateFromCSV.class);
        String[] columns = new String[]{"Email", "First Name", "Middle Name", "Last Name",
                "Birthdate", "Company", "Role", "Address", "City", "Country", "PostalCode",
                "School", "Field of study", "Grade", "Skills", "Links", "I agree to give to Yandex Company my survey",
                "I want receive information about vacancies", "UID", "Yandex login", "Profile Tags", "Global Tags"};
        strategy.setColumnMapping(columns);
        return strategy;
    }

    private static void extractedByFullLine(String path) throws IOException {
        //Build reader instance
        CSVReader reader = new CSVReader(new FileReader(path), ',', '"', 1);
        //Read all rows at once
        List<String[]> allRows = reader.readAll();
        //Read CSV line by line and use the string array as you want
        for (String[] row : allRows) {
            System.out.println(Arrays.toString(row));
        }
    }
}
