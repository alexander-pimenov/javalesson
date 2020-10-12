package ru.pimalex1978.basepatterns.behavioral.command.proselyte;

/**
 * Command4
 */
public class InsertCommand implements Command {
    //Внутри себя содержит базу данных,
    //т.е. ссылку на класс соединяющийся с БД
    Database database;

    //Конструктор принимает БД
    public InsertCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.insert();
    }
}
