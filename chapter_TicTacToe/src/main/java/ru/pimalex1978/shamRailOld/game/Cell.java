package ru.pimalex1978.shamRailOld.game;

/**
 * Атомарный элемент игрового поля.
 */
public class Cell {

    /**
     * Состояние ячейки.
     */
    private String state;

    public Cell(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * Проверяет ячейку на предмет равности решетки, что значит, что ячейка не занята.
     * @return true не занята ячейка иначе false.
     */
    public boolean isEmpty() {
        return this.state.equals("#");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return cell.state.equals(state);
    }

    @Override
    public int hashCode() {
        return 31 * state.hashCode();
    }

    @Override
    public String toString() {
        return this.state;
    }
}