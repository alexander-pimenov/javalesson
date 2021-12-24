package ru.pimalex1978;

import lombok.experimental.UtilityClass;


//https://habr.com/ru/post/438870/
//Lombok возвращает величие Java
//@UtilityClass
//Она создает приватный конструктор и там бросает exception (чтоб ручки грязные от рефлексии не лезли сюда).
// И очень красиво в начале класса сообщает нам, что тут утилитные методы.
@UtilityClass
// will be made final
public final class MyUtilityClass {
    // will be made static
    private final String GRUBHUB = "GRUBHUB";
//    private static final String GRUBHUB = "GRUBHUB"; //- так делает @UtilityClass

    // autogenerated by Lombok - так делает @UtilityClass
    // private UtilityClass() {
    //   throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    //}

    // will be made static
    public String append(String input) {
        return input + GRUBHUB;
    }

    //- так делает @UtilityClass
//    public static String append(String input) {
//        return input + GRUBHUB;
//    }
}
