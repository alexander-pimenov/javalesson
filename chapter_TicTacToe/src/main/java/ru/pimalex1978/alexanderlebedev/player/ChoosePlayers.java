package ru.pimalex1978.alexanderlebedev.player;

import ru.pimalex1978.alexanderlebedev.input.Input;
import ru.pimalex1978.alexanderlebedev.logic.Logic;
import ru.pimalex1978.alexanderlebedev.model.IBoard;

public class ChoosePlayers implements Choose {

    @Override
    public Player[] getPlayers(Input input, IBoard board, Logic logic) {
        Player[] players = new Player[2];
        Player player1 = null;
        Player player2 = null;
        System.out.println("Выберите игру...");
        System.out.println("1 - играет человек-человек");
        System.out.println("2 - играет человек-робот");
        System.out.println("3 - играет робот-человек");
        System.out.println("4 - играет робот-робот");
        boolean escapeLoop = false;
        while (!escapeLoop) {
            int number = input.ask("Введите число от 1 до 4: ");
            switch (number) {
                case 1:
                    player1 = new PlayerHuman(input, board, logic);
                    player2 = new PlayerHuman(input, board, logic);
                    escapeLoop = true;
                    break;
                case 2:
                    player1 = new PlayerHuman(input, board, logic);
                    player2 = new PlayerAI(board, logic);
                    escapeLoop = true;
                    break;
                case 3:
                    player1 = new PlayerAI(board, logic);
                    player2 = new PlayerHuman(input, board, logic);
                    escapeLoop = true;
                    break;
                case 4:
                    player1 = new PlayerAI(board, logic);
                    player2 = new PlayerAI(board, logic);
                    escapeLoop = true;
                    break;
                default:
                    break;
            }
        }
//        можно использовать и if(), но лучше switch-case
//        if (number == 1) {
//            player1 = new PlayerHuman(input, board, logic);
//            player2 = new PlayerHuman(input, board, logic);
//        }
//        if (number == 2) {
//            player1 = new PlayerHuman(input, board, logic);
//            player2 = new PlayerAI(board, logic);
//        }
//        if (number == 3) {
//            player1 = new PlayerAI(board, logic);
//            player2 = new PlayerAI(board, logic);
//        }

        players[0] = player1;
        players[1] = player2;
        return players;
    }
}