package ru.pimalex1978.basepatterns.behavioral.command.vysokomornyi;

/**
 * Класс Invoker
 * Пользователь, работающий с командами.
 * Он не знает про Computer ничего,
 * мы пользователю даем конкретные команды.
 * Т.е. у него есть три кнопки. И всё.
 * Работет пользователь с интерфейсами, он
 * не знает какая команда и как реаизована.
 */
public class User {
    //Имеет три команды
    private Command start;
    private Command stop;
    private Command reset;

    public User(Command start, Command stop, Command reset) {
        this.start = start;
        this.stop = stop;
        this.reset = reset;
    }

    //Методы для запуска трех команд
    void startComputer() {
        start.execute();
    }

    void stopComputer() {
        stop.execute();
    }

    void resetComputer() {
        reset.execute();
    }
}
