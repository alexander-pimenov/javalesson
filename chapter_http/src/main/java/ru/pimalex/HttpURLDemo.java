package ru.pimalex;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpURLDemo {

    public static void print(Object s) {
        System.out.println(s);
    }

    /**
     * @param args аргументы ввода
     * @throws MalformedURLException exception
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//        URL url = new URL("http://ranter.kz");
        URL url = new URL("https://yandex.ru");

        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        print("Метод запроса: " + httpCon.getRequestMethod());
        print("Код ответа: " + httpCon.getResponseCode());
        print("Сообщение ответа: " + httpCon.getResponseMessage());
        Map<String, List<String>> hdrMap = httpCon.getHeaderFields();
        Set<String> hdrFields = hdrMap.keySet();
        print("\n-=Здесь следует заголовок=-");
        for (String key : hdrFields)
            print("" + key + ": " + hdrMap.get(key));
        //Создаем класс BufferedReader из InputStreamReader для которого указываем кодировку
        BufferedReader br = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}
