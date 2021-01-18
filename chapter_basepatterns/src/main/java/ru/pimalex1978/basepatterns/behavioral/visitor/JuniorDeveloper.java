package ru.pimalex1978.basepatterns.behavioral.visitor;

public class JuniorDeveloper implements Developer {
    @Override
    public void create(ProjectClass projectClass) {
        System.out.println("Writing poor class...");
    }

    @Override
    public void create(Database database) {
        System.out.println("Drop ru.job4j.vacancyparser.database...");
    }

    @Override
    public void create(Test test) {
        System.out.println("Creating not reliable test...");
    }
}
