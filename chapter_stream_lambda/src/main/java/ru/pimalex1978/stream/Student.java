package ru.pimalex1978.stream;

import java.util.Objects;

public class Student {

    private String name;
    private Speciality speciality;
    private Integer year;

    public Student(String name, Speciality speciality, Integer year) {
        this.name = name;
        this.speciality = speciality;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public Integer getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", speciality=" + speciality
                + ", year=" + year
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(name, student.name)
                && speciality == student.speciality
                && Objects.equals(year, student.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, speciality, year);
    }
}

enum Speciality {
    Biology, ComputerScience, Economics, Finance,
    History, Philosophy, Physics, Psychology
}
