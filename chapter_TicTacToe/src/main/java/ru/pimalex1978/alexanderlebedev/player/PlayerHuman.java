package ru.pimalex1978.alexanderlebedev.player;

import ru.pimalex1978.alexanderlebedev.input.Input;
import ru.pimalex1978.alexanderlebedev.logic.Logic;
import ru.pimalex1978.alexanderlebedev.model.IBoard;

public class PlayerHuman implements Player {

    private Input input;
    private IBoard board;
    private Logic logic;


    public PlayerHuman(Input input, IBoard board, Logic logic) {
        this.input = input;
        this.board = board;
        this.logic = logic;
    }

    /**
     * Метод, реализующий ход пользователя.
     *
     * @param symbol символ, которым играет пользователь.
     *               Или X или O.
     */
    @Override
    public void go(char symbol) {
        int x, y;
        do {
            x = input.ask("Введите координату X: ") - 1;
            y = input.ask("Введите координату Y: ") - 1;
        } while (!logic.isCellValidForHuman(x, y));
        System.out.println(Character.toUpperCase(symbol) + " сделал ход в клетку: " + (x + 1) + "-" + (y + 1));
        board.getBoard()[x][y] = symbol;
    }
}