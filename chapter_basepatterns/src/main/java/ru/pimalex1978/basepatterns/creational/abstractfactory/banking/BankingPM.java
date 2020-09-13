package ru.pimalex1978.basepatterns.creational.abstractfactory.banking;

import ru.pimalex1978.basepatterns.creational.abstractfactory.ProjectManger;

public class BankingPM implements ProjectManger {
    @Override
    public void manageProject() {
        System.out.println("Banking PM manages banking project...");
    }
}
