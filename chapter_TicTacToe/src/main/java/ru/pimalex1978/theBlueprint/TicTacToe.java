package ru.pimalex1978.theBlueprint;

import java.util.Random;
import java.util.Scanner;

/**
 * https://www.youtube.com/watch?v=3KM6Q0TFC28
 * https://github.com/Taylor-McNeil/YouTubeTutorials-
 */
public class TicTacToe {

    private static int playerScore = 0;
    private static int computerScore = 0;
    private static Scanner input = new Scanner(System.in);
    /*
     *        Сетка:
     *      _ | _ | _
     *      _ | _ | _
     *        |   |
     *
     * Номера позиций в сетке:
     *      1 | 2 | 3
     *      4 | 5 | 6
     *      7 | 8 | 9
     *
     * Индексы в двумерном массиве:
     * [0][0] , [0][2] , [0][4]
     * [1][0] , [1][2] , [1][4]
     * [2][0] , [2][2] , [2][4]
     *
     * Player = 1
     * Computer = 2
     *
     */

    public static void main(String[] args) {

        char[][] gameBoard = {
                {'_', '|', '_', '|', '_'},
                {'_', '|', '_', '|', '_'},
                {' ', '|', ' ', '|', ' '}
        };
        System.out.println("Welcome to the game TicTacToe!");
        System.out.println("Вы играете против Computer.");

        printBoard(gameBoard);
        boolean gameOver = false;
        boolean playAgain = true;

        while (playAgain) {
            while (!gameOver) {
                playerMove(gameBoard);
                gameOver = isGameOver(gameBoard);
                if (gameOver) {
                    break;
                }
                computerMove(gameBoard);
                gameOver = isGameOver(gameBoard);
                if (gameOver) {
                    break;
                }
            }
            System.out.println("Player Score: " + playerScore);
            System.out.println("Computer Score: " + computerScore);
            System.out.println("Would you like to play again? Y/N");
            input.nextLine(); //чтобы ждал ответ
            String result = input.nextLine();
            switch (result) {
                case "Y":
                case "y":
                    playAgain = true;
                    System.out.println("Dope! Let's play again");
                    //we nee to reset the game board
                    resetBoard(gameBoard);
                    gameOver = false;
                    printBoard(gameBoard);
                    break;
                case "N":
                case "n":
                    playAgain = false;
                    System.out.println("Thanks for playing");
                    break;
                default:
                    break;
            }
        }
    }

    public static void printBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void updateBoard(int position, int player, char[][] gameBoard) {
        char character;
        if (player == 1) {
            character = 'X';
        } else {
            character = 'O';
        }
        switch (position) {
            case 1:
                gameBoard[0][0] = character;
                printBoard(gameBoard);
                break;
            case 2:
                gameBoard[0][2] = character;
                printBoard(gameBoard);
                break;
            case 3:
                gameBoard[0][4] = character;
                printBoard(gameBoard);
                break;
            case 4:
                gameBoard[1][0] = character;
                printBoard(gameBoard);
                break;
            case 5:
                gameBoard[1][2] = character;
                printBoard(gameBoard);
                break;
            case 6:
                gameBoard[1][4] = character;
                printBoard(gameBoard);
                break;
            case 7:
                gameBoard[2][0] = character;
                printBoard(gameBoard);
                break;
            case 8:
                gameBoard[2][2] = character;
                printBoard(gameBoard);
                break;
            case 9:
                gameBoard[2][4] = character;
                printBoard(gameBoard);
                break;
            default:
                break;
        }
    }

    public static void playerMove(char[][] gameBoard) {
        int move;
        System.out.println("Please make a move. (1-9)");
        move = checkDigitInputAndReturnInt();

        boolean result = isValidMove(move, gameBoard);
        while (!result) {
            System.out.println("Sorry! Invalid move. Try again.");
            move = input.nextInt();
            result = isValidMove(move, gameBoard);
        }

        System.out.println("Player moved at position " + move);
        updateBoard(move, 1, gameBoard);

    }

    public static boolean isValidMove(int move, char[][] gameBoard) {
        switch (move) {
            case 1:
                return gameBoard[0][0] == '_';
            case 2:
                return gameBoard[0][2] == '_';
            case 3:
                return gameBoard[0][4] == '_';
            case 4:
                return gameBoard[1][0] == '_';
            case 5:
                return gameBoard[1][2] == '_';
            case 6:
                return gameBoard[1][4] == '_';
            case 7:
                return gameBoard[2][0] == ' ';
            case 8:
                return gameBoard[2][2] == ' ';
            case 9:
                return gameBoard[2][4] == ' ';
            default:
                return false;
        }
    }

    public static void computerMove(char[][] gameBoard) {
        Random rand = new Random();
        int move = rand.nextInt(9) + 1;

        boolean result = isValidMove(move, gameBoard);

        while (!result) {
            move = rand.nextInt(9) + 1;
            result = isValidMove(move, gameBoard);
        }

        System.out.println("Computer moved at position " + move);
        updateBoard(move, 2, gameBoard);
    }

