package ru.pimalex1978.shamRail;

import ru.pimalex1978.shamRail.board.Board;
import ru.pimalex1978.shamRail.board.Cell;
import ru.pimalex1978.shamRail.io.*;
import ru.pimalex1978.shamRail.logic.Logic;
import ru.pimalex1978.shamRail.model.Player;
import ru.pimalex1978.shamRail.model.UserPlayer;

public class Game {
    private Player player1;

    private Player player2;

    private Board board;

    private Output output;

    private Logic logic;

    public Game(Player player1,
                Player player2,
                Board board,
                Output output) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        this.output = output;
        this.logic = new Logic(board);
    }

    public void start() {
        Player currentPlayer = player1;
        while (logic.hasGap()) {
            output.write(String.format("%s%n", board));
            output.write(String.format("%s choose cell%n", currentPlayer.name()));
            currentPlayer.play(board);
            if (logic.isWin()) {
                output.write(String.format("%s win!%n", currentPlayer.name()));
                break;
            }
            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(new ConsoleInput(), board, output);
        Player playerX = new UserPlayer(Cell.CellValue.X, board, input, "Player X");
        Player playerO = new UserPlayer(Cell.CellValue.O, board, input, "Player O");
        Game game = new Game(
                playerX, playerO,
                board, output
        );
        game.start();
    }
}
