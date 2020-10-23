package ru.pimalex1978.a11exe.logic;

public interface TicTacToe {

    void makeMove(Point point, Mark mark);

    Mark getMark(Point point);

    boolean isMovePossible();

    Mark checkWinner();

    int getTableSize();

    void newGame();
}