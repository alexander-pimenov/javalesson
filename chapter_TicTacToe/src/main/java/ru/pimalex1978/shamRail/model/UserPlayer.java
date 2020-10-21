package ru.pimalex1978.shamRail.model;

import ru.pimalex1978.shamRail.board.Board;
import ru.pimalex1978.shamRail.board.Cell;
import ru.pimalex1978.shamRail.board.Position;
import ru.pimalex1978.shamRail.io.Input;

public class UserPlayer implements Player {
    private Cell.CellValue cell;

    private Board board;

    private Input input;

    private String name;

    public UserPlayer(Cell.CellValue cell, Board board, Input input, String name) {
        this.cell = cell;
        this.board = board;
        this.input = input;
        this.name = name;
    }

    @Override
    public Cell.CellValue cell() {
        return cell;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void play(Board board) {
        Position position = input.readPosition();
        board.mark(position.getRow(), position.getCol(), cell);
    }
}
