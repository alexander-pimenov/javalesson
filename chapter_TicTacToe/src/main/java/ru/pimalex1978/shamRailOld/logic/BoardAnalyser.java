package ru.pimalex1978.shamRailOld.logic;

import ru.pimalex1978.shamRailOld.game.Board;

/**
 * Анализотор доски.
 */
public class BoardAnalyser {

    /**
     * Игровая доска.
     */
    private Board board;

    /**
     * Объект, ищущий выигрышные стратегии.
     */
    private WinChecker winChecker;

    public BoardAnalyser(Board board) {
        this.board = board;
        this.winChecker = new WinChecker(board);
    }

    /**
     * Определяет осталось ли на доске место для хода.
     * @return true есть, иначе false.
     */
    public boolean hasFreeCell() {
        boolean result = false;
        for (int row = 0; row < board.getSize(); row++) {
            for (int column = 0; column < board.getSize(); column++) {
                if (hasFreeCell(row, column)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Определяет пуста ячейка.
     * @param row строка.
     * @param column столбец.
     * @return состояние.
     */
    public boolean hasFreeCell(int row, int column) {
        return board.getCellState(row, column).isEmpty();
    }

    /**
     * Проверяет есть выигрышная стратегия на поле.
     * @return если есть, то true, иначе false.
     */
    public boolean checkWin() {
        return winChecker.check();
    }
}