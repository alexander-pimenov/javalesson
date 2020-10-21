package ru.pimalex1978.shamRail.model;

import ru.pimalex1978.shamRail.board.Board;
import ru.pimalex1978.shamRail.board.Cell;

public interface Player {
    Cell.CellValue cell();

    String name();

    void play(Board board);
}
