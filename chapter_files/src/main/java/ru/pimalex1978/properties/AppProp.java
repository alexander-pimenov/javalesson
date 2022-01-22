package ru.pimalex1978.properties;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProp {

    public static void main(String[] args) {
        AppProp appProp = new AppProp();

        FileInputStream fis;
        Properties property = new Properties();
        try {
            String propFileName1 = "loginMysql.properties";
            InputStream inputStream1 = AppProp.class.getClassLoader().getResourceAsStream(propFileName1);
//            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(inputStream1);

            String host = property.getProperty("db.host");
            String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");

            System.out.println("HOST: " + host
                    + ", LOGIN: " + login
                    + ", PASSWORD: " + password);

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

        try {

            Properties properties = new Properties();
            String propFileName = "loginMysql.properties";

            InputStream inputStream = AppProp.class.getClassLoader().getResourceAsStream(propFileName);

            properties.load(inputStream);

            String host = properties.getProperty("host");
            String login = properties.getProperty("login");
            String password = properties.getProperty("password");
            System.out.println(properties);
//            Connection connection = DriverManager.getConnection(host, login, password);
        } catch (IOException io) {
            System.out.println("IO");
        } /*catch (SQLException e) {
            System.out.println("sql");
        }*/

        try {

            Properties properties2 = new Properties();
            String propFileName2 = "config.properties";

            InputStream inputStream2 = AppProp.class.getClassLoader().getResourceAsStream(propFileName2);

            properties2.load(inputStream2);
//            System.out.println(properties2);
            String properties2Property = properties2.getProperty("db.password");
            System.out.println(properties2Property);

//            String host = properties2.getProperty("host");
//            String login = properties2.getProperty("login");
//            String password = properties2.getProperty("password");
//            System.out.println(properties2);
        } catch (IOException io) {
            System.out.println("IO");
        }

        //получение всех переменных среды
        System.getenv();


        /*Properties properties = System.getProperties();
//        System.out.println(properties);
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for ( Map.Entry<Object, Object> prop : properties.entrySet()){
            System.out.println(prop.getKey() + " = " + prop.getValue());
        }*/

    }
}

