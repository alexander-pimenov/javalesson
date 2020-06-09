package ru.pimalex1978.dom.example1;

/**
 * Вся информация сохранена в элементах employee.
 * Для того, чтобы где-то хранить её у нас в программе.
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
