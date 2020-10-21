package ru.pimalex1978.shamRail.io;

import ru.pimalex1978.shamRail.board.Board;
import ru.pimalex1978.shamRail.board.Cell;
import ru.pimalex1978.shamRail.board.Position;

public class ValidateInput implements Input {
    private final Input input;

    private final Board board;

    private final Output output;

    public ValidateInput(Input input, Board board, Output output) {
        this.input = input;
        this.board = board;
        this.output = output;
    }

    @Override
    public Position readPosition() {
        boolean validated = false;
        Position position = null;
        while (!validated) {
            try {
                position = input.readPosition();
                if (position.getRow() > board.size() || position.getRow() < 1
                        || position.getCol() > board.size() || position.getCol() < 1) {
                    output.write(String.format("Cell and row must be in [1, %d]%n", board.size()));
                    continue;
                }
                position = new Position(
                        position.getRow() - 1,
                        position.getCol() - 1
                );
                if (board.cellValue(position.getRow(), position.getCol()) != Cell.CellValue.EMPTY) {
                    output.write("Position is already busy! Choose another position%n");
                    continue;
                }
                validated = true;
            } catch (Exception e) {
                output.write(String.format("Invalid data format! Try like this %n"));
                output.write(String.format("1 2%n"));
            }
        }
        return position;
    }
}
