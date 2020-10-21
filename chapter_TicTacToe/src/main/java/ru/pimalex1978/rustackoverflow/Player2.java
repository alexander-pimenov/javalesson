package ru.pimalex1978.rustackoverflow;

import java.util.Scanner;

public class Player2 {
    private int column, row;

    public void move() {

        System.out.println("\nPlayer2's move\n");

        while(true) {
            try {
                System.out.println("Enter cell (row and column) with space. Example: '1 3'");

                Scanner keyboard = new Scanner(System.in);
                column = keyboard.nextInt();
                row = keyboard.nextInt();

                if(Game.getField()[row-1][column-1] == 0) {
                    Game.setField(row-1, column-1, -1);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error in input\n");
            }
        }
    }
}
