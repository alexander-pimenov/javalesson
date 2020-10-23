package ru.pimalex1978.shamRailOld.io;

/**
 * Описывает способ вывода.
 */
public interface Output {

    /**
     * Вывод сообщения.
     * @param message сообщение.
     */
    void writeMessage(String message);

    /**
     * Вывод доски.
     */
    void writeBoard();

}