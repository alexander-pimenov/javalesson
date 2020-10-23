package ru.pimalex1978.johnivo;

import ru.pimalex1978.johnivo.game.ConsoleGame;
import ru.pimalex1978.johnivo.game.Game;
import ru.pimalex1978.johnivo.logic.Logic;
import ru.pimalex1978.johnivo.logic.Logic3T;
import ru.pimalex1978.johnivo.player.BotSimple;
import ru.pimalex1978.johnivo.player.Player;
import ru.pimalex1978.johnivo.player.User;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 11.09.2019
 */
public class TicTacToe {

    public static void main(String[] args) {
        Logic logic = new Logic3T(3);

        Player first = new User(logic, "user", System.in, System.out::println);
//        Player firstBot = new BotSimple(logic, "bot1");
        Player second = new BotSimple(logic, "bot");

        Game cg = new ConsoleGame(logic, first, second, 3, System.out::println);
//        Game cg = new ConsoleGame(logic, firstBot, second, 3, System.out::println);

        cg.newGame();
        while (!cg.exitGame()) {
            cg.nextMove();
        }
    }

}