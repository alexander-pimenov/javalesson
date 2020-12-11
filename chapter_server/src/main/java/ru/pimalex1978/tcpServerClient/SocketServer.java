package ru.pimalex1978.tcpServerClient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Основы программирования TCP-сокетов на Java.
 * https://nuancesprog.ru/p/8583/
 * <p>
 * В приведенной выше программе сервер открывает сокет с
 * порта 50001 на серверной машине и ожидает клиента на
 * server.accept(). После подключения клиента создается
 * экземпляр выходного потока. Это может быть использовано
 * для отправки данных с сервера на подключенный клиент.
 * Именно это и делает serverOutput.writeBytes(). После
 * отправки данных соединение с клиентом завершается.
 */
public class SocketServer {
    public static final int SERVER_PORT = 50001;

    public static void main(String[] args) {
        try {
            System.out.println("Server started.");
            ServerSocket server = new ServerSocket(SERVER_PORT);
            Socket clientConn = server.accept();
            DataOutputStream serverOutput = new DataOutputStream(clientConn.getOutputStream());
            serverOutput.writeBytes("Java revisited\n");
            clientConn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
