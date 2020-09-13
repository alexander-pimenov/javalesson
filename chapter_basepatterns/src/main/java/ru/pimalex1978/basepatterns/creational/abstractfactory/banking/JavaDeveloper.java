package ru.pimalex1978.basepatterns.creational.abstractfactory.banking;


import ru.pimalex1978.basepatterns.creational.abstractfactory.Developer;

public class JavaDeveloper implements Developer {

    @Override
    public void writeCode() {
        System.out.println("Java developer writes Java code...");
    }
}
