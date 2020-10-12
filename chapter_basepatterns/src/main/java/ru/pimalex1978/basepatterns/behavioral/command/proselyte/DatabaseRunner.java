package ru.pimalex1978.basepatterns.behavioral.command.proselyte;

public class DatabaseRunner {
    public static void main(String[] args) {
        //Создаем базу данных
        Database database = new Database();

        //Создаем разработчика
        Developer developer = new Developer(
                new InsertCommand(database),
                new UpdateCommand(database),
                new SelectCommand(database),
                new DeleteCommand(database)
        );

        //Вызываем у нашего разработчика поочередно
        //все методы
        developer.insertRecord();
        developer.updateRecord();
        developer.selectRecord();
        developer.deleteRecord();
    }
}
