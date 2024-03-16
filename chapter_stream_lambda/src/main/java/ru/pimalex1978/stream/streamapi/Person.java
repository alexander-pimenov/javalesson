package ru.pimalex1978.stream.streamapi;


import java.time.LocalDate;

public class Person {
    private String name;
    private LocalDate birthDate;

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