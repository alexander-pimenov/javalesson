package ru.pimalex1978.basepatterns.creational.factory;

public class GoDeveloperFactory implements DeveloperFactory {

    @Override
    public Developer createDeveloper() {
        return new GoDeveloper();
    }
}
