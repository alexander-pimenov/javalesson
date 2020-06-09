package ru.pimalex1978.dom.example3;

/**
 * Абстрактный класс Human, нужен чтобы вынести переменную name
 * из каждого класса наследника (Student, Professor, Member) под общий знаменатель.
 */

public abstract class Human {
    private String name;

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
