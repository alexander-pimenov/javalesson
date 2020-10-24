package ru.pimalex1978.alexanderlebedev;

import ru.pimalex1978.alexanderlebedev.input.Input;
import ru.pimalex1978.alexanderlebedev.input.InputConsole;
import ru.pimalex1978.alexanderlebedev.logic.Logic;
import ru.pimalex1978.alexanderlebedev.model.Board;
import ru.pimalex1978.alexanderlebedev.model.IBoard;
import ru.pimalex1978.alexanderlebedev.player.Choose;
import ru.pimalex1978.alexanderlebedev.player.ChoosePlayers;
import ru.pimalex1978.alexanderlebedev.player.Player;

public class Starter {

    private IBoard board;
    private Logic logic;
    private Input input;
    private Choose choose;

    public Starter(IBoard board, Logic logic, Input input) {
        this.board = board;
        this.logic = logic;
        this.input = input;
        this.choose = new ChoosePlayers();
    }

    public Starter() {
    }

    public void game() {
        int winCounter = 0;
        int xWins = 0;
        int oWins = 0;
        int gameCounter = 1;

        System.out.println("Welcome to the game TicTacToe!");
        System.out.println("Выберите размер игрового поля.");
        System.out.println("Например, 3х3 или 5х5 или 7х7 или другое.");
        Input input = new InputConsole();
        Board board;
        int sizeBoard = input.ask("Для этого введите целое число от 3 до 9: ");
        if (sizeBoard > 2 && sizeBoard <= 9) {
            System.out.println("Выбрано игровое поле: " + sizeBoard + "x" + sizeBoard);
            board = new Board(sizeBoard);
        } else {
            System.out.println("Выбрано стандартное игровое поле: 3х3");
            board = new Board();
        }
        board.initTable();
        board.printTable();

        Logic logic = new Logic(board);

        System.out.println("До скольки побед будете играть? ");
        winCounter = input.ask("Введите целое число. Например, 3 или другое: ");
        if (winCounter == 0) {
            winCounter = 1;
            System.out.println("Играем до " + winCounter + " победы.");
        } else {
            System.out.println("Играем до " + winCounter + " побед(ы).");
        }

        System.out.println("Кто первый ходит, тот играет крестиками.");

        Choose choose = new ChoosePlayers();
        Player[] players = choose.getPlayers(input, board, logic);

//        board.printTable();

        boolean exit = false;
        while (!exit) {
            System.out.println(String.format("Партия № %s, счет (X %s:%s O). Игра до %s побед.",
                    gameCounter, xWins, oWins, winCounter));
            board.initTable();
            board.printTable();
            while (true) {
                players[0].go(board.getCell().getSIGN_X());
                board.printTable();
                if (logic.isWinnerX()) {
                    System.out.println("Победили крестики!");
                    xWins++;
                    gameCounter++;
                    if (xWins == winCounter) {
                        System.out.println(String.format("Игра закончена, победили крестики, счет (X %s:%s O)",
                                xWins, oWins));
                        exit = true;
                    }
                    //                System.out.println("YOU WIN");
                    break;
                }
                if (logic.isTableFull()) {
                    System.out.println("Ничья! Начните новую партию.%n");
                    gameCounter++;
                    break;
                }
                players[1].go(board.getCell().getSIGN_O());
                board.printTable();
                if (logic.isWinnerO()) {
                    System.out.println("Победили нолики!");
                    oWins++;
                    gameCounter++;
                    if (oWins == winCounter) {
                        System.out.println(String.format("Игра закончена, победили нолики, счет (X %s:%s O)",
                                xWins, oWins));
                        exit = true;
                    }
                    //                System.out.println("YOU WIN");
                    break;
                }
                if (logic.isTableFull()) {
                    System.out.println("Ничья! Начните новую партию.%n");
                    gameCounter++;
                    break;
                }
            }
        }
//        System.out.println("GAME OVER");
    }

    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.game();

//        IBoard board = new Board(5);
//        IBoard board = new Board();
//        Logic logic = new Logic(board);
//        Input input = new InputConsole();
//        Starter starter = new Starter(board, logic, input);
//        starter.game();
    }
}