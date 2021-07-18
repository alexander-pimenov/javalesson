package ru.pimalex1978.jackson.example5;

import java.time.LocalDate;

public class DayPOJO {
    private LocalDate date;
    private String name;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
