package ru.pimalex1978.function_interface;

/**
 * Как вызывать default метод интерфейса в реализующем этот интерфейс классе?
 * Используя ключевое слово super вместе с именем интерфейса.
 */
public class DefaultMethodMain {
    public static void main(String[] args) {
        Licence licence = new Licence();
        licence.show();
    }
}

interface Paper {
    default void show() {
        System.out.println("default method show() from interface");
    }
}

class Licence implements Paper {
    public void show() {
        Paper.super.show();
    }
}
