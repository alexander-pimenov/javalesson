package ru.pimalex1978.a11exe;

import ru.pimalex1978.a11exe.game.ConsoleGame;
import ru.pimalex1978.a11exe.game.Game;
import ru.pimalex1978.a11exe.logic.TicTacToe;
import ru.pimalex1978.a11exe.logic.TicTacToe3T;
import ru.pimalex1978.a11exe.player.ComputerRandom;
import ru.pimalex1978.a11exe.player.Human;
import ru.pimalex1978.a11exe.player.Player;

/**
 * Main start game
 * init game params
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.07.2019
 */
public class Main {

    public static void main(String[] args) {

        TicTacToe ticTacToe = new TicTacToe3T(4);
        Player computer = new ComputerRandom(ticTacToe, "Random computer");
        //Player computer2 = new ComputerRandom(ticTacToe, "Random computer2");
        Player human = new Human(ticTacToe, "Human", System.in, System.out);
        Game game = new ConsoleGame(ticTacToe, 2, human, computer, System.out);
        game.newGame();
        while (!game.exit()) {
            game.next();
        }
    }
}