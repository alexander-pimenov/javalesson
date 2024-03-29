package ru.pimalex1978.basepatterns.creational.abstractfactory;

import ru.pimalex1978.basepatterns.creational.abstractfactory.banking.BankTeamFactory;
import ru.pimalex1978.basepatterns.creational.abstractfactory.website.WebsiteTeamFactory;

public class AuctionWebsite {
    public static void main(String[] args) {
        //Изменяя только объект projectTeamFactory можно получить ниже разные выводы программы.
        ProjectTeamFactory projectTeamFactory = new BankTeamFactory();
//        ProjectTeamFactory projectTeamFactory = new WebsiteTeamFactory();
        Developer teamLead = projectTeamFactory.getFirstDeveloper();
        Developer developer = projectTeamFactory.getSecondDeveloper();
        Tester tester = projectTeamFactory.getTester();
        ProjectManger projectManger = projectTeamFactory.getProjectManager();

        System.out.println("Creating website project...");
        teamLead.writeCode();
        developer.writeCode();
        tester.testCode();
        projectManger.manageProject();
    }
}
