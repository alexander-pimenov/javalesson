package ru.pimalex1978.basepatterns.creational.abstractfactory.website;


import ru.pimalex1978.basepatterns.creational.abstractfactory.Tester;

public class ManualTester implements Tester {
    @Override
    public void testCode() {
        System.out.println("Manual tester tests website...");
    }
}
