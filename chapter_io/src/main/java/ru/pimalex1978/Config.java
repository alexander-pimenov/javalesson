package ru.pimalex1978;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    Config(final String path) {
        this.path = path;
    }

    /**
     * Метод читает из файла все ключи и значения разделенные знаком "="
     * в карту values.
     * Не читает пустые строки и строки начинающиеся с ##
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(s -> !s.isEmpty() || !s.startsWith("##"))
                    .filter(s -> s.contains("="))
                    .forEach(s -> values.put(s.substring(0, s.indexOf("=")), s.substring(s.indexOf("=") + 1))
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод читает из файла значение из карты values по ключу.
     *
     * @param key значение ключа из строки.
     * @return значение строки.
     */
    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
//        System.out.println(new Config("app.properties")); //файл не читался поэтому использую код ниже через getResources
        System.out.println(new Config(Config.class.getResource("/app1.properties").getFile()));
        System.out.println(new Config(Config.class.getResource("/app2.properties").getFile()));
    }
}
