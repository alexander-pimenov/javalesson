package ru.pimalex1978.exceptions;

/*Всегда нужно знать, какой exception будем расширять Checked or Unchecked exception
 * Unchecked - расширяем RuntimeException
 * Для своих собственных исключений, всегда нужно писать джавадок, в котором описываем
 * для чего нужен этот exception
 * Всегда нужно знать для чего мы создаем свой тип исключений.
 * Нужно знать. что таких исключений нет у Java или наш тип исключений должен более
 * подробно описывать проблему, которая у нас возникает*/

//это исключение говорит о том, что какой то параметр был задан юзером не корректно

//он экстендит RuntimeException
public class InvalidInputParamException extends RuntimeException {

    //мы посмотрели, что у RuntimeException есть 4 паблик конструктора,
    // переопределим все паблик конструкторы RuntimeException


    public InvalidInputParamException() {
    }

    public InvalidInputParamException(String message) {
        super(message);
    }

    //Throwable cause - это по сути другой exception, можно передать сюда тот тип исключения,
    //который мы хотим обернуть в наш собственный тип
    public InvalidInputParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputParamException(Throwable cause) {
        super(cause);
    }
}
