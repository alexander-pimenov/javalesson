package ru.pimalex1978.puzzlers;

public interface Dog {
    default void bark() {
        System.out.println("гав");
    }
}
