package ru.pimalex1978.shamRailOld.logic;

import ru.pimalex1978.shamRailOld.game.Board;
import ru.pimalex1978.shamRailOld.game.Cell;

/**
 * Проверяет есть ли выигрышные стратегии.
 */
public class WinChecker {

    /**
     * Игровое поле.
     */
    private Board board;

    public WinChecker(Board board) {
        this.board = board;
    }

    /**
     * Метод проверки.
     * Проверяет по всем сторонам доску.
     *
     * @return true есть выиграшная стратегия, иначе false.
     */
    public boolean check() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    /**
     * Проверяет по строкам.
     *
     * @return true есть выиграшная стратегия, иначе false.
     */
    private boolean checkRows() {
        boolean result = false;
        for (int row = 0; row < board.getSize(); row++) {
            result |= checkWay(row, 0, 1, 0);
        }
        return result;
    }

    /**
     * Проверяет по столбцам.
     *
     * @return true есть выиграшная стратегия, иначе false.
     */
    private boolean checkColumns() {
        boolean result = false;
        for (int column = 0; column < board.getSize(); column++) {
            result |= checkWay(0, column, 0, 1);
        }
        return result;
    }

    /**
     * Проверяет по диагоналям.
     *
     * @return true есть выиграшная стратегия, иначе false.
     */
    private boolean checkDiagonals() {
        return checkWay(0, 0, 1, 1)
                || checkWay(board.getSize() - 1, board.getSize() - 1, -1, -1);
    }

    /**
     * Проверяет по пути, заданному через смещения.
     *
     * @return true есть выиграшная стратегия, иначе false.
     */
    private boolean checkWay(int row, int column, int deltaX, int deltaY) {
        boolean result = true;
        Cell startCell = board.getCellState(row, column);
        if (!startCell.isEmpty()) {
            for (int i = 0; i < board.getSize(); i++) {
                Cell checkingCell = board.getCellState(row, column);
                if (checkingCell.isEmpty() || !checkingCell.equals(startCell)) {
                    result = false;
                    break;
                }
                row += deltaY;
                column += deltaX;
            }
        } else {
            result = false;
        }
        return result;
    }

}