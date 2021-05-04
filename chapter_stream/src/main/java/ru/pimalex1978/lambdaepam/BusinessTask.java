package ru.pimalex1978.lambdaepam;

/**
 * Интерфейс для примера.
 * Будем использовать чтобы посмотреть, как происходит
 * преобразование.
 */
public interface BusinessTask {
    void updateStateInDB();

    default void updateStateInDBByDefault() {
        System.out.println("I'm not abstract method in interface");
    }
}
