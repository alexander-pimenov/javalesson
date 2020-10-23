package ru.pimalex1978.shamRailOld;

import ru.pimalex1978.shamRailOld.game.Board;
import ru.pimalex1978.shamRailOld.game.Tictactoe;
import ru.pimalex1978.shamRailOld.io.Input;
import ru.pimalex1978.shamRailOld.io.Output;
import ru.pimalex1978.shamRailOld.io.console.ConsoleInput;
import ru.pimalex1978.shamRailOld.io.console.ConsolePrinter;
import ru.pimalex1978.shamRailOld.logic.ComputerPlayer;
import ru.pimalex1978.shamRailOld.logic.Player;

/**
 * Точка запуска приложения.
 */

public class TictactoeUI {

    /**
     * Метод, запускающий игру.
     * Клиент должен знать.
     *
     * @param board  где, играет.
     * @param input  куда вводит данные.
     * @param output откуда их получает.
     * @param player с кем играет.
     */
    public void startGame(Board board, Input input, Output output, Player player) {
        new Tictactoe(board, input, output, player).begin();
    }

    public static void main(String[] args) {
        final Board board = new Board();
        new TictactoeUI().startGame(board, new ConsoleInput(), new ConsolePrinter(board), new ComputerPlayer(board, "X"));
    }
}