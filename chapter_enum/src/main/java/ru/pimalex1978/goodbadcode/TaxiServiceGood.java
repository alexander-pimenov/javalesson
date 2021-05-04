package ru.pimalex1978.goodbadcode;

public enum TaxiServiceGood {
    ALMAZ(7788), STOLOTSA(135), VIP(107), CRYSTAL(7778),
    TAXI_CARGO(163);

    private int phone;

    TaxiServiceGood(int phone) {
        this.phone = phone;
    }
}
