package ru.pimalex1978.johnivo.game;

/**
 * Описывает игру.
 * Что за чем идет.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 10.09.2019
 */
public interface Game {

    void newGame();

    void showTable();

    void nextMove();

    boolean exitGame();

}