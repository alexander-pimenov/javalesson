package ru.pimalex1978.a11exe;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 09.07.2019
 */
public class WrongMoveException extends RuntimeException {

    public WrongMoveException(String message) {
        super(message);
    }
}