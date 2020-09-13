package ru.pimalex1978.basepatterns.creational.factory;

/**
 * Одна из реализаций интерфейса DeveloperFactory.
 */
public class JavaDeveloperFactory implements DeveloperFactory {
    @Override
    public Developer createDeveloper() {
        return new JavaDeveloper();
    }
}
