package ru.pimalex1978.shamRailOld.game;

import ru.pimalex1978.shamRailOld.io.Input;
import ru.pimalex1978.shamRailOld.io.Output;
import ru.pimalex1978.shamRailOld.logic.Player;

/**
 * Игра Крестики - нолики.
 */
public class Tictactoe {

    /**
     * Статискика игры.
     */
    private GameStatistic gameStatistic;

    /**
     * Способ ввода.
     */
    private Input userInput;

    /**
     * Способ вывода.
     */
    private Output output;

    /**
     * Противник пользователя.
     */
    private Player computer;

    /**
     * Игровая доска.
     */
    private Board board;

    public Tictactoe(Board board, Input userInput, Output output, Player computer) {
        this.board = board;
        this.userInput = userInput;
        this.output = output;
        this.computer = computer;
    }

    /**
     * Запускает игру.
     * Кол-во подыгр и игровая сторона задается пользователем.
     */
    public void begin() {
        int boardSize = Integer.valueOf(userInput.ask("Enter board size(default 3): "));
        board.unsafeResize(boardSize);
        gameStatistic = new GameStatistic(Integer.valueOf(userInput.ask("Enter common game count: ")));
        for (int gameNumber = 1; gameNumber <= gameStatistic.getGamesCount(); gameNumber++) {
            output.writeMessage(String.format("Game: %s", gameNumber));
            Game game = new Game(board, userInput, output, computer);
            game.start();
            gameStatistic.distributeScore(game);
            board.clear();
        }
        printWinner();
    }

    /**
     * Печатает победителя.
     */
    private void printWinner() {
        int comparingRst = gameStatistic.compareScore();
        String winner = comparingRst == -1 ? "Computer wins!"
                : comparingRst == 1 ? "User wins!" : "No one wins!";
        output.writeMessage(winner);
    }
}