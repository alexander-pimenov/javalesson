package ru.pimalex.base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Какой класс появился в Java 8 для кодирования/декодирования данных?
 * Base64 - потокобезопасный класс, который реализует кодировщик и декодировщик данных,
 * используя схему кодирования base64 согласно RFC 4648 и RFC 2045.
 * <p>
 * Base64 содержит 6 основных методов:
 * <p>
 * getEncoder()/getDecoder() - возвращает кодировщик/декодировщик base64, соответствующий стандарту RFC 4648;
 * getUrlEncoder()/getUrlDecoder() - возвращает URL-safe кодировщик/декодировщик base64, соответствующий стандарту RFC 4648;
 * getMimeEncoder()/getMimeDecoder() - возвращает MIME кодировщик/декодировщик, соответствующий стандарту RFC 2045.
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //Как создать Base64 кодировщик и декодировщик?

        // Encode
        String b64 = Base64.getEncoder().encodeToString("input".getBytes(StandardCharsets.UTF_8)); //aW5wdXQ=
        System.out.println(b64);
        // Decode
        final String decode = new String(Base64.getDecoder().decode("aW5wdXQ="), "utf-8");//input
        System.out.println(decode);
    }
}
