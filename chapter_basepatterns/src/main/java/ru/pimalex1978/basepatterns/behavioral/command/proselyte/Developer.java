package ru.pimalex1978.basepatterns.behavioral.command.proselyte;

/**
 * Класс разработчик - тот кто работает с БД.
 * В схеме к паттерну это Invoker, вызывающий класс,
 * который хранит ссылки на реализации интерфейса.
 * Он может содержать в себе историю команд, логировать их,
 * вызывать их, запускать их.
 */
public class Developer {
    //У нас есть 4 команды
    private Command insert;
    private Command update;
    private Command select;
    private Command delete;

    public Developer(Command insert, Command update, Command select, Command delete) {
        this.insert = insert;
        this.update = update;
        this.select = select;
        this.delete = delete;
    }

    //метод для запуска команды insert
    public void insertRecord(){
        insert.execute();
    }

    //метод для запуска команды update
    public void updateRecord(){
        update.execute();
    }

    //метод для запуска команды select
    public void selectRecord(){
        select.execute();
    }

    //метод для запуска команды delete
    public void deleteRecord(){
        delete.execute();
    }
}