    public static boolean isGameOver(char[][] gameBoard) {
        //Horizontal Wins
        if (gameBoard[0][0] == 'X'
                && gameBoard[0][2] == 'X'
                && gameBoard[0][4] == 'X') {
            System.out.println("Player won!");
            playerScore++;
            return true;
        }
        if (gameBoard[0][0] == 'O'
                && gameBoard[0][2] == 'O'
                && gameBoard[0][4] == 'O') {
            System.out.println("Computer won!");
            computerScore++;
            return true;
        }
        if (gameBoard[1][0] == 'X'
                && gameBoard[1][2] == 'X'
                && gameBoard[1][4] == 'X') {
            System.out.println("Player won!");
            playerScore++;
            return true;
        }
        if (gameBoard[1][0] == 'O'
                && gameBoard[1][2] == 'O'
                && gameBoard[1][4] == 'O') {
            System.out.println("Computer won!");
            computerScore++;
            return true;
        }
        if (gameBoard[2][0] == 'X'
                && gameBoard[2][2] == 'X'
                && gameBoard[2][4] == 'X') {
            System.out.println("Player won!");
            playerScore++;
            return true;
        }
        if (gameBoard[2][0] == 'O'
                && gameBoard[2][2] == 'O'
                && gameBoard[2][4] == 'O') {
            System.out.println("Computer won!");
            computerScore++;
            return true;
        }

        //Vertical Wins
        if (gameBoard[0][0] == 'X'
                && gameBoard[1][0] == 'X'
                && gameBoard[2][0] == 'X') {
            System.out.println("Player won!");
            playerScore++;
            return true;
        }
        if (gameBoard[0][0] == 'O'
                && gameBoard[1][0] == 'O'
                && gameBoard[2][0] == 'O') {
            System.out.println("Computer won!");
            computerScore++;
            return true;
        }
        if (gameBoard[0][2] == 'X'
                && gameBoard[1][2] == 'X'
                && gameBoard[2][2] == 'X') {
            System.out.println("Player won!");
            playerScore++;
            return true;
        }
        if (gameBoard[0][2] == 'O'
                && gameBoard[1][2] == 'O'
                && gameBoard[2][2] == 'O') {
            System.out.println("Computer won!");
            computerScore++;
            return true;
        }
        if (gameBoard[0][4] == 'X'
                && gameBoard[1][4] == 'X'
                && gameBoard[2][4] == 'X') {
            System.out.println("Player won!");
            playerScore++;
            return true;
        }
        if (gameBoard[0][4] == 'O'
                && gameBoard[1][4] == 'O'
                && gameBoard[2][4] == 'O') {
            System.out.println("Computer won!");
            computerScore++;
            return true;
        }

        //Diagonal Wins
        if (gameBoard[0][0] == 'X'
                && gameBoard[1][2] == 'X'
                && gameBoard[2][4] == 'X') {
            System.out.println("Player won!");
            playerScore++;
            return true;
        }
        if (gameBoard[0][0] == 'O'
                && gameBoard[1][2] == 'O'
                && gameBoard[2][4] == 'O') {
            System.out.println("Computer won!");
            computerScore++;
            return true;
        }
        if (gameBoard[0][4] == 'X'
                && gameBoard[1][2] == 'X'
                && gameBoard[2][0] == 'X') {
            System.out.println("Player won!");
            playerScore++;
            return true;
        }
        if (gameBoard[0][4] == 'O'
                && gameBoard[1][2] == 'O'
                && gameBoard[2][0] == 'O') {
            System.out.println("Computer won!");
            computerScore++;
            return true;
        }

        //Board is full
        if (gameBoard[0][0] != '_'
                && gameBoard[0][2] != '_'
                && gameBoard[0][4] != '_'
                && gameBoard[1][0] != '_'
                && gameBoard[1][2] != '_'
                && gameBoard[1][4] != '_'
                && gameBoard[2][0] != ' '
                && gameBoard[2][2] != ' '
                && gameBoard[2][4] != ' ') {
            System.out.println("Board is full!");
            return true;
        }
        return false;
    }

    public static void resetBoard(char[][] gameBoard) {
        gameBoard[0][0] = '_';
        gameBoard[0][2] = '_';
        gameBoard[0][4] = '_';
        gameBoard[1][0] = '_';
        gameBoard[1][2] = '_';
        gameBoard[1][4] = '_';
        gameBoard[2][0] = ' ';
        gameBoard[2][2] = ' ';
        gameBoard[2][4] = ' ';
    }

    public static int checkDigitInputAndReturnInt() {
        int move;
        while (!input.hasNextInt()) {
            System.out.println("That not a number! Try again.");
            input.next(); //this is important! - продвигаем Scanner до тех пор, пока не будет hasNextInt()
        }
        move = input.nextInt();
        return move;
    }
}
