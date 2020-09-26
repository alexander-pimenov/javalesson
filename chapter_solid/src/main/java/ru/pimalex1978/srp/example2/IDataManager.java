package ru.pimalex1978.srp.example2;

/**
 * Интерфейс с методами отвечающими за данные для собщений.
 * Для отправки и получения смс.
 */
public interface IDataManager {
    /**
     * Метод совершающий отправку смс.
     *
     * @param message текст смс.
     */
    void send(String message);

    /**
     * Метод совершающий получение смс.
     *
     * @return количество смс.
     */
    int receive();
}
