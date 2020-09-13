package ru.pimalex1978.basepatterns.structural.facade;

/**
 * Класс Workflow (рабочий процесс) - это и есть Фасад.
 * Цель данного класса - это компоновка всех наших
 * элементов: Job, BugTracker, Developer.
 */
public class Workflow {
    private Developer developer = new Developer();
    private Job job = new Job();
    private BugTracker bugTracker = new BugTracker();

    public void solveProblems() {
        job.doJob();
        bugTracker.startSprint();
        developer.doJobBeforeDeadline(bugTracker);
    }
}
