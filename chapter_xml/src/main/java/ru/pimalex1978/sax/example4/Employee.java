package ru.pimalex1978.sax.example4;

public class Employee {
    String department;
    Integer number;
    String name;
    Integer age;
    Salary salary;

    //Зарплата определена в качестве внутреннего класса
    static class Salary{
        Double value;
        String currency;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "department='" + department + '\'' +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary.value + " " + salary.currency +
                '}';
    }
}
