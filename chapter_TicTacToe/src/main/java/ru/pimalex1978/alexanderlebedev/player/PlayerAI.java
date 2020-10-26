package ru.pimalex1978.alexanderlebedev.player;

import ru.pimalex1978.alexanderlebedev.logic.Logic;
import ru.pimalex1978.alexanderlebedev.model.IBoard;

import java.util.Random;

public class PlayerAI implements Player {
    private Random random = new Random();

    private IBoard board;
    private Logic logic;

    public PlayerAI(IBoard board, Logic logic) {
        this.board = board;
        this.logic = logic;
    }

    /**
     * Метод, реализующий ход компьютера.
     *
     * @param symbol символ, которым играет пользователь.
     *               Или X или O.
     */
    @Override
    public void go(char symbol) {
        int x, y;
        do {
            x = random.nextInt(board.size());
            y = random.nextInt(board.size());
        } while (!logic.isCellValidForRobot(x, y));
        System.out.println(Character.toUpperCase(symbol) + " сделал ход в клетку: " + (x + 1) + "-" + (y + 1));
        board.getBoard()[x][y] = symbol;
    }
}