package ru.pimalex1978.geekbrains;

import java.util.Random;
import java.util.Scanner;

/**
 * https://geekbrains.ru/posts/java_ticktacktoe_game
 * аналогичный код здесь:
 * https://www.youtube.com/watch?v=Ty6yhlxon_g
 */
public class TicTacToe {
    /*
     *   Сетка:
     *   0 1 2
     * 0 . . .
     * 1 . . .
     * 2 . . .
     *
     * Номера позиций в сетке:
     *      0     1     2
     * 0  (1 1) (1 2) (1 3)
     * 1  (2 1) (2 2) (2 3)
     * 2  (3 1) (3 2) (3 3)
     *
     */
    /**
     * В качестве полей используем
     * три символьные константы.
     * Их значения нельзя изменять,
     * об этом говорит модификатор final
     */
    private final char SIGN_X = 'x';
    //    private final char SIGN_X = '\uc6c3'; //корейский символ похожий на человека
    private final char SIGN_O = 'o';
    private final char SIGN_EMPTY = '.';
    private int sizeBoard;
    private static int playerScore = 0;
    private static int computerScore = 0;
    /**
     * Двумерный символьный массив
     * table будет нашим игровым полем.
     */
    private char[][] gameBoard;
    /**
     * Объект random для генерации ходов компьютера.
     */
    private Random random;
    /**
     * Scanner для ввода данных от пользователя.
     */
    private Scanner scanner;

    public static void main(String[] args) {
//        new TicTacToe(5).game();
        new TicTacToe().game();
    }

    TicTacToe() {
        // конструктор: инициализация полей
        random = new Random();
        scanner = new Scanner(System.in);
        gameBoard = new char[3][3];
        sizeBoard = 3;
    }

    public TicTacToe(int size) {
        this.random = new Random();
        this.scanner = new Scanner(System.in);
        this.gameBoard = new char[size][size];
        this.sizeBoard = size;
    }

