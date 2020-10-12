package ru.pimalex1978.basepatterns.behavioral.command.vysokomornyi;

/**
 * ConcreteCommand
 * Команда для работы с компьтером.
 */
public class StartCommand implements Command {
    //кладем сюда ссылку на Comp
    Comp computer;

    public StartCommand(Comp computer) {
        this.computer = computer;
    }

    /**
     * Метод вызывающий у компьютера start().
     */
    @Override
    public void execute() {
        computer.start();
    }
}
