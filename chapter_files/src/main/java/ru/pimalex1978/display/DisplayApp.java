package ru.pimalex1978.display;

import java.util.Properties;

/**
 * Как отобразить все свойства системы
 * https://mkyong.com/java/how-to-list-all-system-properties-key-and-value-in-java/?utm_source=mkyong.com&utm_medium=Referral&utm_campaign=afterpost-related&utm_content=link0
 */

public class DisplayApp {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        //Java 8
        properties.forEach((k, v) -> System.out.println(k + " : " + v));

        // Classic way to loop a map
        //for (Map.Entry<Object, Object> entry : properties.entrySet()) {
        //    System.out.println(entry.getKey() + " : " + entry.getValue());
        //}

        // No good, output is truncated, long lines end with ...
        // Ничего хорошего, вывод обрезан, длинные строки заканчиваются ...
        //properties.list(System.out);
    }
}

