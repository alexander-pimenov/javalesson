package ru.pimalex1978.basepatterns.behavioral.command.vysokomornyi;

/**
 * ConcreteCommand
 * Команда для работы с компьтером.
 */
public class StopCommand implements Command {
    //кладем сюда ссылку на Comp
    Comp computer;

    public StopCommand(Comp computer) {
        this.computer = computer;
    }

    /**
     * Метод вызывающий у компьютера stop().
     */
    @Override
    public void execute() {
        computer.stop();
    }
}
