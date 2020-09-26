package ru.pimalex1978.srp.employee_architecture;

import java.sql.SQLException;

public class ClientModule {

    public static void main(String[] args) {

        Employee e1 = new Employee(1, "Andrew", "surgery", true);
        try {
            ClientModule.hireNewEmployee(e1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ClientModule.printEmployeeReport(e1,FormatType.CSV);
    }

    public static void hireNewEmployee(Employee employee) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.saveEmployee(employee);
    }

    public static void terminateEmployee(Employee employee) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.deleteEmployee(employee);
    }

    /**
     * Метод печатающий отчет на экран.
     *
     * @param employee   объект сотрудника.
     * @param formatType тип формата.
     */
    public static void printEmployeeReport(Employee employee, FormatType formatType) {
        EmployeeReportFormatter formatter = new EmployeeReportFormatter(employee, formatType);
        System.out.println(formatter.getFormattedEmployee());
    }
}
