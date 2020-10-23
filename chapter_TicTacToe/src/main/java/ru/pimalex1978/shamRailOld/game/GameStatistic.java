package ru.pimalex1978.shamRailOld.game;

/**
 * Игровая статистика.
 */
public class GameStatistic {

    /**
     * Очки компьютера.
     */
    private int computerScore;

    /**
     * Очки пользователя.
     */
    private int userScore;

    /**
     * Количество игр.
     */
    private int gamesCount;

    public GameStatistic(int gamesCount) {
        this.computerScore = 0;
        this.gamesCount = gamesCount;
        this.userScore = 0;
    }

    /**
     * Распределяет очки между игроками.
     * @param game игра.
     */
    public void distributeScore(Game game) {
        if (game.getWinner().equalsIgnoreCase("computer")) {
            increaseComputerScore();
        } else if (game.getWinner().equalsIgnoreCase("user")) {
            increaseUserScore();
        }
    }

    /**
     * Увеличает очки компьютера.
     */
    public void increaseComputerScore() {
        computerScore++;
    }

    /**
     * Увеличивает очки пользователя.
     */
    public void increaseUserScore() {
        userScore++;
    }

    /**
     * Сравнивает очки.
     * @return -1 больше у пользоватлея, 1 у компьютера, 0 ничья.
     */
    public int compareScore() {
        return Integer.compare(userScore, computerScore);
    }

    public int getComputerScore() {
        return computerScore;
    }

    public int getUserScore() {
        return userScore;
    }

    /**
     * @return кол-во игр.
     */
    public int getGamesCount() {
        return gamesCount;
    }
}