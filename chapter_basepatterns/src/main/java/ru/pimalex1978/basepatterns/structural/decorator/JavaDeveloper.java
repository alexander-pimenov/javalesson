package ru.pimalex1978.basepatterns.structural.decorator;

public class JavaDeveloper implements Developer{
    @Override
    public String makeJob() {
        return "Write Java code.";
    }
}
