package ru.pimalex1978.basepatterns.behavioral.command.vysokomornyi;

public class CommandApp {
    public static void main(String[] args) {
        Comp computer = new Comp();
        User user = new User(
                new StartCommand(computer),
                new StopCommand(computer),
                new ResetCommand(computer)
        );

        //Для примера не правильно созданные реализации интерфейса
        User user2 = new User(
                new StartCommand(computer),
                new StartCommand(computer),
                new StartCommand(computer)
        );

        //Вызываем у нашего пользователя методы
        user.startComputer();
        user.resetComputer();
        user.stopComputer();

        //Для примера вызов команд при не правильно
        //созданных реализациях интерфейса
        user2.startComputer();
        user2.resetComputer();
        user2.stopComputer();
    }
}
