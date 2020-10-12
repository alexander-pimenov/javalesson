package ru.pimalex1978.basepatterns.behavioral.command.proselyte;

/**
 * Интерфейс Command.
 * Этот интерфейс инициирует запуск какой то комнды
 * методом execute()
 * И он будет иметь 4 реализации согласно 4-м командам
 * для работы с БД:
 * insert, delete, update, select.
 */
public interface Command {
    public void execute();
}
