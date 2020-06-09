package ru.pimalex1978.sax.example1;

/**
 * Класс Employee, куда сохраним всю информацию из xml_file1
 */

public class Employee {
    private String name, job;

    public Employee(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
