package ru.pimalex1978.srp.employee_architecture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс отвечающий за соединение с БД.
 * Воспроизведем нашу связь с БД.
 */
public class DatabaseConnectionManager {
    private Connection connection;
    String managerName;

    //Реализуем Синглтон
    private static DatabaseConnectionManager connectionManager = new DatabaseConnectionManager();

    //Приватный конструктор
    private DatabaseConnectionManager() {
    }

    public static DatabaseConnectionManager getInstance() {
        return connectionManager;
    }

    /**
     * Метод осуществляющий соединение с БД.
     */
    public void connectToDB() throws SQLException {
        connection = DriverManager.getConnection("Database URL");
        System.out.println("Connection to DB established");
    }

    /**
     * Метод возвращающий объект Connection.
     *
     * @return объект Connection.
     */
    public Connection getConnectionObject() {
        return connection;
    }

    /**
     * Метод отключающий нас от БД.
     */
    public void disconnect() throws SQLException {
        connection.close();
        System.out.println("Disconnection from DB");
    }
}
