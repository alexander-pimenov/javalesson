package ru.pimalex1978.srp.employee_architecture;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс отвечающий за работу с БД.
 * Сохранение и удаление данных в БД.
 */
public class EmployeeDAO {
    /**
     * Метод для сохранения сотрудника в БД.
     *
     * @param employee объект сотрудника.
     */
    public void saveEmployee(Employee employee) throws SQLException {
        System.out.println("Выполняем соединение с БД...");
//        DatabaseConnectionManager.getInstance().connectToDB();
        //выполняем код по записи данных в БД
        System.out.println(employee + " saved !!!");
        System.out.println("Выполняем разъединение с БД...");
//        DatabaseConnectionManager.getInstance().disconnect();
    }

    /**
     * Метод для удаления сотрудника из БД.
     *
     * @param employee объект сотрудника.
     */
    public void deleteEmployee(Employee employee) throws SQLException {
        System.out.println("Выполняем соединение с БД...");
//        DatabaseConnectionManager.getInstance().connectToDB();
        //выполняем код по удалению записи из БД
        System.out.println(employee + " deleted !!!");
        System.out.println("Выполняем разъединение с БД...");
//        DatabaseConnectionManager.getInstance().disconnect();
    }
}
