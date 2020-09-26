package ru.pimalex1978.srp.employee_architecture;

public class EmployeeReportFormatter extends ReportFormatter {

    Employee anEmployee;
    FormatType formatType;

    public EmployeeReportFormatter(Object o, FormatType formatType) {
        super(o, formatType);
    }

    public String getFormattedEmployee() {
        return formattedOutput;
    }

}
