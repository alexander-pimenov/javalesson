package ru.pimalex1978.class_date;

import java.time.LocalDate;

public class Person {
    private final String name;
    private final LocalDate birthDate;

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}