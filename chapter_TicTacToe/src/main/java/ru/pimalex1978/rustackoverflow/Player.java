package ru.pimalex1978.rustackoverflow;

import java.util.Scanner;

public class Player {
    private int column, row, answer;

    public void answer(int answer) {
        this.answer = answer;
    }

    public void move() {

        if (answer == 1) {
            System.out.println("\nPlayer's move\n");
        } else {
            System.out.println("\nPlayer1's move\n");
        }

        while(true) {
            try {
                System.out.println("Enter cell (row and column) with space. Example: '1 3'");

                Scanner keyboard = new Scanner(System.in);
                column = keyboard.nextInt();
                row = keyboard.nextInt();

                if(Game.getField()[row-1][column-1] == 0) {
                    Game.setField(row-1, column-1, 1);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error in input\n");
            }
        }
    }
}
