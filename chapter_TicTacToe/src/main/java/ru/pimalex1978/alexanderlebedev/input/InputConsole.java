package ru.pimalex1978.alexanderlebedev.input;

import java.util.Scanner;

public class InputConsole implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public int ask(String name) {
        System.out.print(name);
        return inInt();
    }

    private int inInt() {
        //Крутимся в бесконечном цикле пока
        //не будет введено целое число.
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Not an integer! Try again.");
            }
        }
    }
}