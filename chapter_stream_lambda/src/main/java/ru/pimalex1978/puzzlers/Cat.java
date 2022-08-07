package ru.pimalex1978.puzzlers;

public interface Cat {
    default void mew() {
        System.out.println("мяу");
    }
}
