package ru.pimalex1978.basepatterns.behavioral.command.proselyte;

/**
 * Command2
 */
public class UpdateCommand implements Command {
    //Внутри себя содержит базу данных,
    //т.е. ссылку на класс соединяющийся с БД
    Database database;

    public UpdateCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.update();
    }
}
