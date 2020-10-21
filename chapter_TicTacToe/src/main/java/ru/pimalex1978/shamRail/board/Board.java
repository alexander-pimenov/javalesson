package ru.pimalex1978.shamRail.board;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private static final int DEFAULT_SIZE = 3;

    private int size;

    private Cell[][] board;

    public Board() {
        this(DEFAULT_SIZE);
    }

    public Board(int size) {
        this.size = size;
        this.initBoard();
    }

    private void initBoard() {
        board = new Cell[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new Cell(row, col);
            }
        }
    }

    public boolean mark(int row, int col, Cell.CellValue value) {
        if (!board[row][col].isEmpty()) {
            return false;
        }
        board[row][col].setValue(value);
        return true;
    }

    public Cell.CellValue cellValue(int row, int col) {
        return board[row][col].getValue();
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (Cell[] row : board) {
            sj.add(makeSeparator());
            sj.add(makeRow(row));
        }
        sj.add(makeSeparator());
        return sj.toString();
    }

    private String makeRow(Cell[] row) {
        return Arrays.stream(row)
                .map(c -> c.getValue().text())
                .collect(Collectors.joining("|", "|", "|"));
    }

    private String makeSeparator() {
        return IntStream.range(0, board.length)
                .mapToObj(String::valueOf)
                .map(f -> "-")
                .collect(Collectors.joining("+", "+", "+"));
    }
}
