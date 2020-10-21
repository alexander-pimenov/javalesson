package ru.pimalex1978.shamRail.board;

public class Cell {

    public enum CellValue {
        X("X"), O("O"), EMPTY(" ");

        private String name;

        CellValue(String name) {
            this.name = name;
        }

        String text() {
            return name;
        }
    }

    private final int row;
    private final int col;
    private CellValue value = CellValue.EMPTY;

    public Cell(int x, int col) {
        this.row = x;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellValue getValue() {
        return value;
    }

    public void setValue(CellValue value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == CellValue.EMPTY;
    }
}
