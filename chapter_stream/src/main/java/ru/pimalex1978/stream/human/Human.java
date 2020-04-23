package ru.pimalex1978.stream.human;

import java.util.List;

/**
 * Класс Human.
 * Содержит Имя человека и список имен его питомцев.
 */

public class Human {
    private final String name;
    private final List<String> pets;

    public Human(String name, List<String> pets) {
        this.name = name;
        this.pets = pets;
    }

    public String getName() {
        return name;
    }

    public List<String> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return "Human{" + "name='" + name + '\''
                + ", pets=" + pets + '}';
    }
}
