package ru.pimalex1978;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
class Valute {
    private LocalDate date;
    private String id;
    private String numCode;
    private String CharCode;
    private String nominal;
    private String name;
    private BigDecimal value;

    public Valute(LocalDate date, String id, String numCode, String charCode, String nominal, String name, BigDecimal value) {
        this.date = date;
        this.id = id;
        this.numCode = numCode;
        CharCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }
}
