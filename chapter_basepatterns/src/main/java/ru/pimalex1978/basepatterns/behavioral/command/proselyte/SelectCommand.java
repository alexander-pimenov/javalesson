package ru.pimalex1978.basepatterns.behavioral.command.proselyte;

/**
 * Command3
 */
public class SelectCommand implements Command {
    //Внутри себя содержит базу данных,
    //т.е. ссылку на класс соединяющийся с БД
    Database database;

    public SelectCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.select();
    }
}
