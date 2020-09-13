package ru.pimalex1978.basepatterns.structural.facade;

/**
 * Класс Developer делающий работу.
 * Когда есть еще время, то он делает работу, а когда время
 * вышло, то он читает Хабр.
 */
public class Developer {
    public void doJobBeforeDeadline(BugTracker bugTracker) {
        if (bugTracker.isActiveSprint()) {
            System.out.println("Developer is solving problems...");
        } else {
            System.out.println("Developer is reading Habrahabr...");
        }
    }
}
