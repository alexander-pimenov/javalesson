package ru.pimalex1978.basepatterns.structural.bridge;

public class CppDeveloper implements Developer{
    @Override
    public void writeCode() {
        System.out.println("C++ developer writes C++ code...");
    }
}
