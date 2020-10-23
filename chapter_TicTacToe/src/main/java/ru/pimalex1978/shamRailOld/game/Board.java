package ru.pimalex1978.shamRailOld.game;

/**
 * Игровое поле.
 */

public class Board {

    /**
     * Матричное представление игрового поля.
     */
    private Cell[][] board;

    /**
     * Размер игровой доски по умолчанию.
     */
    private int size = 3;

    public Board() {
        this.board = new Cell[size][size];
        this.fillEmpty();
    }

    /**
     * Метод заполняет доску решетками.
     * Решетка на месте ячейки поля значит, что поле не занято ни одной из игровых сторон.
     */
    private void fillEmpty() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                this.board[row][column] = new Cell("#");
            }
        }
    }

    /**
     * Получает ячейку поля по ее координатам.
     * @param row строка.
     * @param column столбец.
     * @return ячейка.
     */
    public Cell getCellState(int row, int column) {
        return this.board[row][column];
    }

    /**
     * Устанавливает значение ячейки поля.
     * @param state значение.
     * @param row строка.
     * @param column столбец.
     * @return true если удалось сделать иначе false.
     */
    public boolean setCellState(String state, int row, int column) {
        boolean result = false;
        if (board[row][column].isEmpty()) {
            this.board[row][column].setState(state);
            result = true;
        }
        return result;
    }

    /**
     * @return размер поля.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Расширяет размер поля, если новый размер больше 3.
     * @param newSize новый размер.
     */
    public void unsafeResize(int newSize) {
        if (newSize > 3) {
            this.size = newSize;
            this.board = new Cell[size][size];
            this.fillEmpty();
        }
    }

    /**
     * Очищает игровое поле, устанавливая в каждую ячейку решетку.
     */
    public void clear() {
        this.fillEmpty();
    }
}