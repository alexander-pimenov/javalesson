package ru.pimalex1978.shamRailOld.logic;

import ru.pimalex1978.shamRailOld.game.Board;

/**
 * Компьютерные противник пользователя.
 */
public class ComputerPlayer implements Player {

    /**
     * Строна.
     */
    private String gameSide;
    /**
     * Игровое поле.
     */
    private Board board;

    /**
     * Анализоватор доски.
     */
    private BoardAnalyser boardAnalyser;

    public ComputerPlayer(Board board, String gameSide) {
        this.board = board;
        this.gameSide = reverseSide(gameSide);
        this.boardAnalyser = new BoardAnalyser(board);
    }

    /**
     * Ход игрока.
     */
    @Override
    public boolean move() {
        boolean result = false;
        if (boardAnalyser.hasFreeCell()) {
            int row;
            int column;
            do {
                row = (int) (Math.random() * board.getSize());
                column = (int) (Math.random() * board.getSize());
                result = board.setCellState(gameSide, row, column);
            } while (!result);
        }
        return result;
    }

    /**
     * Метод получения противоположной стороны.
     * Объект зная свою сторону может узнать о стороне противника.
     * @param side сторона.
     * @return сторона противника.
     */
    public String reverseSide(String side) {
        return side.equals("X") ? "O" : "X";
    }

    /**
     * @return сторона противника.
     */
    @Override
    public String getOpponentSide() {
        return reverseSide(gameSide);
    }
}