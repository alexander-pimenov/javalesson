package ru.pimalex1978.basepatterns.behavioral.command.proselyte;

/**
 * Command1
 */
public class DeleteCommand implements Command {
    //Внутри себя содержит базу данных,
    //т.е. ссылку на класс соединяющийся с БД
    Database database;

    public DeleteCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.delete();
    }
}
