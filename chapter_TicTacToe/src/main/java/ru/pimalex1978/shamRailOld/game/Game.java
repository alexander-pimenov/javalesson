package ru.pimalex1978.shamRailOld.game;

import ru.pimalex1978.shamRailOld.io.Input;
import ru.pimalex1978.shamRailOld.io.Output;
import ru.pimalex1978.shamRailOld.logic.BoardAnalyser;
import ru.pimalex1978.shamRailOld.logic.Player;

/**
 * Единичная игра.
 */
public class Game {

    /**
     * Игровое поле.
     */
    private Board board;

    /**
     * Способ ввода.
     */
    private Input userInput;

    /**
     * Способ вывода.
     */
    private Output output;

    /**
     * Оппонент пользователя.
     */
    private Player computerPlayer;

    /**
     * Победитель в данной игре.
     */
    private String winner;

    public Game(Board board, Input userInput, Output output, Player computerPlayer) {
        this.board = board;
        this.userInput = userInput;
        this.output = output;
        this.computerPlayer = computerPlayer;
    }

    /**
     * Запускает игру.
     * Игра заканчивается, если поле заполнена
     * или есть выигрышная стратегия.
     */
    public void start() {
        boolean isFull;
        boolean isOver;
        boolean step = ("yes".equals(userInput.ask("Make first step(yes|no): ")));
        BoardAnalyser boardAnalyser = new BoardAnalyser(board);
        do {
            handleGameSide(step, boardAnalyser);
            step = changeSide(step);
            output.writeBoard();
            output.writeMessage(System.lineSeparator());
            isFull = !boardAnalyser.hasFreeCell();
            isOver = boardAnalyser.checkWin() || isFull;
            processWinner(isOver, step, isFull);
        } while (!isOver);
    }

    private void handleGameSide(boolean side, BoardAnalyser boardAnalyser) {
        if (side) {
            handleUserInput(boardAnalyser);
        } else {
            handleComputerInput();
        }
    }

    /**
     * Обрабатывает ход пользователя.
     * @param boardAnalyser вспомогательный объект для анализа доски.
     */
    private void handleUserInput(BoardAnalyser boardAnalyser) {
        boolean exit = false;
        do {
            int x = Integer.valueOf(userInput.ask("Enter row: ")) - 1;
            int y = Integer.valueOf(userInput.ask("Enter column: ")) - 1;
            if (boardAnalyser.hasFreeCell(x, y)) {
                board.setCellState(computerPlayer.getOpponentSide(), x, y);
                exit = true;
            } else {
                output.writeMessage("Cell is busy. Try again");
            }
        } while (!exit);
    }

    /**
     * Обрабатывает ход компьютера.
     */
    private void handleComputerInput() {
        output.writeMessage("Computer thinks...");
        computerPlayer.move();
    }

    /**
     * Изменяет сторону игры.
     * @param side текущая сторона.
     * @return противоположная сторона.
     */
    private boolean changeSide(boolean side) {
        return !side;
    }

    private void processWinner(boolean gameEnd, boolean gameStep, boolean isFull) {
        if (gameEnd) {
            winner = isFull ? "Nobody"
                    : gameStep ? "Computer" : "User";
            output.writeMessage(String.format("%s wins!", winner));
        }
    }

    /**
     * Возвращает имя того кто - выиграл либо
     * Nobody, если никто не выиграл.
     * @return Computer User Nobody.
     */
    public String getWinner() {
        return this.winner;
    }
}