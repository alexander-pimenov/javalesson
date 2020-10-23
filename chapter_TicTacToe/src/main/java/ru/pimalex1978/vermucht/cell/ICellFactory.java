package ru.pimalex1978.vermucht.cell;

public interface ICellFactory {
    /**
     * Returns free cell.
     *
     * @return Free cell.
     */
    ICell getFree();

    /**
     * Returns cell with X mark
     *
     * @return Cell with X mark.
     */
    ICell getMarkX();

    /**
     * Returns cell with O mark
     *
     * @return Cell with O mark.
     */
    ICell getMarkO();
}