package ru.pimalex1978.johnivo;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 09.09.2019
 */
public class InvalidActionException extends RuntimeException {

    public InvalidActionException(String message) {
        super(message);
    }
}