    /**
     * Игровая логика.
     * <p>
     * //инициализация таблицы
     * //и рисование таблицы
     * while (true) {
     * // ход человека
     * // проверка: если победа человека или ничья:
     * //    сообщить и выйти из цикла
     * // ход компьютера
     * // проверка: если победа компьютера или ничья:
     * //    сообщить и выйти из цикла
     * }
     */
    void game() {
        System.out.println("Welcome to the game TicTacToe!");
        initBoard();
        printBoard();

        boolean gameOver = false;
        boolean playAgain = true;

        while (playAgain) {
            while (!gameOver) {
                moveHuman(); //ход человека
                printBoard();
                //проверка: победа человека или ничья
                gameOver = checkWin(SIGN_X); //для цикличной игры
//                if (checkWin(SIGN_X)) {
//                    System.out.println("YOU WIN!");
//                    break;
//                }
                if (gameOver) { //для цикличной игры
                    playerScore++;
                    System.out.println("GAME OVER.");
                    break;
                }
                //проверка на заполненность игровой таблицы, т.е. ничья
                gameOver = isTableFull();
//                if (isTableFull()) {
//                    System.out.println("Sorry, DRAW!");
//                    break;
//                }
                if (gameOver) {
                    System.out.println("Sorry, DRAW!");
                    System.out.println("GAME OVER.");
                    break;
                }
                //ход компьютера
                moveComputer();
                //текущее состояние игровой таблицы
                printBoard();
                //проверка: победа компьютера или ничья
                gameOver = checkWin(SIGN_O);
//                if (checkWin(SIGN_O)) {
//                    System.out.println("AI WIN!");
//                    break;
//                }
                if (gameOver) {
                    computerScore++;
                    System.out.println("GAME OVER.");
                    break;
                }
                //проверка на заполненность игровой таблицы, т.е. ничья
                gameOver = isTableFull();
//                if (isTableFull()) {
//                    System.out.println("Sorry, DRAW!");
//                    break;
//                }
                if (gameOver) {
                    System.out.println("Sorry, DRAW!");
                    System.out.println("GAME OVER.");
                    break;
                }
            }
//            System.out.println("GAME OVER.");
            System.out.println("Player Score: " + playerScore);
            System.out.println("Computer Score: " + computerScore);
            System.out.println("Would you like to play again? Y/N");
            scanner.nextLine(); //чтобы ждал ответ
            String result = scanner.nextLine();
            switch (result) {
                case "Y":
                case "y":
                    playAgain = true;
                    System.out.println("Let's play again.");
                    //нужно сбросить данные на доске
                    resetBoard(gameBoard);
                    gameOver = false;
                    printBoard();
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
//        printTable();
//        System.out.println("GAME OVER.");
    }

    /**
     * Обеспечивает начальную инициализацию игрового поля,
     * заполняя его ячейки символами «точка».
     */
    private void initBoard() {
        for (int row = 0; row < sizeBoard; row++)
            for (int col = 0; col < sizeBoard; col++)
                gameBoard[row][col] = SIGN_EMPTY;
    }
//    private void initTable() {
//        for (int row = 0; row < 3; row++)
//            for (int col = 0; col < 3; col++)
//                table[row][col] = SIGN_EMPTY;
//
//    }

    /**
     * Метод, отображающий текущее состояние игровой таблицы.
     */
    private void printBoard() {
        System.out.print(" ");
        for (int header = 0; header < sizeBoard; header++) {
            System.out.print(" " + (header + 1));
        }
        System.out.println();
        for (int row = 0; row < sizeBoard; row++) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < sizeBoard; col++)
                System.out.print(gameBoard[row][col] + " ");
            System.out.println();
        }
    }
//    private void printTable() {
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++)
//                System.out.print(table[row][col] + " ");
//            System.out.println();
//        }
//    }

    /**
     * Метод проверяет игровую таблицу на «победную тройку»,
     * «победную четверку», «победную пятерку» и т.д. — три
     * (четыре, пять ...) одинаковых знака подряд, по вертикали
     * или горизонтали, а также по двум диагоналям. Проверяемый
     * знак указан как параметр char symbol, за счёт чего метод
     * универсален - можно проверять победу и по «крестикам» и по «ноликам».
     * В случае победы возвращается булевское значение true,
     * в противном случае — false.
     *
     * @param dot проверяемый символ
     * @return boolean result
     */
    private boolean checkWin(char dot) {
        for (int i = 0; i < sizeBoard; i++) {
            int result = 0;
            for (int j = 0; j < sizeBoard; j++) {
                if (gameBoard[i][j] == dot) result++;
            }
            if (result == sizeBoard) {
                return true;
            }
        }
        for (int i = 0; i < sizeBoard; i++) {
            int result = 0;
            for (int j = 0; j < sizeBoard; j++) {
                if (gameBoard[j][i] == dot) result++;
            }
            if (result == sizeBoard) {
                return true;
            }
        }

        int firstDiagonal = 0;
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                if (j == i && gameBoard[i][j] == dot) firstDiagonal++;
            }
        }
        if (firstDiagonal == sizeBoard) {
            return true;
        }

        int secondDiagonal = 0;
        for (int i = 0, j = sizeBoard - 1; i < sizeBoard && j >= 0; i++, j--) {
            if (gameBoard[i][j] == dot) secondDiagonal++;
        }
        if (secondDiagonal == sizeBoard) {
            return true;
        }
        return false;
    }
//    private boolean checkWin(char dot) {
//        for (int i = 0; i < 3; i++)
//            if ((table[i][0] == dot && table[i][1] == dot &&
//                    table[i][2] == dot) ||
//                    (table[0][i] == dot && table[1][i] == dot &&
//                            table[2][i] == dot))
//                return true;
//        if ((table[0][0] == dot && table[1][1] == dot &&
//                table[2][2] == dot) ||
//                (table[2][0] == dot && table[1][1] == dot &&
//                        table[0][2] == dot))
//            return true;
//        return false;
//    }

    /**
     * Метод, реализующий ход компьютера.
     * Только координаты ячейки не считываются с консоли,
     * а генерируются случайно, при помощи метода nextInt(3)
     * объекта random.
     */
    private void moveComputer() {
        int x, y;
        do {
            x = random.nextInt(sizeBoard);
            y = random.nextInt(sizeBoard);
        } while (!isCellValidForComputer(x, y));
        gameBoard[x][y] = SIGN_O;
        System.out.println("Computer moved at position: " + (x + 1) + " - " + (y + 1));
    }
