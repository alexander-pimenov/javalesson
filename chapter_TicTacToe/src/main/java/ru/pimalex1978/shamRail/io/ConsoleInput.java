package ru.pimalex1978.shamRail.io;

import ru.pimalex1978.shamRail.board.Position;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Position readPosition() {
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);
        return new Position(row, col);
    }
}
