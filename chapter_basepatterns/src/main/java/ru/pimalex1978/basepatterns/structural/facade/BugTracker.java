package ru.pimalex1978.basepatterns.structural.facade;

/**
 * Класс BugTracker для работы со Спринтами.
 * Спринт - это отрезок времени за который должна
 * быть сделана какая то работа Job.
 */
public class BugTracker {
    private boolean activeSprint;

    /**
     * Метод для того чтоб узнать активный Sprint или нет.
     *
     * @return булево значение или true или false.
     */
    public boolean isActiveSprint() {
        return activeSprint;
    }

    public void startSprint() {
        System.out.println("Sprint is active.");
        activeSprint = true;
    }

    public void finishSprint() {
        System.out.println("Sprint is not active.");
        activeSprint = false;
    }
}