//    private void turnAI() {
//        int x, y;
//        do {
//            x = random.nextInt(3);
//            y = random.nextInt(3);
//        } while (!isCellValid(x, y));
//        table[y][x] = SIGN_O;
//    }

    /**
     * Метод, реизующий ход пользователя.
     */
    private void moveHuman() {
        int x, y;
        do {
            System.out.println("Enter cell (row and column) with space. Example: '1 3' (range:  1.." + this.sizeBoard + "):");
            x = checkDigitInputAndReturnInt() - 1;
//            x = scanner.nextInt() - 1;
            y = checkDigitInputAndReturnInt() - 1;
//            y = scanner.nextInt() - 1;
        } while (!isCellValidForHuman(x, y));
        gameBoard[x][y] = SIGN_X;
        System.out.println("Player moved at position: " + (x + 1) + " - " + (y + 1));
    }
//    private void turnHuman() {
//        int x, y;
//        do {
//            System.out.println("Enter X and Y (1..3):");
//            x = scanner.nextInt() - 1;
//            y = scanner.nextInt() - 1;
//        } while (!isCellValid(x, y));
//        table[y][x] = SIGN_X;
//    }

    /**
     * Метод, определяющий валидность ячейки.
     *
     * @param x координата ячейки по X
     * @param y координата ячейки по Y
     * @return boolean result.
     */
    private boolean isCellValidForHuman(int x, int y) {
        if (x < 0 || y < 0 || x >= sizeBoard || y >= sizeBoard) {
            System.out.println("Не верно введен номер ячейки. Try again.");
            return false;
        }
        boolean result = false;
        if (gameBoard[x][y] == SIGN_EMPTY) {
            result = true;
        } else {
            System.out.println("Эта ячейка занята. Введите другую ячейку.");
        }
        return result;
    }
//    private boolean isCellValid(int x, int y) {
//        if (x < 0 || y < 0 || x >= sizeBoard || y >= sizeBoard) {
//            System.out.println("Не верно введен номер ячейки. Try again.");
//            return false;
//        }
//        if (table[x][y] == SIGN_EMPTY) return true;
//        return false;
//    }
//    private boolean isCellValid(int x, int y) {
//        if (x < 0 || y < 0 || x >= 3 || y >= 3) {
//            return false;
//        }
//        return table[y][x] == SIGN_EMPTY;
//    }

    /**
     * Метод, определяющий валидность ячейки для хода
     * компьютера. Немного отчличается от метода для
     * хода человека.
     *
     * @param x координата ячейки по X
     * @param y координата ячейки по Y
     * @return boolean result.
     */
    private boolean isCellValidForComputer(int x, int y) {
        if (x < 0 || y < 0 || x >= sizeBoard || y >= sizeBoard) {
            return false;
        }
        boolean result = false;
        if (gameBoard[x][y] == SIGN_EMPTY) {
            result = true;
        }
        return result;
    }

    /**
     * Метод проверяющий, что вводится имено целое число,
     * а не что то другое. Возвращает целое введеное число.
     *
     * @return целое число.
     */
    public int checkDigitInputAndReturnInt() {
        int move;
        while (!scanner.hasNextInt()) {
            System.out.println("That not a number! Try again.");
            scanner.next(); //this is important! - продвигаем Scanner до тех пор, пока не будет hasNextInt()
        }
        move = scanner.nextInt();
        return move;
    }

    /**
     * Метод проходит по всем ячейкам игровой таблицы и,
     * если они все заняты, возвращает true. Если хотя
     * бы одна ячейка ещё свободна, возвращается false.
     *
     * @return boolean result
     */
    private boolean isTableFull() {
        for (int row = 0; row < sizeBoard; row++)
            for (int col = 0; col < sizeBoard; col++)
                if (gameBoard[row][col] == SIGN_EMPTY)
                    return false;
        return true;
    }
//    private boolean isTableFull() {
//        for (int row = 0; row < 3; row++)
//            for (int col = 0; col < 3; col++)
//                if (table[row][col] == SIGN_EMPTY)
//                    return false;
//        return true;
//    }

    /**
     * Метод сбрасывает все записи на поле в начальное
     * состояние. Т.е. как при начале игры.
     *
     * @param board игровое поле.
     */
    public void resetBoard(char[][] board) {
        for (int row = 0; row < sizeBoard; row++) {
            for (int col = 0; col < sizeBoard; col++) {
                board[row][col] = SIGN_EMPTY;
            }
        }
    }


    public void selectSizeBoard() {

    }


}
