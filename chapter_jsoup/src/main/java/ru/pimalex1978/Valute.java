package ru.pimalex1978;

import java.math.BigDecimal;
import java.time.LocalDate;

class Valute {
    private LocalDate date;
    private String id;
    private String numCode;
    private String CharCode;
    private String nominal;
    private String name;
    private BigDecimal value;

    public Valute() {
    }

    public Valute(LocalDate date, String id, String numCode, String charCode, String nominal, String name, BigDecimal value) {
        this.date = date;
        this.id = id;
        this.numCode = numCode;
        CharCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public void setCharCode(String charCode) {
        CharCode = charCode;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Valute{" +
                "date='" + date + '\'' +
                ", id='" + id + '\'' +
                ", numCode='" + numCode + '\'' +
                ", CharCode='" + CharCode + '\'' +
                ", nominal='" + nominal + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
