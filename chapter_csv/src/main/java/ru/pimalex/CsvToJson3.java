package ru.pimalex;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CsvToJson3 {
    public static String getJSONFromURL(URL url, String separator) throws IOException {

        String csv = IOUtils.toString(url);
        return getJSON(csv, separator);
    }

    public static String getJSONFromFile(String fileName, String separator)
            throws IOException {

        byte[] bytes = null;

        bytes = org.apache.commons.io.FileUtils
                .readFileToByteArray(new File(fileName));

        String csv = new String(bytes);
        return getJSON(csv, separator);


    }

    public static String getJSON(String content, String separator) {

        StringBuilder sb = new StringBuilder("[\n");

        String csv = content;
        String csvValues[] = csv.split("\n");
        String header[] = csvValues[0].split(separator);

        for (int i = 1; i < csvValues.length; i++) {
            sb.append("\t").append("{").append("\n");
            String tmp[] = csvValues[i].split(separator);
            for (int j = 0; j < tmp.length; j++) {
                sb.append("\t").append("\t\"").append(header[j]).append("\":\"").append(tmp[j]).append("\"");
                if (j < tmp.length - 1) {
                    sb.append(",\n");
                } else {
                    sb.append("\n");
                }
            }
            if (i < csvValues.length - 1) {
                sb.append("\t},\n");
            } else {
                sb.append("\t}\n");
            }
        }

        sb.append("]");

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getJSONFromFile("C:\\YNDX-BEAMERY\\Marketing - CSV\\2021-09-16 One Day Offer dlia frontend-razrabotchikov 13 marta 2021.csv", "\\,"));
//        System.out.println(getJSONFromFile("test/students.csv", "\\,"));
    }
}
