package ru.pimalex1978.basepatterns.behavioral.command.vysokomornyi;

/**
 * ConcreteCommand
 * Команда для работы с компьтером.
 */
public class ResetCommand implements Command {

    //кладем сюда ссылку на Comp
    Comp computer;

    public ResetCommand(Comp computer) {
        this.computer = computer;
    }

    /**
     * Метод вызывающий у компьютера reset().
     */
    @Override
    public void execute() {
        computer.reset();
    }
}
