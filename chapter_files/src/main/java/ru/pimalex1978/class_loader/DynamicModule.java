package ru.pimalex1978.class_loader;

public class DynamicModule extends TrueStaticModule {
    public String toString() {
        return "DynamicModule, version 1! " + (counter++);
    }
}
