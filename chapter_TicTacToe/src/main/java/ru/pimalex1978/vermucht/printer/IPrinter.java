package ru.pimalex1978.vermucht.printer;

import ru.pimalex1978.vermucht.cell.ICell;

public interface IPrinter {
    /**
     * Returns current table status as string.
     *
     * @param table TicTacToe table.
     * @return Table status as string.
     */
    String print(ICell[][] table);
}