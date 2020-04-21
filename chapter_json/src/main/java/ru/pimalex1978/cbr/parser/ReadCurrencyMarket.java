package ru.pimalex1978.cbr.parser;

import com.google.gson.Gson;
import ru.pimalex1978.cbr.entitys.CurrencyMarket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * ресурс значения валют:
 * https://www.cbr-xml-daily.ru/daily_json.js
 * <p>
 * ресурс используемый для преобразования json в классы java:
 * http://www.jsonschema2pojo.org/
 */

public class ReadCurrencyMarket {

    private static final String URL = "https://www.cbr-xml-daily.ru/daily_json.js";

    public static void main(String[] args) throws Exception {


        Gson gson = new Gson();
        URL url = new URL(URL);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        CurrencyMarket currencyMarket = gson.fromJson(bufferedReader, CurrencyMarket.class);
        bufferedReader.close();


        String name = currencyMarket.getValute().getAMD().getName();
        Double value = currencyMarket.getValute().getAMD().getValue();
        System.out.println("AMD : " + name + ", " + value);

        String usd = currencyMarket.getValute().getUSD().getName();
        Double valueUsd = currencyMarket.getValute().getUSD().getValue();
        System.out.println("USD : " + usd + ", " + valueUsd);
        System.out.println("============================================");

        //используем метод getCurr
        CurrencyMarket cur = getCurr(URL);
        String dateCur = cur.getDate();
        String nameCAD = cur.getValute().getCAD().getName();
        Double valueCAD = cur.getValute().getCAD().getValue();
        System.out.println("CAD : " + nameCAD + ", " + valueCAD + ", на дату: " + dateCur.substring(0, 10));

        System.out.println("============================================");
        System.out.println(get(URL));

    }

    //метод для вычитывания всех данных из json объекта и сохранении их в объекте CurrencyMarket
    private static CurrencyMarket getCurr(String urlString) throws Exception {
        Gson gson = new Gson();
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        CurrencyMarket currencyMarket = gson.fromJson(bufferedReader, CurrencyMarket.class);
        bufferedReader.close();
        return currencyMarket;
    }

    //метод для вычитывания всех данных из json объекта, возвращает String
    private static String get(String urlString) throws Exception {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line).append("\n");
        }
        reader.close();
        return result.toString();
    }
}
