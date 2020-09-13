package ru.pimalex1978.basepatterns.structural.facade;

/**
 * Класс Клиент.
 * Ссылки к нашим элементам Job, BugTracker, Developer из
 * клиентского кода идут не напрямую, а через фасад
 * Workflow (рабочий процесс).
 */
public class SprintRunner {
    public static void main(String[] args) {
        Workflow workflow = new Workflow();

        workflow.solveProblems();
    }
}

//Не создавая Facade мы можем все наши
//классы вызывать напрямую:
//        Job job = new Job();
//        job.doJob();
//        BugTracker bugTracker = new BugTracker();
//        bugTracker.startSprint();
//        Developer developer = new Developer();
//        developer.doJobBeforeDeadline(bugTracker);
//
//        bugTracker.finishSprint();
//        developer.doJobBeforeDeadline(bugTracker);
//Результат:
//Job in progress...
//Sprint is active.
//Developer is solving problems...
//Sprint is not active.
//Developer is reading Habrahabr...
