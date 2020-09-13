package ru.pimalex1978.basepatterns.creational.abstractfactory.website;


import ru.pimalex1978.basepatterns.creational.abstractfactory.Developer;

public class PhpDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Php developer writes php code...");
    }
}
