package ru.pimalex1978.json;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Это относится уже не к JSON преобразованиям, а работой с сетевыми запросами,
 * в данном случае HTTP запрос на сервер и получение результата с него.
 */

public class JsonReadRequest {

    public static void main(String[] args) throws Exception {
        System.out.println(get("http://www.cbr-xml-daily.ru/daily_json.js"));

    }

    private static String get(String urlString) throws Exception {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();

        StringBuilder result = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }


}
