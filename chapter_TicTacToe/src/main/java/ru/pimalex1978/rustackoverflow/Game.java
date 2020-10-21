package ru.pimalex1978.rustackoverflow;

import java.util.Scanner;

/**
 * Запускаешь Game.java и играешь. В программу вставил нубский
 * "искусственный интеллект", который в свой первый ход ходит рандомно,
 * а в последующие начинает "думать" как сходить. Игра заканчивается
 * либо победой, либо заполнением всех клеток
 * (не стал писать код для "ничьи" - лень).
 */

public class Game {
    //FIELD
    private static int[][] field = new int[3][3];
    private static boolean Computer_win = false;
    private static boolean Player_win = false;

    public static int[][] getField() {
        return field;
    }

    public static void setField(int row, int column, int n) {
        field[row][column] = n;
    }

    public void display() {
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 3; j++) {
                if (field[j][i] == 1) {
                    System.out.print("O ");
                } else if (field[j][i] == 0) {
                    System.out.print("_ ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        Player player = new Player();
        Computer computer = new Computer();
        Player2 player2 = new Player2();
        System.out.println("Выберите кем играть:");
        System.out.println("1 - Player VS Computer");
        System.out.println("2 - Player VS Player\n");
        Scanner key = new Scanner(System.in);
        int answer = key.nextInt();

        player.answer(answer);


        if (answer == 1) {
            System.out.println("\nPlayer plays as a Zero ( O ); Computer plays as a Cross ( X )\n");
        } else {
            System.out.println("\nPlayer1 plays as a Zero ( O ); Player2 plays as a Cross ( X )\n");
        }

        while (true) {
            game.display();
            player.move();
            if (win_check()) {
                break;
            }
            ;
            if (answer == 1) {
                computer.move();
            } else {
                game.display();
                player2.move();
            }
            if (win_check()) {
                break;
            }
        }

        game.display();
        if (Player_win) {
            if (answer == 1) {
                System.out.println("\nPlayer won the game");
            } else {
                System.out.println("\nPlayer1 won the game");
            }
        } else if (Computer_win) {
            if (answer == 1) {
                System.out.println("\nComputer won the game");
            } else {
                System.out.println("\nPlayer2 won the game");
            }
        } else {
            System.out.println("\nDraw. No one won the game");
        }
    }

    public static boolean win_check() {
        int sum_hor, sum_ver, sum_dia1 = 0, sum_dia2 = 0, sum_all;
        for (int i = 0; i < 3; i++) {
            sum_hor = 0;
            sum_ver = 0;

            for (int j = 0; j < 3; j++) {
                sum_hor += field[j][i];
                sum_ver += field[i][j];
            }
            if (sum_hor == 3 || sum_ver == 3) {
                Player_win = true;
                return true;
            }
            if (sum_hor == -3 || sum_ver == -3) {
                Computer_win = true;
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            sum_dia1 += field[i][i];
            sum_dia2 += field[2 - i][i];
        }
        if (sum_dia1 == 3 || sum_dia2 == 3) {
            Player_win = true;
            return true;
        }
        if (sum_dia1 == -3 || sum_dia2 == -3) {
            Computer_win = true;
            return true;
        }

        sum_all = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] != 0) {
                    sum_all += 1;
                }
            }
        }
        if (sum_all == 9) {
            return true;
        }
        return false;
    }
}
