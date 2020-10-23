package ru.pimalex1978.johnivo.logic;

/**
 * Описывает логику игры.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 09.09.2019
 */
public interface Logic {

    void newGame();

    int getTableSize();

    int getNumberOfMoves();

    String getMark(Cell cell);

    void makeMove(Cell cell, String mark);

    boolean checkPossibleOfMove();

    String checkWinner();

    //void exitGame();

}