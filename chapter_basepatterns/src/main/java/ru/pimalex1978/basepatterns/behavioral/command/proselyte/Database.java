package ru.pimalex1978.basepatterns.behavioral.command.proselyte;

/**
 * В классе представлен стандартный набор методов
 * для работы с БД.
 * Шаблон команда используется для создания отдельных
 * классов для каждого из представленных запросов.
 * Receiver - получатель команд.
 */
public class Database {
    public void insert() {
        System.out.println("Inserting record...");
    }

    public void update() {
        System.out.println("Updating record...");
    }

    public void select() {
        System.out.println("Reading record...");
    }

    public void delete() {
        System.out.println("Deleting record...");
    }
}
