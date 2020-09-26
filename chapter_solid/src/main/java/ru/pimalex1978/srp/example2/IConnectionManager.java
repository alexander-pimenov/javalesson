package ru.pimalex1978.srp.example2;

/**
 * Интерфейс с методами отвечающими за звонки:
 * за установку и обрывание связи.
 */
public interface IConnectionManager {
    /**
     * Метод совершающий звонок.
     * @param phoneNumber номер телефона.
     */
    void dial(String phoneNumber);

    /**
     * Метод завершающий звонок.
     */
    void disconnect();
}
