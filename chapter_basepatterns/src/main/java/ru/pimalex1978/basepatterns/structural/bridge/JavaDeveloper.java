package ru.pimalex1978.basepatterns.structural.bridge;

public class JavaDeveloper implements Developer{
    @Override
    public void writeCode() {
        System.out.println("Java developer writes Java code...");
    }
}
