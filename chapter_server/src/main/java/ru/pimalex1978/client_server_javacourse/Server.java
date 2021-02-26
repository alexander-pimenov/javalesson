package ru.pimalex1978.client_server_javacourse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс - сервер, принимает запросы от клиентов и отдает данные.
 * Сервер - серверное приложение, которое будет слушать запросы и отвечать
 * на них.
 * <p>
 * ServerSocket Серверный сокет “открывается” на локальном компьютере и
 * занимает определенный порт. В нашем случае порт 1777.
 * Дальше сервер входит в бесконечный цикл, в рамках которого и происходит
 * обработка запросов от клиентских приложений.
 * <p>
 * Первым шагом внутри цикла сервер переходит в режим ожидания соединения
 * — вызов accept(). При приходе запроса от клиентского приложения метод
 * возвращает объект Socket, который используется так же как и клиентский
 * сокет.
 * <p>
 * Схема работы серверного сокета упрощенно выглядит так:
 * <p>
 * 1. Создаем серверный сокет на определенном порту
 * 2. Входим в цикл, в котором:
 * ___1) вызываем метод accept()
 * ___2) при приходе соединения получаем объект типа Socket
 * ___3) работаем с этим сокетом через потоки ввода-вывода
 * ___4) по окончанию закрываем потоки и объект типа Socket
 * <p>
 * Если проводит бытовую аналогию серверного сокета — в офисе на телефоне
 * сидит секретарь (вызов метода accept). Как только приходит звонок, он
 * поднимает трубку (появляется объект типа Socket) и проводит разговор
 * (использует потоки ввода-вывода). После окончания трубка кладется и
 * цикл повторяется.
 * <p>
 * Источник:
 * https://java-course.ru/begin/networking/
 */
public class Server {
    public static void main(String[] args) {
        // Определяем номер порта, который будет "слушать" сервер
        int port = 1777;

        try {
            // Открыть серверный сокет (ServerSocket)
            // Это специальный класс для сетевого взаимодействия с серверной стороны
            ServerSocket servSocket = new ServerSocket(port);

            // Входим в бесконечный цикл - ожидаем соединения
            while (true) {
                System.out.println("Waiting for a connection on " + port);

                // Получив соединение начинаем работать с сокетом
                // Работаем с потоками ввода-вывода
                try (Socket localSocket = servSocket.accept();
                     PrintWriter pw = new PrintWriter(localSocket.getOutputStream(), true);
                     BufferedReader br = new BufferedReader(new InputStreamReader(localSocket.getInputStream()))) {

                    // Читаем сообщения от клиента до тех пор пока он не скажет "bye"
                    String str;
                    while ((str = br.readLine()) != null) {
                        // Печатаем сообщение
                        System.out.println("The message: " + str);

                        // Сравниваем с "bye" и если это так - выходим из цикла
                        if (str.equalsIgnoreCase("bye")) {
                            // Тоже говорим клиенту "bye" и выходим из цикла
                            pw.println("bye");
                            break;
                        } else {
                            // Посылаем клиенту ответ
                            str = "Server returns: " + str;
                            pw.println(str);
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}