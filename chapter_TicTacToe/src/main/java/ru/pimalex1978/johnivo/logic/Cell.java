package ru.pimalex1978.johnivo.logic;

/**
 * Описывает клетку поля
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 09.09.2019
 */
public class Cell {

    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}