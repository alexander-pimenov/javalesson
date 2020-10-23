package ru.pimalex1978.slevkelebr;

/**
 * Interface Input - интрейфейс ввода данных из консоли.
 *
 * @author Frolov Sergey (Slevkelebr@yandex.ru)
 * @version 0.1
 * @since 22.05.2020
 */
public interface Input {
    String askStr(String question);

    int askInt(String question);

}
