package ru.pimalex1978.srp.employee_architecture;

public class ReportFormatter {

    String formattedOutput;

    public ReportFormatter(Object o, FormatType formatType) {
        //в зависимости от того какой FormatType мы приняли
        //такие действия и выполним
        switch (formatType) {
            case XML:
                formattedOutput = convertObjectToXML(o);
                break;
            case CSV:
                formattedOutput = convertObjectToCSV(o);
                break;
        }
    }

    public String getFormattedValue() {
        return formattedOutput;
    }

    private String convertObjectToXML(Object o) {
        return "converted to XML " + o.toString();
    }

    private String convertObjectToCSV(Object o) {
        return "converted to CSV " + o.toString();
    }

}
