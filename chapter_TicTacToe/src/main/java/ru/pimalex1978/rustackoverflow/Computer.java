package ru.pimalex1978.rustackoverflow;

import java.util.Random;

public class Computer {
    private int column, row;

    public void move() {
        System.out.println("\nComputer's move\n");
        while (true) {
            Random random = new Random();
            row = random.nextInt(2);
            column = random.nextInt(2);

            if (win_check_player()) {
                break;
            }

            if (Game.getField()[row][column] != 1 && Game.getField()[row][column] != -1) {
                Game.setField(row, column, -1);
                break;
            }
        }
    }

    private boolean win_check_player() {
        int[][] field = Game.getField();
        int sum_hor, sum_ver, sum_dia1 = 0, sum_dia2 = 0;

        for (int i = 0; i < 3; i++) {
            sum_hor = 0;
            sum_ver = 0;

            for (int j = 0; j < 3; j++) {
                sum_hor += field[j][i];
                sum_ver += field[i][j];
            }

            //if computer close to win
            if (sum_hor == -2) {
                for (int j = 0; j < 3; j++) {
                    if (Game.getField()[j][i] != -1) {
                        Game.setField(j, i, -1);
                        return true;
                    }
                }
            }

            if (sum_ver == -2) {
                for (int j = 0; j < 3; j++) {
                    if (Game.getField()[i][j] != -1) {
                        Game.setField(i, j, -1);
                        return true;
                    }
                }
            }

            //if player wins
            if (sum_hor == 2) {
                for (int j = 0; j < 3; j++) {
                    if (Game.getField()[j][i] != 1) {
                        Game.setField(j, i, -1);
                        return true;
                    }
                }
            }

            if (sum_ver == 2) {
                for (int j = 0; j < 3; j++) {
                    if (Game.getField()[i][j] != 1) {
                        Game.setField(i, j, -1);
                        return true;
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            sum_dia1 += field[i][i];
            sum_dia2 += field[2 - i][i];
        }

        //if computer close to win
        if (sum_dia1 == -2) {
            for (int i = 0; i < 3; i++) {
                if (Game.getField()[i][i] != -1) {
                    Game.setField(i, i, -1);
                    return true;
                }
            }
        }
        if (sum_dia2 == -2) {
            for (int i = 0; i < 3; i++) {
                if (Game.getField()[2 - i][i] != -1) {
                    Game.setField(2 - i, i, -1);
                    return true;
                }
            }
        }

        //if player close to win
        if (sum_dia1 == 2) {
            for (int i = 0; i < 3; i++) {
                if (Game.getField()[i][i] != 1) {
                    Game.setField(i, i, -1);
                    return true;
                }
            }
        }
        if (sum_dia2 == 2) {
            for (int i = 0; i < 3; i++) {
                if (Game.getField()[2 - i][i] != 1) {
                    Game.setField(2 - i, i, -1);
                    return true;
                }
            }
        }

        return false;
    }
}
