package ru.pimalex1978.httpServer;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    /**
     * Порт.
     */
    private int port;
    /**
     * Хранится путь к папке, файлы из которой
     * нужно раздавать.
     */
    private String directory;

    public Server(int port, String directory) {
        this.port = port;
        this.directory = directory;
    }

    void start() {
        //Создаем экземпляр ServerSocket, в его конструктор
        //передаем порт
        try (var server = new ServerSocket(this.port)) {
            System.out.println("Server started");
            //создаем бесконечный цикл
            while (true) {
                //с помощью метода accept() получаем экземпляр сокета
                var socket = server.accept();
                //дальше берем поток, в котором и будет всё происходить
                //для этого создаем экземпляр созданного класса, который
                //наследует Thread
                var thread = new Handler(socket, this.directory);
                //запускаем поток
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //из первого аргумента получаем порт
        var port = Integer.parseInt(args[0]);
        //из второго аргумента получаем директорию
        var directory = args[1];
        //создаем экземпляр сервера и запускаем его
        new Server(port, directory).start();
    }
}
