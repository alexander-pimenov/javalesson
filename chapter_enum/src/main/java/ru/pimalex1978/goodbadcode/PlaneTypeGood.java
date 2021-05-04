package ru.pimalex1978.goodbadcode;

public enum PlaneTypeGood {
//    AIRBUS_A320, AIRBUS_A380, AIRBUS_A330, BOEING_737_800, BOEING_777
}

/*
 * Вместо одного неправильного перечисления создаем перечисление для
 * производителя и отдельный класс для указания модели.
 * */
enum PlaneProducer {
    AIRBUS, BOEING
}

class Plane {
    private PlaneProducer producer;
    private String model;
}
